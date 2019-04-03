package com.objectfrontier.land.web;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.objectfrontier.land.common.exception.FileUploadException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.dto.MediaDTO;
import com.objectfrontier.land.model.Media;
import com.objectfrontier.land.service.impl.MediaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author Sriram Narayanan
 * @since v1.0
 * Controller to manage the request and response for the MediaService
 */
@RestController
@RequestMapping("/api/v1/media")
@CrossOrigin(value = "*")
@Api(value = "/api/v1/media")
public class MediaController {

    @Autowired
    MediaServiceImpl mediaService;
    
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
    @ApiOperation(value = "createMedia",
    	          notes = "File has to be uploaded to the storage for creating the media",
    	          response = MediaDTO.class)
    @PostMapping
    public ResponseEntity<MediaDTO> createMedia(@RequestParam(name = "file") MultipartFile file) {

        InputStream inputStream;
        String fileContent = file.getContentType();
        Long fileSize = file.getSize();
        String fileOriginalName = file.getOriginalFilename();
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.FILE_UPLOAD_ERROR);
        }
        return new ResponseEntity<MediaDTO>(mediaService.create(inputStream, fileContent, fileOriginalName, fileSize), HttpStatus.CREATED);
    }

    @GetMapping("/image/{id}")
    @ApiOperation(value = "findImageById",
                  notes = "Find a media based on the id",
                  response = MediaDTO.class)
    public ResponseEntity<MediaDTO> findImageById(@PathVariable("id") long id) {
        return new ResponseEntity<MediaDTO>(mediaService.read(id), HttpStatus.OK);
    }
    
    @GetMapping("/file/{id}/")
    @ApiOperation(value = "findFileById",
                  notes = "Find a media based on the id",
                  response = MediaDTO.class)
    public ResponseEntity<byte[]> findFileById(@PathVariable("id") long id, @RequestParam("choice") boolean choice) {
        HttpHeaders headers = new HttpHeaders();
        MediaDTO mediaDTO = mediaService.read(id);
        if(choice) {
            headers.add("content-disposition", "attachment; filename=" + mediaDTO.getName());
        } else {
            headers.add("content-disposition", "inline; filename=" + mediaDTO.getName());
            headers.add("content_type", "application/pdf");
        }
        return new ResponseEntity<byte[]>((byte[]) mediaDTO.getEncodedMedia(), headers, HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
    @ApiOperation(value = "deleteMediaById",
                  notes = "Delete a media based on the id",
                  hidden = true,
                  response = MediaDTO.class)
    public ResponseEntity<Void> deleteMedia(@PathVariable(required = true) long id) {
        mediaService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}