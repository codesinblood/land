package com.objectfrontier.land.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.DiscussionFollower;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * This interface declares the DB operation for DiscussionFollower entity
 */
@Repository
public interface DiscussionFollowerRepository extends PagingAndSortingRepository<DiscussionFollower, Long> {

    List<DiscussionFollower> findByDiscussionId(long discussionId);

    DiscussionFollower findByFollowerIdAndDiscussionId(long followerId, long discussionId);
    
    public List<DiscussionFollower> findByDiscussionIdInAndFollowerId(List<Long> discussionId, long followerId, Pageable page);
}
