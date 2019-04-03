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
import com.objectfrontier.land.model.OnlineTest;
import com.objectfrontier.land.repository.OnlineTestRepository;
import com.objectfrontier.land.service.impl.OnlineTestServiceImpl;

public class OnlineTestServiceImplTest {

    @Mock
    private OnlineTestRepository onlineTestRepoMock;

    @InjectMocks
    private OnlineTestServiceImpl onlineTestServiceImplMock;
    private OnlineTest actual;
    private OnlineTest expected;
    private long id;

    @BeforeClass
    public void setup() {
    	
        actual = new OnlineTest();
        actual.setDescription("online test");
        actual.setPassPercentage(60);
        actual.setFastrackDuration(new Time(System.currentTimeMillis()));
        actual.setFulltrackDuration(new Time(System.currentTimeMillis()));
        actual.setFastrackMaxAttempt(6);
        actual.setFulltrackMaxAttempt(6);
        
        expected = new OnlineTest();
        expected.setDescription("online test");
        expected.setPassPercentage(60);
        expected.setFastrackDuration(new Time(System.currentTimeMillis()));
        expected.setFulltrackDuration(new Time(System.currentTimeMillis()));
        expected.setFastrackMaxAttempt(6);
        expected.setFulltrackMaxAttempt(6);

        id = 1;
        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test OnlineTest CRUD methods
     */
    @Test
    public void testCreatePositive() {

        when(onlineTestRepoMock.save(actual)).thenReturn(expected);
        assertEquals((onlineTestServiceImplMock.create(actual)).toString(), expected.toString());
    }


    @Test
    public void testReadPositive() {

        when(onlineTestRepoMock.findById(id)).thenReturn(Optional.of(expected));
        assertEquals(onlineTestServiceImplMock.read(id).toString(), expected.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        
        when(onlineTestRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        onlineTestServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(onlineTestRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expected));
        when(onlineTestRepoMock.save(Mockito.any(OnlineTest.class))).thenReturn(expected);
        assertEquals(onlineTestServiceImplMock.update(actual).toString(), expected.toString());
    }

    @Test()
    public void testDeletePostive() {
        doNothing().when(onlineTestRepoMock).deleteById(Mockito.any(Long.class));
        onlineTestServiceImplMock.delete(1);
    }
}
