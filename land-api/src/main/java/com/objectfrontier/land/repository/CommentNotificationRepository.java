package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.CommentNotification;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * This interface declares the DB operation for CommentNotification entity
 */
@Repository
public interface CommentNotificationRepository extends CrudRepository<CommentNotification, Long> {

}
