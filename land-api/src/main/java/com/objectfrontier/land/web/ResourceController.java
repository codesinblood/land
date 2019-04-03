package com.objectfrontier.land.web;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.objectfrontier.land.dto.ResourceDTO;
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.service.impl.ResourceServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author Sriram Narayanan
 * @since v1.0
 * <p>
 * Controller to manage the request and response for the ResourceService
 */
@RestController
@RequestMapping("/api/v1/resource")
//@Api(value = "/api/v1/resource")
public class ResourceController {

    @Autowired
    ResourceServiceImpl resourceService;

    @ApiOperation(value = "createResource",
    	          notes = "File has to be uploaded to the storage for creating the resource",
    	          response = ResourceDTO.class)
    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestParam(name = "file") MultipartFile file) {

        InputStream inputStream;
        String fileContent = file.getContentType();
        Long fileSize = file.getSize();
        String fileOriginalName = file.getOriginalFilename();
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.FILE_UPLOAD_ERROR);
        }
        return new ResponseEntity<ResourceDTO>(resourceService.create(inputStream, fileContent, fileOriginalName, fileSize), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "findResourceById",
                  notes = "Find a resource based on the id",
                  response = Resource.class)
    public ResponseEntity<ResourceDTO> findResourceById(@PathVariable("id") long id) {
        return new ResponseEntity<ResourceDTO>(resourceService.read(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteResourceById",
                  notes = "Delete a resource based on the id",
                  hidden = true,
                  response = Resource.class)
    public ResponseEntity<Void> deleteResource(@PathVariable(required = true) long id) {
        resourceService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ApiOperation(value = "updateResource",
    notes = "update a resource", response = Resource.class)
    public ResponseEntity<Resource> updateResource(@RequestBody Resource resource) {
        return new ResponseEntity<Resource>(resourceService.update(resource), HttpStatus.OK);
    }
}
