/**
 * 
 */
package com.objectfrontier.land.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectfrontier.land.dto.forum.DiscussionCommentDTO;
import com.objectfrontier.land.dto.forum.DiscussionDTO;
import com.objectfrontier.land.dto.forum.DiscussionFollowerDTO;
import com.objectfrontier.land.dto.forum.DiscussionTopicLinkDTO;
import com.objectfrontier.land.dto.forum.TopicDTO;
import com.objectfrontier.land.dto.forum.UserDTO;
import com.objectfrontier.land.model.CommentNotification;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.model.DiscussionFollower;
import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.service.impl.CommentNotificationServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionCommentServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionFollowerServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionTopicServiceImpl;
import com.objectfrontier.land.service.impl.TopicServiceImpl;

/**
 * @author jayanth.subramanian
 * @since v1.0.0
 * <p>
 * Test cases for the methods in Forum Controller
 */
public class ForumControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DiscussionFollowerServiceImpl discussionFollowerServiceImpl;

    @Mock
    private DiscussionCommentServiceImpl discussionCommentServiceImpl;

    @Mock
    private CommentNotificationServiceImpl commentNotificationServiceImpl;

    @Mock
    private DiscussionTopicServiceImpl discussionTopicServiceImpl;

    @Mock
    private DiscussionServiceImpl discussionServiceImpl;

    @Mock
    private TopicServiceImpl topicServiceImpl;

    private Timestamp userLastLoggedIn;
    private Date createdDate;
    private User user;
    private Discussion actualDiscussion;
    private Discussion expectedDiscussion;
    private List<Discussion> actualDiscussions;
    private List<Discussion> discussions;
    private DiscussionComment actualDiscussionComment;
    private DiscussionComment expectedDiscussionComment;
    private DiscussionCommentDTO discussionCommentDto;
    private List<DiscussionCommentDTO> expectedDiscussionCommentDtos;
    private List<DiscussionComment> expectedDiscussionComments;
    private DiscussionFollower actualDiscussionFollower;
    private DiscussionFollower expectedDiscussionFollower;
    private List<DiscussionFollower> discussionFollowers;
    private DiscussionFollowerDTO actualDiscussionFollowerDto;
    private CommentNotification commentNotification;
    private List<CommentNotification> commentNotifications;
    private DiscussionDTO discussionDto;
    private UserDTO follower;
    private DiscussionTopic discussionTopicLink;
    private List<DiscussionTopic> discussionTopicLinks;
    private DiscussionTopicLinkDTO discussionTopicLinkDto;
    private List<DiscussionTopicLinkDTO> discussionTopicLinkDtos;
    private Topic topic;
    private List<Topic> topics;
    private String searchInput;
    private int pageNo;
    private int offset;
    private int courseId;

    @InjectMocks
    private ForumController forumController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @BeforeClass
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(forumController).build();

        userLastLoggedIn = new Timestamp(System.currentTimeMillis());
        createdDate = new Date(System.currentTimeMillis());

        user = new User(1, "Alice", "Montgomery", "alice@google.com", userLastLoggedIn, true);

        follower = new UserDTO();
        follower.setId((long) 1);
        follower.setFirstName("Alice");
        follower.setLastName("Montgomery");

        actualDiscussion = new Discussion();
        actualDiscussion.setTitle("Java Reflection");
        actualDiscussion.setDescription("how does java reflection work?");
        actualDiscussion.setDiscussionCreator(user);
        actualDiscussion.setCreatedDate(createdDate);

        expectedDiscussion = actualDiscussion;
        expectedDiscussion.setId(1);

        actualDiscussions = new ArrayList<>();
        actualDiscussions.add(expectedDiscussion);

        discussions = new ArrayList<>();
        actualDiscussionComment = new DiscussionComment();
        actualDiscussionComment.setComment("Will it work");
        actualDiscussionComment.setDiscussion(actualDiscussion);
        actualDiscussionComment.setCommenter(user);
        actualDiscussionComment.setCreatedDate(createdDate);
        actualDiscussionComment.setStatus(true);

        expectedDiscussionComment = actualDiscussionComment;
        expectedDiscussionComment.setId(1);

        discussionDto = new DiscussionDTO();
        discussionDto.setId(1);
        discussionDto.setTitle("Java Reflection");
        discussionDto.setDescription("how does java reflection work?");
        discussionDto.setDiscussionCreator(follower);
        discussionDto.setCreatedDate(createdDate);
        discussionDto.setDiscussionTopics(new ArrayList<DiscussionTopicLinkDTO>());

        discussionCommentDto = new DiscussionCommentDTO();
        discussionCommentDto.setId(1);
        discussionCommentDto.setComment("this is a comment");
        discussionCommentDto.setDiscussion(new DiscussionDTO());
        discussionCommentDto.setCommenter(new UserDTO());
        discussionCommentDto.setCreatedDate(new Date(System.currentTimeMillis()));

        expectedDiscussionCommentDtos = new ArrayList<>();
        expectedDiscussionCommentDtos.add(discussionCommentDto);

        expectedDiscussionComments = new ArrayList<>();
        expectedDiscussionComments.add(actualDiscussionComment);

        actualDiscussionFollower = new DiscussionFollower();
        actualDiscussionFollower.setDiscussion(actualDiscussion);
        actualDiscussionFollower.setFollower(user);

        discussionFollowers = new ArrayList<>();
        discussionFollowers.add(actualDiscussionFollower);

        expectedDiscussionFollower = actualDiscussionFollower;
        expectedDiscussionFollower.setId(1);

        actualDiscussionFollowerDto = new DiscussionFollowerDTO();
        actualDiscussionFollowerDto.setDiscussion(discussionDto);
        actualDiscussionFollowerDto.setFollower(follower);

        commentNotification = new CommentNotification();
        commentNotification.setDiscussionComment(actualDiscussionComment);
        commentNotification.setUser(user);
        commentNotification.setHasSeen(false);

        commentNotifications = new ArrayList<>();
        commentNotifications.add(commentNotification);

        discussionTopicLink = new DiscussionTopic();
        discussionTopicLink.setDiscussion(actualDiscussion);
        discussionTopicLink.setTopic(new Topic());
        discussionTopicLink.setPrimary(true);

        discussionTopicLinks = new ArrayList<>();
        discussionTopicLinks.add(discussionTopicLink);

        discussionTopicLinkDto = new DiscussionTopicLinkDTO();
        discussionTopicLinkDto.setId(1);
        discussionTopicLinkDto.setTopic(new TopicDTO());
        discussionTopicLinkDto.setPrimary(true);

        discussionTopicLinkDtos = new ArrayList<>();
        discussionTopicLinkDtos.add(discussionTopicLinkDto);

        topic = new Topic();
        topic.setName("Basic Java");
        topic.setDescription("Basic syntax and sematics of java");
        topic.setDisplayOrder(1);
        topic.setCourseId(1);

        topics = new ArrayList<>();
        topics.add(topic);

        courseId = 25;

        searchInput = "ja";
        pageNo = 1;
        offset = 1;
    }

    @Test
    public void testFindCommentsByDiscussionIdPositive() throws Exception {

        when(discussionCommentServiceImpl.findByDiscussionId(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(expectedDiscussionComments);
        mockMvc.perform(get("/api/v1/forum/comment/discussion/{discussionId}/{userId}?offset=" + offset + "&pageNo=" + pageNo + "", expectedDiscussion.getId(), user.getId()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(discussionCommentServiceImpl, times(1)).findByDiscussionId(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt());
        verifyNoMoreInteractions(discussionCommentServiceImpl);
    }

    @Test
    public void testCreateDiscussionFollowerPositive() throws Exception {

        when(discussionFollowerServiceImpl.create(any(DiscussionFollower.class))).thenReturn(expectedDiscussionFollower);
        mockMvc.perform(post("/api/v1/forum/follower")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(actualDiscussionFollowerDto)))
               .andExpect(status().isOk());

        verify(discussionFollowerServiceImpl, times(1)).create(any(DiscussionFollower.class));
        verifyNoMoreInteractions(discussionFollowerServiceImpl);
    }

    @Test
    public void testCreateDiscussionCommentPositive() throws Exception {

        when(discussionCommentServiceImpl.create(any(DiscussionComment.class))).thenReturn(expectedDiscussionComment);
        when(discussionFollowerServiceImpl.findByDiscussionId(any(Long.class))).thenReturn(discussionFollowers);
        when(commentNotificationServiceImpl.createAll(commentNotifications)).thenReturn(commentNotifications);
        mockMvc.perform(post("/api/v1/forum/comment")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(discussionCommentDto)))
               .andExpect(status().isOk());

        verify(commentNotificationServiceImpl, times(1)).createAll(Mockito.anyList());
        verify(discussionFollowerServiceImpl, times(1)).findByDiscussionId(Mockito.any(Long.class));
        verify(discussionCommentServiceImpl, times(1)).create(Mockito.any(DiscussionComment.class));
        verifyNoMoreInteractions(discussionCommentServiceImpl);
        verifyNoMoreInteractions(discussionFollowerServiceImpl);
        verifyNoMoreInteractions(commentNotificationServiceImpl);
    }

    @Test
    public void testCreateDiscussionPositive() throws Exception {

        when(discussionServiceImpl.create(any(Discussion.class))).thenReturn(expectedDiscussion);
        when(discussionTopicServiceImpl.createAll(discussionTopicLinks)).thenReturn(discussionTopicLinks);
        when(discussionFollowerServiceImpl.create(expectedDiscussionFollower)).thenReturn(expectedDiscussionFollower);
        mockMvc.perform(post("/api/v1/forum/discussion")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(discussionDto)))
               .andExpect(status().isOk());

        verify(discussionTopicServiceImpl, times(1)).createAll(Mockito.anyList());
        verify(discussionFollowerServiceImpl, times(2)).create(Mockito.any(DiscussionFollower.class));
        verify(discussionServiceImpl, times(1)).create(Mockito.any(Discussion.class));
        verifyNoMoreInteractions(discussionServiceImpl);
        verifyNoMoreInteractions(discussionFollowerServiceImpl);
        verifyNoMoreInteractions(discussionTopicServiceImpl);
    }

    @Test
    public void testFindDiscussionByTopicPositive() throws Exception {

        when(discussionTopicServiceImpl.findByTopicId(any(Long.class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(discussionTopicLinks);
        mockMvc.perform(get("/api/v1/forum/topic/{id}?offset=" + offset + "&pageNo=" + pageNo + "", (long) 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(discussionTopicServiceImpl, times(1)).findByTopicId(any(Long.class), Mockito.anyInt(), Mockito.anyInt());
        verifyNoMoreInteractions(discussionTopicServiceImpl);
    }

    @Test
    public void testDeleteDiscussionFollowerPositive() throws Exception {

        doNothing().when(discussionFollowerServiceImpl).delete(Mockito.any(Long.class));
        mockMvc.perform(delete("/api/v1/forum/follower/{id}", (long) 1))
               .andExpect(status().isNoContent());

        verify(discussionFollowerServiceImpl, times(1)).delete(1);
        verifyNoMoreInteractions(discussionFollowerServiceImpl);
    }

    @Test
    public void testSearchDiscussionPositive() throws Exception {

        when(discussionServiceImpl.search(searchInput, pageNo, offset)).thenReturn(actualDiscussions);
        mockMvc.perform(get("/api/v1/forum/discussion/search/{searchInput}?offset=" + offset + "&pageNo=" + pageNo + "", searchInput))
          .andExpect(status().isOk());

        verify(discussionServiceImpl, times(1)).search(searchInput, pageNo, offset);
        verifyNoMoreInteractions(discussionServiceImpl);
    }

    @Test
    public void testReadDiscussionsThatAreFollowedPositive() throws Exception {

        when(discussionFollowerServiceImpl.findByDiscussionsAndUserId(discussions, user.getId(), pageNo, offset)).thenReturn(discussionFollowers);
        mockMvc.perform(get("/api/v1/forum/discussion/following/{courseId}/{userId}?offset=" + offset + "&pageNo=" + pageNo + "", courseId, user.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(discussionFollowerServiceImpl, times(1)).findByDiscussionsAndUserId(discussions, user.getId(), pageNo, offset);
    }

    @Test
    public void testReadDiscussionsSortByRecentPositive() throws Exception {

        when(discussionServiceImpl.sortedByRecent(discussions, pageNo, offset)).thenReturn(discussions);
        mockMvc.perform(get("/api/v1/forum/discussion/recent/{courseId}?offset=" + offset + "&pageNo=" + pageNo + "", courseId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        
        verify(discussionServiceImpl, times(1)).sortedByRecent(discussions, pageNo, offset);
        verifyNoMoreInteractions(discussionServiceImpl);
    }

    @Test
    public void testReadDiscussionsSortByRecentCommentPositive() throws Exception {

        when(discussionCommentServiceImpl.findByDiscussionIdsOrderByCreatedDate(discussions)).thenReturn(expectedDiscussionComments);
        mockMvc.perform(get("/api/v1/forum/discussion/recent/comment/{courseId}?offset=" + offset + "&pageNo=" + pageNo + "", courseId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(discussionCommentServiceImpl, times(1)).findByDiscussionIdsOrderByCreatedDate(discussions);
        verifyNoMoreInteractions(discussionCommentServiceImpl);
    }

    @Test
    public void testFindAllTopicInCourse() throws Exception {

        when(topicServiceImpl.findByCourseId(courseId)).thenReturn(topics);
        mockMvc.perform(get("/api/v1/forum/course/{id}", courseId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(topicServiceImpl, times(1)).findByCourseId(courseId);
        verifyNoMoreInteractions(topicServiceImpl);
        
    }
}
