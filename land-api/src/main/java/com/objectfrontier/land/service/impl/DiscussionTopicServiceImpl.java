package com.objectfrontier.land.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.repository.DiscussionTopicRepository;
import com.objectfrontier.land.service.DiscussionTopicService;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @author NarayananM
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for DiscussionTopicLink
 * entity
 */
@Service
public class DiscussionTopicServiceImpl implements DiscussionTopicService {

    @Autowired
    private DiscussionTopicRepository discussionTopicRepository;

    /**
     * Service class that created a DiscussionTopicLink record by using the
     * discussionTopicLink object.
     */
    @Override
    public DiscussionTopic create(DiscussionTopic discussionTopicLink) {
        return discussionTopicRepository.save(discussionTopicLink);
    }

    /**
     * Service class that created all the DiscussionTopicLink record by using the
     * list of object.
     */
    @Override
    public List<DiscussionTopic> createAll(List<DiscussionTopic> discussionTopicLinks) {
        return (List<DiscussionTopic>) discussionTopicRepository.saveAll(discussionTopicLinks);
    }

    /**
     * Service class that finds a DiscussionTopicLink record by using the
     * discussionTopicLink id.
     */
    @Override
    public DiscussionTopic findById(long id) {

        Optional<DiscussionTopic> discussionTopicLink = discussionTopicRepository.findById(id);
        return discussionTopicLink.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    /**
     * Service class that find all the DiscussionTopicLink record by using the list
     * of object.
     */
    @Override
    public List<DiscussionTopic> findAll() {
        return (List<DiscussionTopic>) discussionTopicRepository.findAll();
    }

    /**
     * Service class that delete a DiscussionTopicLink record by using the
     * discussionTopicLink id.
     */
    @Override
    public void delete(long id) {
        discussionTopicRepository.deleteById(id);
    }

    /**
     * Service class that update a DiscussionTopicLink record by using the
     * discussionTopicLink id.
     */
    @Override
    public DiscussionTopic update(DiscussionTopic updatedDiscussionTopicLink) {
        return discussionTopicRepository.save(updatedDiscussionTopicLink);
    }

    @Override
    public List<DiscussionTopic> findByTopicId(Long topicId, int pageNo, int discussionCount) {

        Pageable requestedPage = PageRequest.of(pageNo, discussionCount);
        List<DiscussionTopic> discussionsInTopic = discussionTopicRepository.findByTopicId(topicId, requestedPage);
        return discussionsInTopic;
    }

    @Override
    public List<DiscussionTopic> findByListOfTopicId(List<Topic> topics, int pageNo, int discussionCount) {

        List<Long> topicIdList = new ArrayList<>();
        topics.forEach(topic -> topicIdList.add(topic.getId()));
        Pageable requestedPage = PageRequest.of(pageNo, discussionCount);
        List<DiscussionTopic> discussionsInTopic = discussionTopicRepository.findByTopicIdIn(topicIdList, requestedPage);
        return discussionsInTopic;
    }
    
    public List<DiscussionTopic> findByListOfPrimaryTopic(List<Topic> topics) {
        
        List<Long> topicIdList = new ArrayList<>();
        topics.forEach(topic -> topicIdList.add(topic.getId()));
        List<DiscussionTopic> discussionsInTopic = discussionTopicRepository.findByIsPrimaryTrueAndTopicIdIn(topicIdList);
        return discussionsInTopic;
    }
}
