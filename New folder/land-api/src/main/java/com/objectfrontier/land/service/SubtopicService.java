package com.objectfrontier.land.service;

import com.objectfrontier.land.model.Subtopic;

/**
 * @author sugandapriya.l
 * @since Feb 28, 2019
 * 
 * Interface class that has the methods for SubtopicService
 */

public interface SubtopicService {

    public Subtopic create(Subtopic subtopic);

    public Subtopic update(Subtopic subtopic);

    public void delete(long id);

    public Subtopic read(long id);

}
