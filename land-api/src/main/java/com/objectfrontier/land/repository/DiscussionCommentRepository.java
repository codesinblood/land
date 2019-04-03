package com.objectfrontier.land.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.DiscussionComment;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * This interface declares the DB operation for DiscussionComment entity
 */
@Repository
public interface DiscussionCommentRepository extends PagingAndSortingRepository<DiscussionComment, Long> {

    List<DiscussionComment> findByDiscussionId(long discussionId, Pageable page);
    List<DiscussionComment> findByDiscussionIdIn(List<Long> discussionId, Pageable page);
    List<DiscussionComment> findByDiscussionIdInOrderByCreatedDateDesc(List<Long> discussionId);
}
