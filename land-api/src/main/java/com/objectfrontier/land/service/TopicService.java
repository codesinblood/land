package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.Topic;

/**
 * @author karthik.n
 * @since v1.0
 */

public interface TopicService {

    public Topic create(Topic topic);

    public List<Topic> readAll();
    
    public Topic update(Topic topic);

    public void delete(long id);

    public Topic read(long id);
    
    public List<Topic> findByCourseId(long courseId);
}
