package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.CommentNotification;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Interface class that has the following methods for
 * CommentNotificationService
 */
public interface CommentNotificationService {

    public CommentNotification create(CommentNotification commentNotification);

    public List<CommentNotification> createAll(List<CommentNotification> commentNotification);

    public CommentNotification findById(long id);

    public List<CommentNotification> findAll();

    public void delete(long id);

    public CommentNotification update(CommentNotification updatedDiscussionComment);

}
