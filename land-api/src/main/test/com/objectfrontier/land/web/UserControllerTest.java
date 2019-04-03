package com.objectfrontier.land.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.service.impl.UserServiceImpl;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Test cases to check request and response for the User Service from User Controller
 */

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userServiceImplMock;

    @InjectMocks
    private UserController userControllerMock;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @BeforeClass
    public void init() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();
    }

    @Test(dataProvider = "dpCreatePositive")
    public void testCreatePositive(User actualUser, User expectedUser) throws Exception {

        when(userServiceImplMock.create(any(User.class))).thenReturn(expectedUser);

        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(actualUser)))
                .andExpect(status().isCreated());

        verify(userServiceImplMock, times(1)).create(Mockito.any(User.class));
        verifyNoMoreInteractions(userServiceImplMock);
    }

    @DataProvider
    Object[][] dpCreatePositive() {

        User actualUser = new User("Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);
        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);

        return new Object[][]{
                {actualUser, expectedUser}
        };
    }

    @Test
    public void testDeletepositive() throws Exception {

        doNothing().when(userServiceImplMock).delete(Mockito.any(Long.class));

        mockMvc.perform(delete("/api/v1/user/{id}", (long) 1)).andExpect(status().isNoContent());

        verify(userServiceImplMock, times(1)).delete(1);
        verifyNoMoreInteractions(userServiceImplMock);
    }

    @Test(dataProvider = "dpReadAllPositive")
    public void testReadAllPositive(List<User> expectedUsers) throws Exception {

        when(userServiceImplMock.readAll()).thenReturn(expectedUsers);

        mockMvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Alexander")))
                .andExpect(jsonPath("$[0].lastName", is("Flemming")))
                .andExpect(jsonPath("$[0].email", is("flemming@gmail.com")))
                .andExpect(jsonPath("$[0].activeStatus", is(true)))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(userServiceImplMock, times(1)).readAll();
        verifyNoMoreInteractions(userServiceImplMock);
    }

    @DataProvider
    Object[][] dpReadAllPositive() {

        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);
        List<User> users = new ArrayList<User>();
        users.add(expectedUser);
        return new Object[][]{
                {users}
        };
    }

    @Test(dataProvider = "dpReadPositive")
    public void testReadPositive(Long id, User expectedUser) throws Exception {

        when(userServiceImplMock.read(any(Long.class))).thenReturn(expectedUser);

        mockMvc.perform(get("/api/v1/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Alexander")))
                .andExpect(jsonPath("$.lastName", is("Flemming")))
                .andExpect(jsonPath("$.email", is("flemming@gmail.com")))
                .andExpect(jsonPath("$.activeStatus", is(true)));

        verify(userServiceImplMock, times(1)).read(1);
        verifyNoMoreInteractions(userServiceImplMock);
    }

    @DataProvider
    Object[][] dpReadPositive() {

        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);
        return new Object[][]{
                {(long) 1, expectedUser}
        };
    }

    @Test(dataProvider = "dpUpdatePositive")
    public void testUpdatePositive(User actualUser, User expectedUser) throws Exception {

        when(userServiceImplMock.update(any(User.class))).thenReturn(expectedUser);

        mockMvc.perform(put("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(actualUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Alexander")))
                .andExpect(jsonPath("$.lastName", is("Flemming")))
                .andExpect(jsonPath("$.email", is("flemming@gmail.com")))
                .andExpect(jsonPath("$.activeStatus", is(true)));

        verify(userServiceImplMock, times(1)).update(Mockito.any(User.class));
        verifyNoMoreInteractions(userServiceImplMock);
    }

    @DataProvider
    Object[][] dpUpdatePositive() {

        User actualUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);
        User expectedUser = new User(1, "Alexander", "Flemming", "flemming@gmail.com", new Timestamp(System.currentTimeMillis()), true);
        return new Object[][]{
                {actualUser, expectedUser}
        };
    }
}
