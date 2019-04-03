package com.objectfrontier.land.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.CommentNotification;
import com.objectfrontier.land.repository.CommentNotificationRepository;
import com.objectfrontier.land.service.CommentNotificationService;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for CommentNotification
 * entity
 */
@Service
public class CommentNotificationServiceImpl implements CommentNotificationService {

    @Autowired
    private CommentNotificationRepository commentNotificationRepository;

    /**
     * Service class that created a CommentNotification record by using the
     * commentNotification object.
     */
    @Override
    public CommentNotification create(CommentNotification commentNotification) {
        return commentNotificationRepository.save(commentNotification);
    }

    /**
     * Service class that created all the CommentNotification record by using the
     * list of object.
     */
    @Override
    public List<CommentNotification> createAll(List<CommentNotification> commentNotifications) {
        return (List<CommentNotification>) commentNotificationRepository.saveAll(commentNotifications);
    }

    /**
     * Service class that finds a CommentNotification record by using the
     * commentNotifications id.
     */
    @Override
    public CommentNotification findById(long id) {

        Optional<CommentNotification> commentNotification = commentNotificationRepository.findById(id);
        return commentNotification.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    /**
     * Service class that find all the CommentNotification record by using the list
     * of object.
     */
    @Override
    public List<CommentNotification> findAll() {
        return (List<CommentNotification>) commentNotificationRepository.findAll();
    }

    /**
     * Service class that delete a CommentNotification record by using the
     * commentNotifications id.
     */
    @Override
    public void delete(long id) {
        commentNotificationRepository.deleteById(id);
    }

    /**
     * Service class that update a CommentNotification record by using the
     * commentNotifications object.
     */
    @Override
    public CommentNotification update(CommentNotification updatedDiscussionComment) {
        return commentNotificationRepository.save(updatedDiscussionComment);
    }
}
