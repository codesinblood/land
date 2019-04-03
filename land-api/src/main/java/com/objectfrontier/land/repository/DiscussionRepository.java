package com.objectfrontier.land.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Discussion;

/**
 * @author gunasekaran.k
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * This interface declares the DB operation for Discussion entity
 */
@Repository
public interface DiscussionRepository extends CrudRepository<Discussion, Long> {

//    public List<Discussion> findByIdInOrderByField(List<Long> discussionIds, List<Long> ids, Pageable page);
    public List<Discussion> findByIdIn(List<Long> discussionIds, Pageable page);

    List<Discussion> findByTitleContainingOrDescriptionContainingOrderByTitle(String searchTitleInput, String searchDescriptionInput, Pageable page);
}
