package com.objectfrontier.land.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.repository.DiscussionRepository;
import com.objectfrontier.land.service.DiscussionService;

/**
 * @author gunasekaran.k
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for Discussion entity
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    /**
     * Service class that created a Discussion record by using the discussion
     * object.
     */
    @Override
    public Discussion create(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    /**
     * Service class that finds a Discussion record by using the discussion id.
     */
    @Override
    public Discussion findById(long id) {

        Optional<Discussion> discussion = discussionRepository.findById(id);
        return discussion.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public List<Discussion> sortByRecentlyCommented(List<Discussion> discussions, int pageNumber, int discussionCount) {
        
        List<Long> discussionIds = new ArrayList<>();
        discussions.forEach(discussion -> discussionIds.add(discussion.getId()));
        Pageable requestedPage = PageRequest.of(pageNumber, discussionCount);
        List<Discussion> sortedDiscussions = discussionRepository.findByIdIn(discussionIds, requestedPage);
        return sortedDiscussions;
    }
    /**
     * Service class that find all the Discussion record by using the list of
     * object.
     */
    @Override
    public List<Discussion> findAll() {
        return (List<Discussion>) discussionRepository.findAll();
    }

    /**
     * Service class that delete a Discussion record by using the discussion id.
     */
    @Override
    public void delete(long id) {
        discussionRepository.deleteById(id);
    }

    /**
     * Service class that update a Discussion record by using the discussion object.
     */
    @Override
    public Discussion update(Discussion updatedDiscussion) {
        return discussionRepository.save(updatedDiscussion);
    }

    /**
     * Service class that returns a list of Discussion that has searchInput in title or description
     */
    @Override
    public List<Discussion> search(String searchInput, int pageNo, int offset) {
        Pageable requestedPage = PageRequest.of(pageNo, offset);
        return discussionRepository.findByTitleContainingOrDescriptionContainingOrderByTitle(searchInput, searchInput, requestedPage);
    }

    public List<Discussion> sortedByRecent(List<Discussion> discussions, int pageNumber, int discussionCount) {
        
        List<Long> discussionIds = new ArrayList<>();
        discussions.forEach(discussion -> discussionIds.add(discussion.getId()));
        Pageable requestedPage = PageRequest.of(pageNumber, discussionCount, Sort.by("createdDate").descending());
        List<Discussion> sortedDiscussions = discussionRepository.findByIdIn(discussionIds, requestedPage);
        return sortedDiscussions;
    }
}
