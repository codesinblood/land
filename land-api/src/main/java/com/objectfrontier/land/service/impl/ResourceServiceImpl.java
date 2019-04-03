package com.objectfrontier.land.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.objectfrontier.land.common.exception.FileUploadException;
import com.objectfrontier.land.common.exception.PreConditionFailException;
import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.common.utility.ResourceHandler;
import com.objectfrontier.land.dto.ResourceDTO;
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.repository.ResourceRepository;
import com.objectfrontier.land.service.ResourceService;

/**
 * @author Sriram Narayanan
 * @since v1.0
 * Service class defines the CRUD operations for Resource entity
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    Environment environment;
    @Autowired
    private ResourceRepository resourceRepository;
    ModelMapper modelMapper = new ModelMapper();

    private static final String IMAGE = "image";
    private static final String IMAGE_MAXSIZE = "imagefile.maxSize";
    private static final String ENCODED_IMAGE_PREFIX = "encodedImage.prefix";
    private static final String ENCODED_IMAGE_SUFFIX = "encodedImage.suffix";

    /**
     * Service class that created a resource record by using the Resource Handler
     * class.
     */
    @Override
    public ResourceDTO create(InputStream inputStream, String contentType, String fileOriginalName, Long fileSize) {

        Long imageFileSizeLimit = environment.getProperty(IMAGE_MAXSIZE, Long.class);
        String encodedImgPrefix = environment.getProperty(ENCODED_IMAGE_PREFIX);
        String encodedImgSuffix = environment.getProperty(ENCODED_IMAGE_SUFFIX);
        Resource uploadFile = new Resource();
        String localStoragePath;
        String encodedImage = "";

        if ((IMAGE).equals(ResourceHandler.getAbstractFileContentType(contentType)) && fileSize > imageFileSizeLimit) {
            throw new PreConditionFailException(ErrorCode.IMAGE_SIZE_LIMIT_EXCEEDED); // File size is restricted to 5MB
        }

        try {
            localStoragePath = ResourceHandler.fileUpload(inputStream, contentType, fileOriginalName);
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.FILE_UPLOAD_ERROR, e);
        }

        uploadFile.setType(contentType);
        uploadFile.setName(
                StringUtils.isEmpty(fileOriginalName) ? Timestamp.from(Instant.now()).toString() : fileOriginalName);
        uploadFile.setUrl(localStoragePath);
        Resource uploadedFile = resourceRepository.save(uploadFile);

        // preparing the image as data:image/png;base64 format
        String abstractFileContentType = ResourceHandler.getAbstractFileContentType(contentType);
        if (IMAGE.equals(abstractFileContentType)) {
            encodedImage = new StringBuilder().append(encodedImgPrefix).append(contentType).append(encodedImgSuffix)
                    .append(ResourceHandler.imageEncoder(localStoragePath)).toString();
        }

        ResourceDTO resourceDTO = modelMapper.map(uploadedFile, ResourceDTO.class);
        resourceDTO.setId(uploadedFile.getId());
        resourceDTO.setType(contentType);
        resourceDTO.setName(uploadedFile.getName());
        resourceDTO.setContent(encodedImage);
        return resourceDTO;
    }

    @Override
    public ResourceDTO read(long id) {

        String encodedImage;

        Optional<Resource> resource = resourceRepository.findById(id);
        if (resource.isPresent() && resource.get().getType().contains("image")) {
            encodedImage = ResourceHandler.imageEncoder(resource.get().getUrl());
        } else {
            throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        ResourceDTO resourceDTO = modelMapper.map(resource, ResourceDTO.class);
        resourceDTO.setId(resource.get().getId());
        resourceDTO.setType(resource.get().getType());
        resourceDTO.setName(resource.get().getName());
        resourceDTO.setContent(encodedImage);
        return resourceDTO;

        // return resource.orElseThrow(() -> new
        // ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    @Override
    public void delete(long id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public Resource update(Resource updatedResource) {

        Resource existingResource = resourceRepository.findById(updatedResource.getId()).get();
        existingResource.setName(updatedResource.getName());
        existingResource.setUrl(updatedResource.getUrl());
        existingResource.setType(updatedResource.getType());
        return resourceRepository.save(existingResource);
    }
}
