package com.objectfrontier.land.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
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
import com.objectfrontier.land.model.CommentNotification;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.CommentNotificationRepository;
import com.objectfrontier.land.service.impl.CommentNotificationServiceImpl;

/**
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Test cases for the CRUD operations in Comment Notification Service
 */
public class CommentNotificationServiceImplTest {

    @Mock
    private CommentNotificationRepository commentNotificationRepoMock;

    private Timestamp time;
    private Date createdDate;
    private User user;
    private User follower;
    private Timestamp userLastLoggedIn;
    private Date discussionCreatedDate;
    private User discussionUser;
    private Discussion discussion;
    private DiscussionComment discussionComment;
    private CommentNotification actualCommentNotification;
    private CommentNotification expectedCommentNotification;
    private List<CommentNotification> actualCommentNotifications;
    private List<CommentNotification> expectedCommentNotifications;

    @InjectMocks
    private CommentNotificationServiceImpl commentNotificationServiceImplMock;

    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
        time = new Timestamp(System.currentTimeMillis());
        createdDate = new Date(System.currentTimeMillis());

        user = new User("Alexander", "Flemming", "flemming@gmail.com", time, true);
        follower = new User("Blake", "Lively", "blake@gmail.com", time, true);

        userLastLoggedIn = new Timestamp(System.currentTimeMillis());
        discussionCreatedDate = new Date(System.currentTimeMillis());

        discussionUser = new User("Alice", "Montgomery", "alice@google.com", userLastLoggedIn, true);

        discussion = new Discussion();
        discussion.setTitle("Java Reflection");
        discussion.setDescription("how does java reflection work?");
        discussion.setDiscussionCreator(discussionUser);
        discussion.setCreatedDate(discussionCreatedDate);

        discussionComment = new DiscussionComment();
        discussionComment.setComment("java reflection does handle generation of getters");
        discussionComment.setDiscussion(discussion);
        discussionComment.setCommenter(follower);
        discussionComment.setCreatedDate(createdDate);
        discussionComment.setStatus(true);

        actualCommentNotification = new CommentNotification();
        actualCommentNotification.setDiscussionComment(discussionComment);
        actualCommentNotification.setUser(user);
        actualCommentNotification.setHasSeen(false);

        expectedCommentNotification = actualCommentNotification;
        expectedCommentNotification.setId(1);

        actualCommentNotifications = new ArrayList<>();
        actualCommentNotifications.add(actualCommentNotification);

        expectedCommentNotifications = new ArrayList<>();
        expectedCommentNotifications.add(expectedCommentNotification);
    }

    @Test
    public void testCreatePositive() {
        when(commentNotificationRepoMock.save(actualCommentNotification)).thenReturn(expectedCommentNotification);
        assertEquals((commentNotificationServiceImplMock.create(actualCommentNotification)).toString(), expectedCommentNotification.toString());
    }

    @Test
    public void testReadPositive() {
        when(commentNotificationRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedCommentNotification));
        assertEquals(commentNotificationServiceImplMock.findById(actualCommentNotification.getId()), expectedCommentNotification);
    }

    @Test
    public void testReadAllPositive() {
        when(commentNotificationRepoMock.findAll()).thenReturn(expectedCommentNotifications);
        assertEquals(commentNotificationServiceImplMock.findAll(), expectedCommentNotifications);
    }

    @Test
    public void testCreateAllPositive() {
        when(commentNotificationRepoMock.saveAll(actualCommentNotifications)).thenReturn(expectedCommentNotifications);
        assertEquals(commentNotificationServiceImplMock.createAll(actualCommentNotifications), expectedCommentNotifications);
    }

    @Test
    public void testUpdatePositive() {
        when(commentNotificationRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedCommentNotification));
        when(commentNotificationRepoMock.save(actualCommentNotification)).thenReturn(expectedCommentNotification);
        assertEquals(commentNotificationServiceImplMock.update(actualCommentNotification), expectedCommentNotification);
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(commentNotificationRepoMock).deleteById(Mockito.any(Long.class));
        commentNotificationServiceImplMock.delete(1);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(commentNotificationRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        commentNotificationServiceImplMock.findById(1);
    }
}
