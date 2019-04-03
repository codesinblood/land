package com.objectfrontier.land.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.PreConditionFailException;
import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.dto.CourseDTO;
import com.objectfrontier.land.dto.EvaluationDTO;
import com.objectfrontier.land.dto.EvaluationParamDTO;
import com.objectfrontier.land.dto.ExerciseDTO;
import com.objectfrontier.land.dto.OnlineTestDTO;
import com.objectfrontier.land.dto.ReferenceDTO;
import com.objectfrontier.land.dto.SubtopicDTO;
import com.objectfrontier.land.dto.SubtopicTagDTO;
import com.objectfrontier.land.dto.TopicDTO;
import com.objectfrontier.land.model.Course;
import com.objectfrontier.land.repository.CourseRepository;
import com.objectfrontier.land.service.CourseService;

/**
 * @author jayanth.subramanian
 * @author karthik.n
 * @since v1.0
 *
 *        Service class defining the CRUD operations for Course entity
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * create method validates the course properties invokes the save method to add
     * course to DB generates a mail to respective users after successfully creating
     * the course
     *
     * @param course that is ought to be created
     * @throws AppException when validation fails
     * @return course that is created
     */
    @Override
    public CourseDTO create(CourseDTO courseDTO) {

        validateNewCourse(courseDTO);
        Course course = modelMapper.map(courseDTO, Course.class);
        Course createdCourse = null; 
        // TODO assign author to users by invoking UserCourseRole API method
        // TODO check whether the above API call returns success response. If so,
        // generate a mail to all users by invoking Mail API
        createdCourse = courseRepository.save(course);
        return modelMapper.map(createdCourse, CourseDTO.class);
    }

    /**
     * validate new course method validates the course properties
     *
     * @param course that is ought to be validated
     * @throws AppException when 
     *           course name is null or too long 
     *           thumb nail is not found users list has too many users
     */
    private void validateNewCourse(CourseDTO courseDTO) {

        if (Objects.isNull(courseDTO.getName())) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (Objects.isNull(courseDTO.getResource())) {
            throw new PreConditionFailException(ErrorCode.RESOURCE_NOT_FOUND);
        }

        if (courseDTO.getName().length() > 40) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (courseDTO.getAuthors().size() > 10) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }
    }

    /* (non-Javadoc)
     * @see com.objectfrontier.land.service.CourseService#update(com.objectfrontier.land.model.Course)
     */
    @Override
    public CourseDTO update(CourseDTO courseDTO) {

        validate(courseDTO);
        Course createdCourse = null;
        Course course = modelMapper.map(courseDTO, Course.class);
        createdCourse = courseRepository.save(course);
        CourseDTO createdCourseDTO = modelMapper.map(createdCourse, CourseDTO.class);
        return createdCourseDTO;
    }

    /**
     * create method validates the course properties invokes the save method to add
     * course to DB (or) update method to create topic
     *
     * @param courseDTO that is ought to be updated
     * @throws AppException when validation fails
     * @return course that is updated
     */
    public CourseDTO upSert(CourseDTO courseDTO) {

        if (courseDTO.getTopics() != null && !courseDTO.getTopics().isEmpty()) {
            return courseServiceImpl.update(courseDTO);
        } else {
            return courseServiceImpl.create(courseDTO);
        }
    }

    /**
     * @param courseDTO
     */
    private void validate(CourseDTO courseDTO) {

        List<TopicDTO> topics = courseDTO.getTopics();
        if (topics.size() > 0 || topics != null) {
            for(TopicDTO topicDTO : topics) {
                validateTopic(topicDTO);
            }
        }
    }

    private void validateTopic(TopicDTO topicDTO) {

        if (Objects.isNull(topicDTO.getName())) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (topicDTO.getName().length() > 40) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (Objects.isNull(topicDTO.getDescription())) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (topicDTO.getDescription().length() > 100) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        if (topicDTO.getAuthors() == null || topicDTO.getAuthors().size() >= 10 || topicDTO.getAuthors().size() < 1) {
            throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
        }

        List<SubtopicDTO> subtopics = topicDTO.getSubTopics();
        if (subtopics != null && !subtopics.isEmpty()) {
            validateSubtopic(subtopics);
        }

        List<ExerciseDTO> exercises = topicDTO.getExercises();
        if (exercises != null && !exercises.isEmpty()) {
            validateExercise(exercises);
        }

        List<OnlineTestDTO> onlinetests = topicDTO.getOnlineTests();
        if (onlinetests != null && !onlinetests.isEmpty()) {
            validateOnlineTest(onlinetests);
        }

        List<ReferenceDTO> references = topicDTO.getReferences();
        if (references != null && !references.isEmpty()) {
            validateReference(references);
        }

        List<EvaluationDTO> evaluations = topicDTO.getEvaluations();
        if (evaluations != null && !evaluations.isEmpty()) {
            validateEvaluation(evaluations);
        }

        List<EvaluationParamDTO> evaluationParams = topicDTO.getEvaluationParams();
        if (evaluationParams != null && !evaluationParams.isEmpty()) {
            validateEvaluationParam(evaluationParams);
        }

    }

    /**
     * @param evaluationParams
     */
    private void validateEvaluationParam(List<EvaluationParamDTO> evaluationParams) {

        for(EvaluationParamDTO evaluationParam : evaluationParams) {

            if (Objects.isNull(evaluationParam.getDescription())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluationParam.getMarMark())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluationParam.getParam())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluationParam.getWeightage())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

        }
    }

    /**
     * @param evaluations
     */
    private void validateEvaluation(List<EvaluationDTO> evaluations) {

        for(EvaluationDTO evaluation : evaluations) {

            if (Objects.isNull(evaluation.getPassCriteria())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluation.getMaxAttempt())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluation.getFastrackRecapDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluation.getFastrackReviewDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluation.getFulltrackRecapDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(evaluation.getFulltrackReviewDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

        }
    }

    /**
     * @param references
     */
    private void validateReference(List<ReferenceDTO> references) {

        for(ReferenceDTO reference : references) {

            if (Objects.isNull(reference.getDescription())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (reference.getDescription().length() > 40) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(reference.getLink())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }
        }
    }

    /**
     * @param onlinetests
     */
    private void validateOnlineTest(List<OnlineTestDTO> onlinetests) {

        for(OnlineTestDTO onlineTest : onlinetests) {

            if (Objects.isNull(onlineTest.getDescription())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (onlineTest.getDescription().length() > 100) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(onlineTest.getFastrackMaxAttempt())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(onlineTest.getFastrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(onlineTest.getFulltrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(onlineTest.getFulltrackMaxAttempt())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

        }
    }

    /**
     * @param exercises
     */
    private void validateExercise(List<ExerciseDTO> exercises) {

        for(ExerciseDTO exercise : exercises) {

            if (Objects.isNull(exercise.getDescription())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (exercise.getDescription().length() > 100) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (exercise.getName().length() > 40) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getName())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getFastrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getFulltrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getRecapFastrack())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getRecapFulltrack())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getReviewFastrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(exercise.getReviewFulltrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }
        }
    }

    /**
     * @param subtopics
     */
    private void validateSubtopic(List<SubtopicDTO> subtopics) {

        for(SubtopicDTO subtopic : subtopics) {

            if (Objects.isNull(subtopic.getDescription())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (subtopic.getDescription().length() > 100) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (subtopic.getName().length() > 40) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(subtopic.getName())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(subtopic.getFulltrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (Objects.isNull(subtopic.getFastrackDuration())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            List<SubtopicTagDTO> subtopicTags = subtopic.getSubTopicTags();
            validateSubtopicTag(subtopicTags);
        }
    }

    /**
     * @param subtopicTags
     */
    private void validateSubtopicTag(List<SubtopicTagDTO> subtopicTags) {

        for(SubtopicTagDTO subtopicTag : subtopicTags) {

            if (Objects.isNull(subtopicTag.getName())) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }

            if (subtopicTag.getName().length() > 40) {
                throw new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);
            }
        }
    }

    /**
     * @param course id
     */
    @Override
    public CourseDTO read(long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course == null) {
            throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        return modelMapper.map(course, CourseDTO.class); 
    }

}