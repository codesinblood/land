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
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionFollower;
import com.objectfrontier.land.repository.DiscussionFollowerRepository;
import com.objectfrontier.land.service.DiscussionFollowerService;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for DiscussionFollower
 * entity
 */
@Service
public class DiscussionFollowerServiceImpl implements DiscussionFollowerService {

    @Autowired
    private DiscussionFollowerRepository discussionFollowerRepository;

    /**
     * Service class that created a DiscussionFollower record by using the
     * discussionFollower object.
     */
    @Override
    public DiscussionFollower create(DiscussionFollower discussionFollower) {
        return discussionFollowerRepository.save(discussionFollower);
    }

    /**
     * Service class that finds a DiscussionFollower record by using the
     * discussionFollower id.
     */
    @Override
    public DiscussionFollower findById(long id) {

        Optional<DiscussionFollower> discussionFollower = discussionFollowerRepository.findById(id);
        return discussionFollower.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    /**
     * Service class that finds DiscussionFollower record by using the discussion
     * id.
     */
    @Override
    public List<DiscussionFollower> findByDiscussionId(long id) {
        return discussionFollowerRepository.findByDiscussionId(id);
    }

    /**
     * Service class that finds DiscussionFollower record by using the discussion id
     * and user id.
     */
    @Override
    public DiscussionFollower findByUserIdAndDiscussionId(long userId, long discussionId) {
        return discussionFollowerRepository.findByFollowerIdAndDiscussionId(userId, discussionId);
    }

    /**
     * Service class that find all the DiscussionFollower record by using the list
     * of object.
     */
    @Override
    public List<DiscussionFollower> findAll() {
        return (List<DiscussionFollower>) discussionFollowerRepository.findAll();
    }

    /**
     * Service class that delete a DiscussionFollower record by using the
     * discussionFollower id.
     */
    @Override
    public void delete(long id) {
        discussionFollowerRepository.deleteById(id);
    }

    /**
     * Service class that update a DiscussionFollower record by using the
     * discussionFollower object.
     */
    @Override
    public DiscussionFollower update(DiscussionFollower updatedDiscussionFollower) {
        return discussionFollowerRepository.save(updatedDiscussionFollower);
    }
    
    public List<DiscussionFollower> findByDiscussionsAndUserId(List<Discussion> discussions, long userId, int pageNo, int discussionCount) {
        
        List<Long> discussionIds = new ArrayList<>();
        discussions.forEach(discussion -> discussionIds.add(discussion.getId()));
        Pageable requestedPage = PageRequest.of(pageNo, discussionCount);
        List<DiscussionFollower> discussionsFollowed = discussionFollowerRepository.findByDiscussionIdInAndFollowerId(discussionIds, userId, requestedPage);
        return discussionsFollowed;
    }
}
