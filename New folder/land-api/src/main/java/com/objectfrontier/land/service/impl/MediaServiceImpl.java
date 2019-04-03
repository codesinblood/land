package com.objectfrontier.land.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.objectfrontier.land.common.exception.FileUploadException;
import com.objectfrontier.land.common.exception.PreConditionFailException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.common.utility.MediaHandler;
import com.objectfrontier.land.dto.MediaDTO;
import com.objectfrontier.land.model.Media;
import com.objectfrontier.land.repository.MediaRepository;
import com.objectfrontier.land.service.MediaService;

/**
 * @author Sriram Narayanan
 * @author mani.chellapandian
 * @since v1.0
 * Service class defines the CRUD operations for Media entity
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    Environment environment;

    @Autowired
    private MediaRepository mediaRepository;


    ModelMapper modelMapper = new ModelMapper();

    private static final String IMAGE = "image";
    private static final String VIDEO = "video";
    private static final String IMAGE_MAXSIZE = "imagefile.maxSize";
    private static final String ENCODED_IMAGE_PREFIX = "encodedImage.prefix";
    private static final String ENCODED_IMAGE_SUFFIX = "encodedImage.suffix";

    /**
     * Service class that created a media record by using the Media Handler class.
     */
    @Override
    public MediaDTO create(InputStream inputStream, String contentType, String fileOriginalName, Long fileSize) {

        Long imageFileSizeLimit = environment.getProperty(IMAGE_MAXSIZE, Long.class);
        String encodedImgPrefix = environment.getProperty(ENCODED_IMAGE_PREFIX);
        String encodedImgSuffix = environment.getProperty(ENCODED_IMAGE_SUFFIX);
        Media uploadFile = new Media();
        String localStoragePath;
        String encodedImage = "";

        if ((IMAGE).equals(MediaHandler.getAbstractFileContentType(contentType)) && fileSize > imageFileSizeLimit) {
            throw new PreConditionFailException(ErrorCode.IMAGE_SIZE_LIMIT_EXCEEDED);  //File size is restricted to 5MB
        }

        try {
            localStoragePath = MediaHandler.fileUpload(inputStream, contentType, fileOriginalName);
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.FILE_UPLOAD_ERROR, e);
        }

        uploadFile.setType(contentType);
        uploadFile.setName(StringUtils.isEmpty(fileOriginalName) ? Timestamp.from(Instant.now()).toString() : fileOriginalName);
        uploadFile.setUrl(localStoragePath);
        Media uploadedFile = mediaRepository.save(uploadFile);

        // preparing the image as data:image/png;base64 format 
        String abstractFileContentType = MediaHandler.getAbstractFileContentType(contentType);
        if (IMAGE.equals(abstractFileContentType)) {
            String base64EncodedImage = MediaHandler.imageEncoder(localStoragePath).toString();
            encodedImage = MediaHandler.prepareBase64String(contentType, encodedImgPrefix, encodedImgSuffix, base64EncodedImage);
        }

        MediaDTO mediaDTO = modelMapper.map(uploadedFile, MediaDTO.class);
        mediaDTO.setEncodedMedia(encodedImage);
        return mediaDTO;
    }

    @Override
    public MediaDTO read(long id) {

        String encodedImage;
        byte[] mediaBytes;
        String encodedImgPrefix = environment.getProperty(ENCODED_IMAGE_PREFIX);
        String encodedImgSuffix = environment.getProperty(ENCODED_IMAGE_SUFFIX);

        Media media = mediaRepository.findById(id).get();

        MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);
        switch (MediaHandler.getAbstractFileContentType(media.getType())) {
        case IMAGE:
            encodedImage = MediaHandler.imageEncoder(media.getUrl());
            mediaDTO.setEncodedMedia(MediaHandler.prepareBase64String(media.getType(), encodedImgPrefix, encodedImgSuffix, encodedImage));
            break;
        case VIDEO:
            mediaDTO.setMediaPath(media.getUrl());
            break;
        default:
            try {
                mediaBytes = MediaHandler.convertMiscFilesToByte(media.getUrl());
                mediaDTO.setEncodedMedia(mediaBytes);
            } catch (Exception e) {
                throw new FileUploadException(ErrorCode.FILE_DOWNLOAD_ERROR);
            }
        }
        return mediaDTO;
    }

    @Override
    public void delete(long id) {
        mediaRepository.deleteById(id);
    }

    @Override
    public Media update(Media updatedMedia) {

        Media existingMedia = mediaRepository.findById(updatedMedia.getId()).get();
        existingMedia.setName(updatedMedia.getName());
        existingMedia.setUrl(updatedMedia.getUrl());
        existingMedia.setType(updatedMedia.getType());
        return mediaRepository.save(existingMedia);
    }

}
