package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.DiscussionFollower;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * Interface class that has the following methods for
 * DiscussionFollowerService
 */
public interface DiscussionFollowerService {

    public DiscussionFollower create(DiscussionFollower discussionFollower);

    public DiscussionFollower findById(long id);

    public List<DiscussionFollower> findByDiscussionId(long id);

    public DiscussionFollower findByUserIdAndDiscussionId(long userId, long discussionId);

    public List<DiscussionFollower> findAll();

    public void delete(long id);

    public DiscussionFollower update(DiscussionFollower updatedDiscussionFollower);
}
