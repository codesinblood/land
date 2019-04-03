package com.objectfrontier.land.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Time;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.Evaluation;
import com.objectfrontier.land.repository.EvaluationRepository;
import com.objectfrontier.land.service.impl.EvaluationServiceImpl;

public class EvaluationServiceImplTest {


    @Mock
    private EvaluationRepository evaluationRepoMock;

    @InjectMocks
    private EvaluationServiceImpl evaluationServiceImplMock;
    private Evaluation actual;
    private Evaluation expected;
    private long id;

    @BeforeClass
    public void setup() {

        actual = new Evaluation();
        actual.setMaxAttempt(3);
        actual.setPassCriteria(50);
        actual.setFastrackRecapDuration(new Time(System.currentTimeMillis()));
        actual.setFastrackReviewDuration(new Time(System.currentTimeMillis()));
        actual.setFulltrackRecapDuration(new Time(System.currentTimeMillis()));
        actual.setFulltrackReviewDuration(new Time(System.currentTimeMillis()));
        
        expected = new Evaluation();
        expected.setMaxAttempt(3);
        expected.setPassCriteria(50);
        expected.setFastrackRecapDuration(new Time(System.currentTimeMillis()));
        expected.setFastrackReviewDuration(new Time(System.currentTimeMillis()));
        expected.setFulltrackRecapDuration(new Time(System.currentTimeMillis()));
        expected.setFulltrackReviewDuration(new Time(System.currentTimeMillis()));

        id = 1;
        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test Evaluation CRUD methods
     */
    @Test
    public void testCreatePositive() {

        when(evaluationRepoMock.save(actual)).thenReturn(expected);
        assertEquals((evaluationServiceImplMock.create(actual)).toString(), expected.toString());
    }

    @Test
    public void testReadPositive() {

        when(evaluationRepoMock.findById(id)).thenReturn(Optional.of(expected));
        assertEquals(evaluationServiceImplMock.read(id).toString(), expected.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        
        when(evaluationRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        evaluationServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(evaluationRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expected));
        when(evaluationRepoMock.save(Mockito.any(Evaluation.class))).thenReturn(expected);
        assertEquals(evaluationServiceImplMock.update(actual).toString(), expected.toString());
    }

    @Test()
    public void testDeletePostive() {
        doNothing().when(evaluationRepoMock).deleteById(Mockito.any(Long.class));
        evaluationServiceImplMock.delete(1);
    }
}
