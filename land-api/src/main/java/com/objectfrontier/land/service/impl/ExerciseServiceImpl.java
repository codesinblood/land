package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Exercise;
import com.objectfrontier.land.repository.ExerciseRepository;
import com.objectfrontier.land.service.ExerciseService;

/**
 * @author venkatesh.k
 * @since v1.0
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise create(Exercise exercise) {

        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise update(Exercise exercise) {

        return exerciseRepository.save(exercise);
    }

    @Override
    public void delete(long id) {

        exerciseRepository.deleteById(id);
    }

    @Override
    public Exercise read(long id) {

        Optional<Exercise> exercise = exerciseRepository.findById(id);
        return exercise.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
