package com.objectfrontier.land.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.DiscussionTopic;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * This interface declares the DB operation for DiscussionTopicLink entity
 */
@Repository
public interface DiscussionTopicRepository extends PagingAndSortingRepository<DiscussionTopic, Long> {
    
    public List<DiscussionTopic> findByTopicId(long topicId, Pageable page);
    
    public List<DiscussionTopic> findByTopicIdIn(List<Long> topicId, Pageable page);
    
    public List<DiscussionTopic> findByIsPrimaryTrueAndTopicIdIn(List<Long> topicId);
}
