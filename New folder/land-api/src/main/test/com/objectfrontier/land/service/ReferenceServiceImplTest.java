package com.objectfrontier.land.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.Reference;
import com.objectfrontier.land.repository.ReferenceRepository;
import com.objectfrontier.land.service.impl.ReferenceServiceImpl;

public class ReferenceServiceImplTest {

    @Mock
    private ReferenceRepository referenceRepoMock;

    @InjectMocks
    private ReferenceServiceImpl referenceServiceImplMock;
    private Reference actual;
    private Reference expected;
    private long id;

    @BeforeClass
    public void setup() {

        actual = new Reference();
        actual.setDescription("reference");
        actual.setLink("D:/temp");

        expected = new Reference();
        expected.setDescription("online test");
        expected.setLink("D:/temp");

        id = 1;
        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test Reference CRUD methods
     */
    @Test
    public void testCreatePositive() {

        when(referenceRepoMock.save(actual)).thenReturn(expected);
        assertEquals((referenceServiceImplMock.create(actual)).toString(), expected.toString());
    }

    @Test
    public void testReadPositive() {

        when(referenceRepoMock.findById(id)).thenReturn(Optional.of(expected));
        assertEquals(referenceServiceImplMock.read(id).toString(), expected.toString());
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        
        when(referenceRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        referenceServiceImplMock.read(1);
    }

    @Test
    public void testUpdatePositive() {

        when(referenceRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expected));
        when(referenceRepoMock.save(Mockito.any(Reference.class))).thenReturn(expected);
        assertEquals(referenceServiceImplMock.update(actual).toString(), expected.toString());
    }

    @Test()
    public void testDeletePostive() {
        doNothing().when(referenceRepoMock).deleteById(Mockito.any(Long.class));
        referenceServiceImplMock.delete(1);
    }
}
