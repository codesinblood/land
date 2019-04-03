package com.objectfrontier.land.service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.UserRepository;
import com.objectfrontier.land.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Test cases for the crud operations in User Service
 */

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepoMock;

    @InjectMocks
    private UserServiceImpl userServiceImplMock;

    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test(dataProvider = "dpCreatePositive")
    public void testCreatePositive(User actualUser, User expectedUser) {

        when(userRepoMock.save(actualUser)).thenReturn(expectedUser);
        assertEquals((userServiceImplMock.create(actualUser)).toString(), expectedUser.toString());
    }

    @DataProvider
    Object[][] dpCreatePositive() {

        User actualUser = new User("Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);
        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);

        return new Object[][]{{actualUser, expectedUser}};
    }

    @Test()
    public void testDeletePostive() {

        doNothing().when(userRepoMock).deleteById(Mockito.any(Long.class));
        userServiceImplMock.delete(1);
    }

    @Test(dataProvider = "dpReadPositive")
    public void testReadPositive(Long id, User expectedUser) {

        when(userRepoMock.findById(id)).thenReturn(Optional.of(expectedUser));
        assertEquals(userServiceImplMock.read(id), expectedUser);
    }

    @DataProvider
    Object[][] dpReadPositive() {

        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);
        return new Object[][]{{(long) 1, expectedUser}};
    }

    @Test(dataProvider = "dpReadAllPositive")
    public void testReadAllPositive(List<User> expectedUsers) {

        when(userRepoMock.findAll()).thenReturn(expectedUsers);
        assertEquals(userServiceImplMock.readAll(), expectedUsers);
    }

    @DataProvider
    Object[][] dpReadAllPositive() {

        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);
        List<User> users = new ArrayList<User>();
        users.add(expectedUser);
        return new Object[][]{{users}};
    }

    @Test(dataProvider = "dpUpdatePositive")
    public void testUpdatePositive(User actualUser, User expectedUser) {

        when(userRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedUser));
        when(userRepoMock.save(Mockito.any(User.class))).thenReturn(expectedUser);
        assertEquals(userServiceImplMock.update(actualUser), expectedUser);
    }

    @DataProvider
    Object[][] dpUpdatePositive() {

        User actualUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);
        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com",
                new Timestamp(System.currentTimeMillis()), true);
        return new Object[][]{{actualUser, expectedUser}};
    }

    @Test(expectedExceptions = ResourceNotFoundException.class, priority = 6)
    public void testReadNegative() {

        User user = null;
        when(userRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));
        userServiceImplMock.read(1);
    }
}