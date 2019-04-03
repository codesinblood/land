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
import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.DiscussionTopicRepository;
import com.objectfrontier.land.service.impl.DiscussionTopicServiceImpl;

/**
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Test cases for the CRUD operations in Discussion Topic Link Service
 */
public class DiscussionTopicLinkServiceTest {

    @Mock
    private DiscussionTopicRepository discussionTopicLinkRepoMock;

    private Timestamp userLastLoggedIn;
    private Date createdDate;
    private User user;
    private Discussion discussion;
    private DiscussionTopic actualDiscussionTopicLink;
    private DiscussionTopic expectedDiscussionTopicLink;
    private List<DiscussionTopic> expectedDiscussionTopicLinks;
    private List<DiscussionTopic> actualDiscussionTopicLinks;
    private int limit;
    private int pageNumber;
    private List<Long> topicIdList;
    private Pageable requestedPage;
    private Topic topic;
    private List<Topic> topics;
    private int discussionCount;

    @InjectMocks
    private DiscussionTopicServiceImpl discussionTopicLinkServiceImplMock;

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

        topic = new Topic();
        topic.setId(1);
        topic.setCourseId(1);
        topic.setDescription("Basic java");

        topics = new ArrayList<>();
        topics.add(topic);

        actualDiscussionTopicLink = new DiscussionTopic();
        actualDiscussionTopicLink.setDiscussion(discussion);
        actualDiscussionTopicLink.setTopic(new Topic());
        actualDiscussionTopicLink.setPrimary(true);
        expectedDiscussionTopicLink = actualDiscussionTopicLink;
        expectedDiscussionTopicLink.setId(1);

        actualDiscussionTopicLinks = new ArrayList<>();
        actualDiscussionTopicLinks.add(actualDiscussionTopicLink);

        expectedDiscussionTopicLinks = new ArrayList<>();
        expectedDiscussionTopicLinks.add(expectedDiscussionTopicLink);

        limit = 10;
        pageNumber = 2;

        topicIdList = new ArrayList<>();
        topicIdList.add((long) 1);

        discussionCount = 1;

        requestedPage = PageRequest.of(pageNumber, discussionCount);
    }

    @Test
    public void testCreatePositive() {
        when(discussionTopicLinkRepoMock.save(actualDiscussionTopicLink)).thenReturn(expectedDiscussionTopicLink);
        assertEquals((discussionTopicLinkServiceImplMock.create(actualDiscussionTopicLink)).toString(), expectedDiscussionTopicLink.toString());
    }

    @Test
    public void testCreateAllPositive() {
        when(discussionTopicLinkRepoMock.saveAll(actualDiscussionTopicLinks)).thenReturn(expectedDiscussionTopicLinks);
        assertEquals((discussionTopicLinkServiceImplMock.createAll(actualDiscussionTopicLinks)).toString(), expectedDiscussionTopicLinks.toString());
    }

    @Test
    public void testReadPositive() {
        when(discussionTopicLinkRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedDiscussionTopicLink));
        assertEquals(discussionTopicLinkServiceImplMock.findById(any(Long.class)).toString(), expectedDiscussionTopicLink.toString());
    }

    @Test
    public void testReadAllPositive() {
        when(discussionTopicLinkRepoMock.findAll()).thenReturn(expectedDiscussionTopicLinks);
        assertEquals(discussionTopicLinkServiceImplMock.findAll(), expectedDiscussionTopicLinks);
    }

    @Test
    public void testUpdatePositive() {
        when(discussionTopicLinkRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedDiscussionTopicLink));
        when(discussionTopicLinkRepoMock.save(actualDiscussionTopicLink)).thenReturn(expectedDiscussionTopicLink);
        assertEquals(discussionTopicLinkServiceImplMock.update(actualDiscussionTopicLink).toString(), expectedDiscussionTopicLink.toString());
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(discussionTopicLinkRepoMock).deleteById(Mockito.any(Long.class));
        discussionTopicLinkServiceImplMock.delete(1);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(discussionTopicLinkRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        discussionTopicLinkServiceImplMock.findById(1);
    }
    
    @Test
    public void testFindByTopicIdPositive() {
        when(discussionTopicLinkRepoMock.findByTopicId(any(Long.class), any(Pageable.class))).thenReturn(expectedDiscussionTopicLinks);
        assertEquals(discussionTopicLinkServiceImplMock.findByTopicId((long) 1, pageNumber, limit).toString(), expectedDiscussionTopicLinks.toString());
    }

    @Test
    public void testFindByListOfTopics() {
        when(discussionTopicLinkRepoMock.findByTopicIdIn(topicIdList, requestedPage)).thenReturn(expectedDiscussionTopicLinks);
        assertEquals(discussionTopicLinkServiceImplMock.findByListOfTopicId(topics, pageNumber, discussionCount).toString(), expectedDiscussionTopicLinks.toString());
    }

    @Test
    public void testFindByListOfPrimaryTopic() {
        when(discussionTopicLinkRepoMock.findByIsPrimaryTrueAndTopicIdIn(topicIdList)).thenReturn(expectedDiscussionTopicLinks);
        assertEquals(discussionTopicLinkServiceImplMock.findByListOfPrimaryTopic(topics), expectedDiscussionTopicLinks);
    }
}
