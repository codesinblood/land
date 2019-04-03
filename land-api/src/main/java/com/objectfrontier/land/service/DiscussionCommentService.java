package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.DiscussionComment;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Interface class that has the following methods for
 * DiscussionCommentService
 */
public interface DiscussionCommentService {

    public DiscussionComment create(DiscussionComment discussionComment);

    public DiscussionComment findById(long id);

    public List<DiscussionComment> findByDiscussionId(long id, int page , int discussionCommentCount);
    
    public List<DiscussionComment> findAll();

    public void delete(long id);

    public DiscussionComment update(DiscussionComment updatedDiscussionComment);

}
