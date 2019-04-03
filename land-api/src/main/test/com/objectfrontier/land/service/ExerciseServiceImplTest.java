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
import com.objectfrontier.land.model.Exercise;
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.repository.ExerciseRepository;
import com.objectfrontier.land.service.impl.ExerciseServiceImpl;

public class ExerciseServiceImplTest {


    @Mock
    private ExerciseRepository exerciseRepoMock;

    @InjectMocks
    private ExerciseServiceImpl exerciseServiceImplMock;
    private Resource resource;
    private Exercise actual;
    private Exercise expected;
    private long id;

    @BeforeClass
    public void setup() {

        resource = new Resource();
        resource.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        resource.setType("image");
        resource.setName("Desert");

        actual = new Exercise();
        actual.setName("exercise name");
        actual.setDescription("basic java exercises");
        actual.setFastrackDuration(new Time(System.currentTimeMillis()));
        actual.setFulltrackDuration(new Time(System.currentTimeMillis()));
        actual.setReviewFastrackDuration(new Time(System.currentTimeMillis()));
        actual.setReviewFulltrackDuration(new Time(System.currentTimeMillis()));
        actual.setRecapFulltrack(new Time(System.currentTimeMillis()));
        actual.setRecapFastrack(new Time(System.currentTimeMillis()));
        actual.setResource(resource);

        expected = new Exercise();
        expected.setName("exercise name");
        expected.setDescription("basic java exercises");
        expected.setFastrackDuration(new Time(System.currentTimeMillis()));
        expected.setFulltrackDuration(new Time(System.currentTimeMillis()));
        expected.setReviewFastrackDuration(new Time(System.currentTimeMillis()));
        expected.setReviewFulltrackDuration(new Time(System.currentTimeMillis()));
        expected.setRecapFulltrack(new Time(System.currentTimeMillis()));
        expected.setRecapFastrack(new Time(System.currentTimeMillis()));
        expected.setResource(resource);

        id = 1;
        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test Exercise CRUD methods
     */
    @Test
    public void testCreatePositive() {

        when(exerciseRepoMock.save(actual)).thenReturn(expected);
        assertEquals((exerciseServiceImplMock.create(actual)).toString(), expected.toString());
    }

    @Test
    public void testReadPositive() {

        when(exerciseRepoMock.findById(id)).thenReturn(Optional.of(expected));
        assertEquals(exerciseServiceImplMock.read(id).toString(), expected.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        
        when(exerciseRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        exerciseServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(exerciseRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expected));
        when(exerciseRepoMock.save(Mockito.any(Exercise.class))).thenReturn(expected);
        assertEquals(exerciseServiceImplMock.update(actual).toString(), expected.toString());
    }

    @Test()
    public void testDeletePostive() {
        doNothing().when(exerciseRepoMock).deleteById(Mockito.any(Long.class));
        exerciseServiceImplMock.delete(1);
    }
}
