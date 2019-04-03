package com.objectfrontier.land.service;

import com.objectfrontier.land.dto.ResourceDTO;
import com.objectfrontier.land.model.Resource;

import java.io.InputStream;

/**
 * @author Sriram Narayanan
 * @since v1.0
 * <p>
 * Interface class that has the operations for ResourceService
 */
public interface ResourceService {

    public ResourceDTO create(InputStream inputStream, String fileContent, String fileOriginalName, Long fileSize);

    public ResourceDTO read(long id);

    public void delete(long id);

    public Resource update(Resource updatedResource);
}