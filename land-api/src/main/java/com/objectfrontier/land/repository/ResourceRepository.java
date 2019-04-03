package com.objectfrontier.land.repository;

import com.objectfrontier.land.model.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Narayanan m
 * @since v1.0
 * <p>
 * This interface declares the DB operation for Resource entity
 */

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {

}
