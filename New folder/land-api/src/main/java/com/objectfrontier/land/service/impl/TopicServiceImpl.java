package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.repository.TopicRepository;
import com.objectfrontier.land.service.TopicService;

/**
 * @author karthik.n
 * @since v1.0
 * Service class defines the CRUD operations for Topic entity
 */

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    /**
     * Service class that created a topic record by using the topic object. 
     */
    @Override
    public Topic create(Topic topic) {

        Topic createdTopic = topicRepository.save(topic);
        return createdTopic;
    }

    /**
     * Implementation yet to be completed. 
     */
    @Override
    public Topic update(Topic topic) {
        return topicRepository.save(topic);
    }

    
    /**
     * Implementation yet to be completed. 
     */
    @Override
    public void delete(long id) {
        topicRepository.deleteById(id);
    }

    /**
     * Implementation yet to be completed. 
     */
    @Override
    public Topic read(long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
