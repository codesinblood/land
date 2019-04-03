package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Exercise;

/**
 * @author venkatesh.k
 * @since v1.0
 */

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

}
