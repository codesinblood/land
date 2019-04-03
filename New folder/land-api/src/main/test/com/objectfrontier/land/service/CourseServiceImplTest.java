/**
 *
 */
package com.objectfrontier.land.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.PreConditionFailException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Course;
import com.objectfrontier.land.model.Media;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.CourseRepository;
import com.objectfrontier.land.service.impl.CourseServiceImpl;

/**
 * @author jayanth.subramanian
 * @since v1.0
 *
 *        TestCases to test Course Service methods
 */
public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepoMock;

    @InjectMocks
    private CourseServiceImpl courseServiceImplMock;

    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    /*
     * Positive Test Case to test Course Create method
     */
    public void testCreatePositive(Course actual, Course expected) {

        when(courseRepoMock.save(actual)).thenReturn(expected);
        assertEquals((courseServiceImplMock.create(actual)).toString(), expected.toString());
    }

    @DataProvider
    Object[][] dpCreatePositive() throws IOException {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("Alice");
        user.setEmail("alice@google.com");
//        user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        users.add(user);

        Media media = new Media();
        media.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        media.setType("image");
        media.setName("Tulips");

        Course actual = new Course();
        actual.setName("Java");
        actual.setAuthors(users);
        actual.setSelfAssignable(false);
        actual.setMedia(media);

        Course expected = new Course();
        expected.setName("Java");
        expected.setAuthors(users);
        expected.setSelfAssignable(false);
        actual.setMedia(media);

        return new Object[][]{{actual, expected}};
    }

    /*
     * Negative Test Case to test Course Create method(when course name is null)
     */
    @Test(dataProvider = "dpCreateNegative1", priority = 1)
    public void testCreateNegative1(Course course, PreConditionFailException expectedException) {

        try {
            when(courseRepoMock.save(Mockito.any(Course.class)))
                    .thenThrow(new PreConditionFailException(ErrorCode.PRECONDITION_FAIL));
            courseServiceImplMock.create(course);
        } catch (PreConditionFailException e) {
            assertEquals(e.getMessage(), expectedException.getMessage());
        }
    }

    @DataProvider
    Object[][] dpCreateNegative1() throws IOException {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("Alice");
        user.setEmail("alice@google.com");
//        user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        users.add(user);

        Media media = new Media();
        media.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        media.setType("image");
        media.setName("Tulips");

        Course course = new Course();
        course.setName(null);
        course.setAuthors(users);
        course.setSelfAssignable(false);
        course.setMedia(media);

        PreConditionFailException expectedException = new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);

        return new Object[][]{{course, expectedException}};
    }

    /*
     * Negative Test Case to test Course Create method(when no
     * resource/file/thumbnail is found)
     */
    @Test(dataProvider = "dpCreateNegative2", priority = 2)
    public void testCreateNegative2(Course course, PreConditionFailException expectedException) {

        try {
            when(courseRepoMock.save(Mockito.any(Course.class)))
                    .thenThrow(new PreConditionFailException(ErrorCode.RESOURCE_NOT_FOUND));
            courseServiceImplMock.create(course);
        } catch (PreConditionFailException e) {
            assertEquals(e.getMessage(), expectedException.getMessage());
        }
    }

    @DataProvider
    Object[][] dpCreateNegative2() throws IOException {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("Alice");
        user.setEmail("alice@google.com");
//        user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        users.add(user);

        Course course = new Course();
        course.setName("JSP");
        course.setAuthors(users);
        course.setSelfAssignable(false);

        PreConditionFailException expectedException = new PreConditionFailException(ErrorCode.RESOURCE_NOT_FOUND);

        return new Object[][]{{course, expectedException}};
    }

    /*
     * Negative Test Case to test Course Create method(when course name is too long)
     */
    @Test(dataProvider = "dpCreateNegative3", priority = 2)
    public void testCreateNegative3(Course course, PreConditionFailException expectedException) {

        try {
            when(courseRepoMock.save(Mockito.any(Course.class)))
                    .thenThrow(new PreConditionFailException(ErrorCode.PRECONDITION_FAIL));
            courseServiceImplMock.create(course);
        } catch (PreConditionFailException e) {
            assertEquals(e.getMessage(), expectedException.getMessage());
        }
    }

    @DataProvider
    Object[][] dpCreateNegative3() throws IOException {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("Alice");
        user.setEmail("alice@google.com");
//        user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        users.add(user);

        Media media = new Media();
        media.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        media.setType("image");
        media.setName("Tulips");

        Course course = new Course();
        course.setName("Java, a cross platform, neutral, compiled and interpreted piece of art");
        course.setAuthors(users);
        course.setSelfAssignable(false);
        course.setMedia(media);

        PreConditionFailException expectedException = new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);

        return new Object[][]{{course, expectedException}};
    }

    /*
     * Negative Test Case to test Course Create method(when course name is too long)
     */
    @Test(dataProvider = "dpCreateNegative4", priority = 3)
    public void testCreateNegative4(Course course, PreConditionFailException expectedException) {

        try {
            when(courseRepoMock.save(Mockito.any(Course.class)))
                    .thenThrow(new PreConditionFailException(ErrorCode.PRECONDITION_FAIL));
            courseServiceImplMock.create(course);
        } catch (PreConditionFailException e) {
            assertEquals(e.getMessage(), expectedException.getMessage());
        }
    }

    @DataProvider
    Object[][] dpCreateNegative4() throws IOException {

        List<User> users = new ArrayList<>();
        users.add(new User("firstName", "lastName", "email", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Alice", "Montgomery", "alice@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Bryan", "Montgomery", "bryan@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Ezra", "Montgomery", "ezra@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Aria", "Montgomery", "aria@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Ezra", "Montgomery", "ezra@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Spencer", "Hastings", "spenz@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Monica", "Hastings", "mon@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Phoebe", "Buffay", "phebes@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Ross", "Geller", "rach@gmail.com", new Timestamp(System.currentTimeMillis()), true));
        users.add(new User("Joey", "Tribbiani", "joey@gmail.com", new Timestamp(System.currentTimeMillis()), true));

        Media media = new Media();
        media.setUrl("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        media.setType("image");

        Course course = new Course();
        course.setName("Java");
        course.setAuthors(users);
        course.setSelfAssignable(false);
        course.setMedia(media);

        PreConditionFailException expectedException = new PreConditionFailException(ErrorCode.PRECONDITION_FAIL);

        return new Object[][]{{course, expectedException}};
    }
}
