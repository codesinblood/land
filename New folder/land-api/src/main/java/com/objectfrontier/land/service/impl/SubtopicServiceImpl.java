package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Subtopic;
import com.objectfrontier.land.repository.SubtopicRepository;
import com.objectfrontier.land.service.SubtopicService;

/**
 * @author sugandapriya.l
 * @since v1.0
 */
@Service
public class SubtopicServiceImpl implements SubtopicService {

    @Autowired
    private SubtopicRepository subtopicRepository;

    /* 
     * implementations yet to be completed 
     */
    @Override
    public Subtopic create(Subtopic subtopic) {
        return subtopicRepository.save(subtopic);
    }

    @Override
    public Subtopic update(Subtopic subtopic) {
        return subtopicRepository.save(subtopic);
    }

    @Override
    public void delete(long id) {
        subtopicRepository.deleteById(id);
    }

    @Override
    public Subtopic read(long id) {
        Optional<Subtopic> subTopic = subtopicRepository.findById(id);
        return subTopic.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
