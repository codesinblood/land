package com.objectfrontier.land.repository;

import com.objectfrontier.land.model.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Narayanan m
 * @since v1.0
 * <p>
 * This interface declares the DB operation for Resource entity
 */

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {

}
