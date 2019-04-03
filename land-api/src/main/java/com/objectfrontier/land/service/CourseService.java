package com.objectfrontier.land.service;

import com.objectfrontier.land.dto.CourseDTO;

/**
 * @author jayanth.subramanian
 * @author karthik.n
 * @since v1.0
 *
 * Interface declaring CRUD methods for Course entity
 */
public interface CourseService {

    public CourseDTO create(CourseDTO courseDTO);

    public CourseDTO update(CourseDTO courseDTO);

    public CourseDTO read(long id);
}
