package com.objectfrontier.land.repository;

import com.objectfrontier.land.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jayanth.subramanian
 * @since v1.0
 *
 * Interface declaring the DB operations for Course entity
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
