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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionFollower;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.DiscussionFollowerRepository;
import com.objectfrontier.land.service.impl.DiscussionFollowerServiceImpl;

/**
 * @author gunasekaran.k
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Test cases for the CRUD operations in Discussion Follower Service
 */
public class DiscussionFollowerServiceImplTest {

    @Mock
    private DiscussionFollowerRepository discussionFollowerRepoMock;

    private Timestamp userLastLoggedIn;
    private Date createdDate;
    private User user;
    private Discussion discussion;
    private List<Discussion> discussions;
    private DiscussionFollower actualDiscussionFollower;
    private DiscussionFollower expectedDiscussionFollower;
    private List<DiscussionFollower> discussionFollowers;
    private List<DiscussionFollower> expectedDiscussionFollowers;
    private List<Long> discussionIds;
    private Pageable page;
    private int pageNo;
    private int offset;

    @InjectMocks
    private DiscussionFollowerServiceImpl discussionFollowerServiceImplMock;

    
    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
        userLastLoggedIn = new Timestamp(System.currentTimeMillis());
        createdDate = new Date(System.currentTimeMillis());

        user = new User("Alice", "Montgomery", "alice@google.com", userLastLoggedIn, true);

        discussion = new Discussion();
        discussion.setTitle("Java Reflection");
        discussion.setDescription("how does java reflection work?");
        discussion.setDiscussionCreator(user);
        discussion.setCreatedDate(createdDate);

        discussions = new ArrayList<>();

        actualDiscussionFollower = new DiscussionFollower();
        actualDiscussionFollower.setDiscussion(discussion);
        actualDiscussionFollower.setFollower(user);

        expectedDiscussionFollower = actualDiscussionFollower;
        expectedDiscussionFollower.setId(1);

        discussionFollowers = new ArrayList<>();

        expectedDiscussionFollowers = new ArrayList<>();
        expectedDiscussionFollowers.add(expectedDiscussionFollower);

        pageNo = 1;
        offset = 4;

        page = PageRequest.of(pageNo, offset);
    }

    @Test
    public void testCreatePositive() {
        when(discussionFollowerRepoMock.save(actualDiscussionFollower)).thenReturn(expectedDiscussionFollower);
        assertEquals((discussionFollowerServiceImplMock.create(actualDiscussionFollower)).toString(), expectedDiscussionFollower.toString());
    }

    @Test
    public void testReadPositive() {
        when(discussionFollowerRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedDiscussionFollower));
        assertEquals(discussionFollowerServiceImplMock.findById(expectedDiscussionFollower.getId()).toString(), expectedDiscussionFollower.toString());
    }

    @Test
    public void testReadByDiscussionIdPositive() {
        when(discussionFollowerRepoMock.findByDiscussionId(any(Long.class))).thenReturn(expectedDiscussionFollowers);
        assertEquals(discussionFollowerServiceImplMock.findByDiscussionId(discussion.getId()).toString(), expectedDiscussionFollowers.toString());
    }

    @Test
    public void testFindByUserIdAndDiscussionIdPositive() {
        when(discussionFollowerRepoMock.findByFollowerIdAndDiscussionId(any(Long.class), any(Long.class))).thenReturn(expectedDiscussionFollower);
        assertEquals(discussionFollowerServiceImplMock.findByUserIdAndDiscussionId(user.getId(), discussion.getId()).toString(), expectedDiscussionFollower.toString());
    }

    @Test
    public void testReadAllPositive() {
        when(discussionFollowerRepoMock.findAll()).thenReturn(expectedDiscussionFollowers);
        assertEquals(discussionFollowerServiceImplMock.findAll(), expectedDiscussionFollowers);
    }

    @Test
    public void testUpdatePositive() {
        when(discussionFollowerRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedDiscussionFollower));
        when(discussionFollowerRepoMock.save(actualDiscussionFollower)).thenReturn(expectedDiscussionFollower);
        assertEquals(discussionFollowerServiceImplMock.update(actualDiscussionFollower).toString(), expectedDiscussionFollower.toString());
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(discussionFollowerRepoMock).deleteById(Mockito.any(Long.class));
        discussionFollowerServiceImplMock.delete(1);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(discussionFollowerRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        discussionFollowerServiceImplMock.findById(1);
    }

    @Test
    public void testFindByDiscussionsAndUserId() {
        when(discussionFollowerRepoMock.findByDiscussionIdInAndFollowerId(discussionIds, user.getId(), page)).thenReturn(discussionFollowers);
        assertEquals(discussionFollowerServiceImplMock.findByDiscussionsAndUserId(discussions, user.getId(), pageNo, offset), discussionFollowers);
    }
}
