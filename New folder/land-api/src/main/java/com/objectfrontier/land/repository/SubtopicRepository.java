package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Subtopic;

/**
 * @author sugandapriya.l
 * @since v1.0
 */

@Repository
public interface SubtopicRepository extends CrudRepository<Subtopic, Long> {

}
