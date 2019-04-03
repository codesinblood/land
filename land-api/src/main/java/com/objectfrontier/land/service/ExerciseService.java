package com.objectfrontier.land.service;

import com.objectfrontier.land.model.Exercise;

/**
 * @author venkatesh.k
 * @since v1.0
 */

public interface ExerciseService {

    public Exercise create(Exercise exercise);

    public Exercise update(Exercise exercise);

    public void delete(long id);

    public Exercise read(long id);
}
