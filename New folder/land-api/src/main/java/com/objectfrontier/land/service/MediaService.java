package com.objectfrontier.land.service;

import java.io.InputStream;

import com.objectfrontier.land.dto.MediaDTO;
import com.objectfrontier.land.model.Media;

/**
 * @author Sriram Narayanan
 * @since v1.0
 * <p>
 * Interface class that has the operations for MediaService
 */
public interface MediaService {

    public MediaDTO create(InputStream inputStream, String fileContent, String fileOriginalName, Long fileSize);

    public MediaDTO read(long id);
    
    public void delete(long id);

    public Media update(Media updatedResource);
}