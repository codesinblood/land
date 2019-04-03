/**
 *
 */
package com.objectfrontier.land.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.objectfrontier.land.dto.CourseDTO;
import com.objectfrontier.land.model.Course;
import com.objectfrontier.land.service.impl.CourseServiceImpl;
import com.objectfrontier.land.service.impl.EvaluationServiceImpl;
import com.objectfrontier.land.service.impl.ExerciseServiceImpl;
import com.objectfrontier.land.service.impl.OnlineTestServiceImpl;
import com.objectfrontier.land.service.impl.ReferenceServiceImpl;
import com.objectfrontier.land.service.impl.SubtopicServiceImpl;
import com.objectfrontier.land.service.impl.TopicServiceImpl;

/**
 * @author jayanth.subramanian
 * @author karthik.n
 * @since v1.0
 *
 *        Controller class for managing request and response for course service methods
 */
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;


    @Autowired
    TopicServiceImpl topicService;

    @Autowired
    SubtopicServiceImpl subtopicService;

    @Autowired
    OnlineTestServiceImpl onlineTestService;

    @Autowired
    EvaluationServiceImpl evaluationService;

    @Autowired
    ExerciseServiceImpl exerciseService;

    @Autowired
    ReferenceServiceImpl referenceService;

    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<CourseDTO>(courseService.create(courseDTO), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<CourseDTO> upSert(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<CourseDTO>(courseService.upSert(courseDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> read(@PathVariable long id) {
        return new ResponseEntity<>(courseService.read(id), HttpStatus.CREATED);
    }

}
