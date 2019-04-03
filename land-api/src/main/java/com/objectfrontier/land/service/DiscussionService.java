package com.objectfrontier.land.service;

import java.util.List;

import com.objectfrontier.land.model.Discussion;

/**
 * @author gunasekaran.k
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Interface class that has the following methods for DiscussionService
 */
public interface DiscussionService {

    public Discussion create(Discussion discussion);

    public Discussion findById(long id);

    public List<Discussion> findAll();

    public void delete(long id);

    public Discussion update(Discussion updatedDiscussion);

    public List<Discussion> search(String searchInput, int pageNo, int offset);
}
