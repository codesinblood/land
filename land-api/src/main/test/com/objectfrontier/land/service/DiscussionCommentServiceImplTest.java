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
import org.springframework.data.domain.Sort;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.DiscussionCommentRepository;
import com.objectfrontier.land.service.impl.DiscussionCommentServiceImpl;

/**
 * @author gunasekaran.k
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Test cases for the CRUD operations in Discussion Comment Service
 */
public class DiscussionCommentServiceImplTest {

    @Mock
    private DiscussionCommentRepository discussionCommentRepoMock;

    private Timestamp userLastLoggedIn;
    private Date createdDate;
    private User user;
    private Pageable page;
    private int pageNo;
    private int offset;
    private Discussion discussion;
    private List<Discussion> discussions;
    private DiscussionComment actualDiscussionComment;
    private DiscussionComment expectedDiscussionComment;
    private List<DiscussionComment> actualDiscussionComments;
    private List<DiscussionComment> expectedDiscussionComments;
    private List<Long> discussionIds;

    @InjectMocks
    private DiscussionCommentServiceImpl discussionCommentServiceImplMock;

    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
        userLastLoggedIn = new Timestamp(System.currentTimeMillis());
        createdDate = new Date(System.currentTimeMillis());

        user = new User("Alice", "Montgomery", "alice@google.com", userLastLoggedIn, true);

        pageNo = 1;
        offset = 1;

        page = PageRequest.of(pageNo, offset, Sort.by("createdDate").descending());

        discussion = new Discussion();
        discussion.setId(1);
        discussion.setTitle("Java Reflection");
        discussion.setDescription("how does java reflection work?");
        discussion.setDiscussionCreator(user);
        discussion.setCreatedDate(createdDate);

        discussions = new ArrayList<>();

        actualDiscussionComment = new DiscussionComment();
        actualDiscussionComment.setComment("Will it work");
        actualDiscussionComment.setDiscussion(discussion);
        actualDiscussionComment.setCommenter(user);
        actualDiscussionComment.setCreatedDate(createdDate);
        actualDiscussionComment.setStatus(true);

        expectedDiscussionComment = actualDiscussionComment;
        expectedDiscussionComment.setId(1);

        actualDiscussionComments = new ArrayList<>();
        actualDiscussionComments.add(actualDiscussionComment);

        expectedDiscussionComments = new ArrayList<>();
        expectedDiscussionComments.add(expectedDiscussionComment);

        discussionIds = new ArrayList<>();
    }

    @Test
    public void testCreatePositive() {
        when(discussionCommentRepoMock.save(actualDiscussionComment)).thenReturn(expectedDiscussionComment);
        assertEquals((discussionCommentServiceImplMock.create(actualDiscussionComment)).toString(), expectedDiscussionComment.toString());
    }

    @Test
    public void testReadPositive() {
        when(discussionCommentRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedDiscussionComment));
        assertEquals(discussionCommentServiceImplMock.findById(actualDiscussionComment.getId()).toString(), expectedDiscussionComment.toString());
    }

    @Test
    public void testReadByDiscussionIdPositive() {
        when(discussionCommentRepoMock.findByDiscussionId(Mockito.anyLong(), any(Pageable.class))).thenReturn(expectedDiscussionComments);
        assertEquals(discussionCommentServiceImplMock.findByDiscussionId(discussion.getId(), pageNo, offset).toString(), expectedDiscussionComments.toString());
    }

    @Test
    public void testReadAllPositive() {
        when(discussionCommentRepoMock.findAll()).thenReturn(expectedDiscussionComments);
        assertEquals(discussionCommentServiceImplMock.findAll(), expectedDiscussionComments);
    }

    @Test
    public void testUpdatePositive() {
        when(discussionCommentRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedDiscussionComment));
        when(discussionCommentRepoMock.save(actualDiscussionComment)).thenReturn(expectedDiscussionComment);
        assertEquals(discussionCommentServiceImplMock.update(actualDiscussionComment).toString(), actualDiscussionComment.toString());
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(discussionCommentRepoMock).deleteById(Mockito.any(Long.class));
        discussionCommentServiceImplMock.delete(1);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(discussionCommentRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        discussionCommentServiceImplMock.findById(1);
    }

    @Test
    public void testFindByDiscussionIds() {
        when(discussionCommentRepoMock.findByDiscussionIdIn(discussionIds, page)).thenReturn(expectedDiscussionComments);
        assertEquals(discussionCommentServiceImplMock.findByDiscussionIds(discussions, pageNo, offset), expectedDiscussionComments);
        
    }

    @Test
    public void testFindByDiscussionIdsOrderByCreatedDate() {
        when(discussionCommentRepoMock.findByDiscussionIdInOrderByCreatedDateDesc(discussionIds)).thenReturn(expectedDiscussionComments);
        assertEquals(discussionCommentServiceImplMock.findByDiscussionIdsOrderByCreatedDate(discussions), expectedDiscussionComments);
        
    }
}
