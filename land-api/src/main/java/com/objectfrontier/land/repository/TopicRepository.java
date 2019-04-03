package com.objectfrontier.land.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Topic;

/**
 * @author karthik.n
 * @author mani.chellapandian
 * @since v1.0
 */

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    
    public List<Topic> findByCourseId(long courseId);

}
