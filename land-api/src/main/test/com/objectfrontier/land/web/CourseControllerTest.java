package com.objectfrontier.land.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectfrontier.land.model.Course;
import com.objectfrontier.land.model.Resource;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.service.impl.CourseServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jayanth.subramanian
 * @since Feb 19, 2019
 * <p>
 * TestCases to test Course Controller methods
 */
public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseServiceImpl courseServiceImplMock;

    @InjectMocks
    private CourseController courseController;

    /*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    /*
     * Positive test case for create method
     */
    @Test(dataProvider = "dpCreatePositive")
    public void testCreatePositive(Course actual, Course expected) throws Exception {

        Mockito.when(courseServiceImplMock.create(Mockito.any(Course.class))).thenReturn(expected);

        mockMvc.perform(post("/api/v1/course").contentType(MediaType.APPLICATION_JSON).content(asJsonString(actual)))
                .andExpect(status().isCreated());

        verify(courseServiceImplMock, times(1)).create(Mockito.any(Course.class));
        verifyNoMoreInteractions(courseServiceImplMock);
    }

    @DataProvider
    Object[][] dpCreatePositive() throws IOException {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("Alice");
        user.setEmail("alice@google.com");
        user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        users.add(user);

        Resource resource = new Resource();
        resource.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        resource.setType("image");

        Course actual = new Course();
        actual.setName("Java");
        actual.setUsers(users);
        actual.setSelfAssignable(false);
        actual.setResource(resource);

        Course expected = new Course();
        expected.setName("Java");
        expected.setUsers(users);
        expected.setSelfAssignable(false);
        actual.setResource(resource);

        return new Object[][]{{actual, expected}};
    }
}
