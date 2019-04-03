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
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.repository.DiscussionCommentRepository;
import com.objectfrontier.land.service.DiscussionCommentService;

/**
 * @author gunasekaran.k
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for DiscussionComment entity
 */
@Service
public class DiscussionCommentServiceImpl implements DiscussionCommentService {

    @Autowired
    private DiscussionCommentRepository discussionCommentRepository;

    /**
     * Service class that created a DiscussionComment record by using the
     * discussionComment object.
     */
    @Override
    public DiscussionComment create(DiscussionComment discussionComment) {
        return discussionCommentRepository.save(discussionComment);
    }

    /**
     * Service class that finds a DiscussionComment record by using the
     * discussionComment id.
     */
    @Override
    public DiscussionComment findById(long id) {

        Optional<DiscussionComment> discussionComment = discussionCommentRepository.findById(id);
        return discussionComment.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    /**
     * Service class that finds a DiscussionComment record by using the discussion
     * id.
     */
    @Override
    public List<DiscussionComment> findByDiscussionId(long id, int pageNo, int discussionCommentCount) {
        
        Pageable requestedPage = PageRequest.of(pageNo, discussionCommentCount);
        return discussionCommentRepository.findByDiscussionId(id, requestedPage);
    }

    /**
     * Service class that find all the DiscussionComment record by using the list of
     * object.
     */
    @Override
    public List<DiscussionComment> findAll() {
        return (List<DiscussionComment>) discussionCommentRepository.findAll();
    }

    /**
     * Service class that delete a DiscussionComment record by using the
     * discussionComment id.
     */
    @Override
    public void delete(long id) {
        discussionCommentRepository.deleteById(id);
    }

    /**
     * Service class that update a DiscussionComment record by using the
     * discussionComment object.
     */
    @Override
    public DiscussionComment update(DiscussionComment updatedDiscussionComment) {
        return discussionCommentRepository.save(updatedDiscussionComment);
    }

    public List<DiscussionComment> findByDiscussionIds(List<Discussion> discussions, int pageNumber, int discussionCount) {
        List<Long> discussionIds = new ArrayList<>();
        discussions.forEach(discussion -> discussionIds.add(discussion.getId()));
        Pageable requestedPage = PageRequest.of(pageNumber, discussionCount, Sort.by("createdDate").descending());
        return discussionCommentRepository.findByDiscussionIdIn(discussionIds, requestedPage);
    }
    
    public List<DiscussionComment> findByDiscussionIdsOrderByCreatedDate(List<Discussion> discussions) {
        List<Long> discussionIds = new ArrayList<>();
        discussions.forEach(discussion -> discussionIds.add(discussion.getId()));
        return discussionCommentRepository.findByDiscussionIdInOrderByCreatedDateDesc(discussionIds);
    }
}
