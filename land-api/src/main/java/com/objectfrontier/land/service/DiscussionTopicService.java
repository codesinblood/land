package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @author NarayananM
 * @since v1.0
 * <p>
 * Interface class that has the following methods for
 * DiscussionTopicLinkService
 */
public interface DiscussionTopicService {

    public DiscussionTopic create(DiscussionTopic discussionTopicLink);

    public DiscussionTopic findById(long id);

    public List<DiscussionTopic> findAll();

    public void delete(long id);

    public DiscussionTopic update(DiscussionTopic updatedDiscussionComment);
    
    public List<DiscussionTopic> findByTopicId(Long topicId, int pageNo, int discussionCount);
    
    public List<DiscussionTopic> findByListOfTopicId(List<Topic> topic, int pageNo, int discussionCount);

    public List<DiscussionTopic> createAll(List<DiscussionTopic> discussionTopicLinks);
}
