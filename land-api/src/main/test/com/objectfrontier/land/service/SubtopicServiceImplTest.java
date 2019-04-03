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
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.model.Subtopic;
import com.objectfrontier.land.repository.SubtopicRepository;
import com.objectfrontier.land.service.impl.SubtopicServiceImpl;

public class SubtopicServiceImplTest {

    @Mock
    private SubtopicRepository subtopicRepoMock;

    @InjectMocks
    private SubtopicServiceImpl subtopicServiceImplMock;
    private Resource resource;
    private Subtopic expectedSubtopic;
    private Subtopic actualSubtopic;
    private long id;

    @BeforeClass
    public void setup() {

        resource = new Resource();
        resource.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        resource.setType("image");
        resource.setName("Desert");

        actualSubtopic = new Subtopic();
        actualSubtopic.setDescription("basic java");
        actualSubtopic.setFastrackDuration(new Time(System.currentTimeMillis()));
        actualSubtopic.setFulltrackDuration(new Time(System.currentTimeMillis()));
        actualSubtopic.setResource(resource);

        expectedSubtopic = new Subtopic();
        expectedSubtopic.setDescription("basic java");
        expectedSubtopic.setFastrackDuration(new Time(System.currentTimeMillis()));
        expectedSubtopic.setFulltrackDuration(new Time(System.currentTimeMillis()));

        id = 1;
        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test Subtopic CRUD methods
     */
    @Test
    public void testCreatePositive() {

        when(subtopicRepoMock.save(actualSubtopic)).thenReturn(expectedSubtopic);
        assertEquals((subtopicServiceImplMock.create(actualSubtopic)).toString(), expectedSubtopic.toString());
    }

    @Test
    public void testReadPositive() {

        when(subtopicRepoMock.findById(id)).thenReturn(Optional.of(expectedSubtopic));
        assertEquals(subtopicServiceImplMock.read(id).toString(), expectedSubtopic.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(subtopicRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        subtopicServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(subtopicRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedSubtopic));
        when(subtopicRepoMock.save(Mockito.any(Subtopic.class))).thenReturn(expectedSubtopic);
        assertEquals(subtopicServiceImplMock.update(actualSubtopic).toString(), expectedSubtopic.toString());
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(subtopicRepoMock).deleteById(Mockito.any(Long.class));
        subtopicServiceImplMock.delete(1);
    }
}
