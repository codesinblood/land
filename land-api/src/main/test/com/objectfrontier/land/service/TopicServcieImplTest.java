package com.objectfrontier.land.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.Evaluation;
import com.objectfrontier.land.model.EvaluationParam;
import com.objectfrontier.land.model.Exercise;
import com.objectfrontier.land.model.OnlineTest;
import com.objectfrontier.land.model.Reference;
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.model.Subtopic;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.repository.TopicRepository;
import com.objectfrontier.land.service.impl.TopicServiceImpl;

public class TopicServcieImplTest {

    @Mock
    private TopicRepository topicRepoMock;

    @InjectMocks
    private TopicServiceImpl topicServiceImplMock;
    private Topic expectedTopic;
    private Topic actualTopic; 
    private Resource resource;
    private long id;
    private List<Subtopic> subtopics;
    private Subtopic subtopic;
    private List<OnlineTest> onlineTests;
    private OnlineTest onlineTest;
    private List<Reference> references;
    private Reference reference;
    private List<Exercise> exercises;
    private Exercise exercise;
    private List<Evaluation> evaluations;
    private Evaluation evaluation;
    private List<EvaluationParam> evaluationParams;
    private EvaluationParam evaluationParam;

    @BeforeClass
    public void setup() {

        resource = new Resource();
        resource.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        resource.setType("image");
        resource.setName("Desert");

        subtopics = new ArrayList<>();
        subtopic = new Subtopic();
        subtopic.setDescription("basic java");
        subtopic.setFastrackDuration(new Time(System.currentTimeMillis()));
        subtopic.setFulltrackDuration(new Time(System.currentTimeMillis()));
        subtopic.setResource(resource);
        subtopics.add(subtopic); 

        onlineTests = new ArrayList<>();
        onlineTest = new OnlineTest();
        onlineTest.setDescription("Test 1");
        onlineTest.setPassPercentage(60.00);
        onlineTest.setFastrackDuration(new Time(System.currentTimeMillis()));
        onlineTest.setFulltrackDuration(new Time(System.currentTimeMillis()));
        onlineTest.setFastrackMaxAttempt(3);
        onlineTest.setFulltrackMaxAttempt(5);
        onlineTests.add(onlineTest);

        references = new ArrayList<>();
        reference = new Reference();
        reference.setDescription("reference for java");
        reference.setLink("D:/temp");
        references.add(reference);

        exercises = new ArrayList<>();
        exercise = new Exercise();
        exercise.setName("java exercise");
        exercise.setDescription("exercise for basic java");
        exercise.setResource(resource);
        exercise.setFastrackDuration(new Time(System.currentTimeMillis()));
        exercise.setFulltrackDuration(new Time(System.currentTimeMillis()));
        exercise.setReviewFastrackDuration(new Time(System.currentTimeMillis()));
        exercise.setReviewFulltrackDuration(new Time(System.currentTimeMillis()));
        exercise.setRecapFastrack(new Time(System.currentTimeMillis()));
        exercise.setRecapFulltrack(new Time(System.currentTimeMillis()));
        exercises.add(exercise);

        evaluations = new ArrayList<>();
        evaluation = new Evaluation();
        evaluation.setMaxAttempt(5);
        evaluation.setPassCriteria(60);
        evaluation.setFastrackRecapDuration(new Time(System.currentTimeMillis()));
        evaluation.setFastrackReviewDuration(new Time(System.currentTimeMillis()));
        evaluation.setFulltrackRecapDuration(new Time(System.currentTimeMillis()));
        evaluation.setFulltrackReviewDuration(new Time(System.currentTimeMillis()));
        evaluations.add(evaluation);

        evaluationParams = new ArrayList<>();
        evaluationParam = new EvaluationParam();
        evaluationParam.setParam("setting param");
        evaluationParam.setDescription("evaluation param");
        evaluationParam.setWeightage(80.00);
        evaluationParam.setMarMark(70);
        evaluationParams.add(evaluationParam);

        expectedTopic = new Topic();
        expectedTopic.setName("Java");
        expectedTopic.setDescription("Basics of java");
        expectedTopic.setDisplayOrder(1);
        expectedTopic.setSubTopics(subtopics);
        expectedTopic.setOnlineTests(onlineTests);
        expectedTopic.setReferences(references);
        expectedTopic.setExercises(exercises);
        expectedTopic.setEvaluations(evaluations);
        expectedTopic.setEvaluationParams(evaluationParams);

        actualTopic = new Topic();
        actualTopic.setId(1);
        actualTopic.setName("Java");
        actualTopic.setDescription("Basics of java");
        actualTopic.setDisplayOrder(1);
        actualTopic.setSubTopics(subtopics);
        actualTopic.setOnlineTests(onlineTests);
        actualTopic.setReferences(references);
        actualTopic.setExercises(exercises);
        actualTopic.setEvaluations(evaluations);
        actualTopic.setEvaluationParams(evaluationParams);

        id = 1;
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePositive() {

        when(topicRepoMock.save(actualTopic)).thenReturn(expectedTopic);
        assertEquals((topicServiceImplMock.create(actualTopic)).toString(), expectedTopic.toString());
    }


    @Test
    public void testReadPositive() {

        when(topicRepoMock.findById(id)).thenReturn(Optional.of(expectedTopic));
        assertEquals(topicServiceImplMock.read(id).toString(), expectedTopic.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {

        when(topicRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        topicServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(topicRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedTopic));
        when(topicRepoMock.save(Mockito.any(Topic.class))).thenReturn(expectedTopic);
        assertEquals(topicServiceImplMock.update(actualTopic).toString(), expectedTopic.toString());
    }

    @Test
    public void testDeletePostive() {

        doNothing().when(topicRepoMock).deleteById(Mockito.any(Long.class));
        topicServiceImplMock.delete(1);
    }
}
