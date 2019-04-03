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
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.DiscussionRepository;
import com.objectfrontier.land.service.impl.DiscussionServiceImpl;

/**
 * @author jayanth.subramanian
 * @since v1.0
 * <p>
 * Test cases for the CRUD operations in Discussion Service
 */
public class DiscussionServiceImplTest {

    @Mock
    private DiscussionRepository discussionRepoMock;

    private Timestamp userLastLoggedIn;
    private Date createdDate;
    private User user;
    private Discussion actualDiscussion;
    private Discussion expectedDiscussion;
    private List<Discussion> actualDiscussions;
    private List<Discussion> expectedDiscussions;
    private List<Discussion> discussions;
    private String searchInput;
    private int pageNo;
    private int offset;
    private Pageable page;
    private List<Long> discussionIds;

    @InjectMocks
    private DiscussionServiceImpl discussionServiceImplMock;

    @BeforeClass
    public void setup() {

        MockitoAnnotations.initMocks(this);
        userLastLoggedIn = new Timestamp(System.currentTimeMillis());
        createdDate = new Date(System.currentTimeMillis());

        user = new User("Alice", "Montgomery", "alice@google.com", userLastLoggedIn, true);

        discussions = new ArrayList<>();

        actualDiscussion = new Discussion();
        actualDiscussion.setTitle("Java Reflection");
        actualDiscussion.setDescription("how does java reflection work?");
        actualDiscussion.setDiscussionCreator(user);
        actualDiscussion.setCreatedDate(createdDate);

        expectedDiscussion = actualDiscussion;
        expectedDiscussion.setId(1);

        actualDiscussions = new ArrayList<>();
        actualDiscussions.add(actualDiscussion);

        expectedDiscussions = new ArrayList<>();
        expectedDiscussions.add(expectedDiscussion);

        searchInput = "ja";
        pageNo = 1;
        offset = 5;

        discussionIds = new ArrayList<>();
        discussionIds.add((long) 1);

        page = PageRequest.of(pageNo, offset, Sort.by("createdDate").descending());        
    }

    @Test
    public void testCreatePositive() {
        when(discussionRepoMock.save(actualDiscussion)).thenReturn(expectedDiscussion);
        assertEquals((discussionServiceImplMock.create(actualDiscussion)).toString(), expectedDiscussion.toString());
    }

    @Test
    public void testReadPositive() {
        when(discussionRepoMock.findById(any(Long.class))).thenReturn(Optional.of(expectedDiscussion));
        assertEquals(discussionServiceImplMock.findById(any(Long.class)).toString(), expectedDiscussion.toString());
    }

    @Test
    public void testReadAllPositive() {
        when(discussionRepoMock.findAll()).thenReturn(expectedDiscussions);
        assertEquals(discussionServiceImplMock.findAll(), expectedDiscussions);
    }

    @Test
    public void testUpdatePositive() {
        when(discussionRepoMock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(expectedDiscussion));
        when(discussionRepoMock.save(actualDiscussion)).thenReturn(expectedDiscussion);
        assertEquals(discussionServiceImplMock.update(actualDiscussion).toString(), expectedDiscussion.toString());
    }

    @Test
    public void testDeletePostive() {
        doNothing().when(discussionRepoMock).deleteById(Mockito.any(Long.class));
        discussionServiceImplMock.delete(1);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void testReadNegative() {
        when(discussionRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        discussionServiceImplMock.findById(1);
    }

    @Test
    public void testSearch() {
        when(discussionRepoMock.findByTitleContainingOrDescriptionContainingOrderByTitle(Mockito.anyString(), Mockito.anyString(), any(Pageable.class))).thenReturn(actualDiscussions);
        assertEquals(discussionServiceImplMock.search(searchInput, pageNo, offset), actualDiscussions);
    }

    @Test
    public void testSortedByRecent() {
        when(discussionRepoMock.findByIdIn(discussionIds, page)).thenReturn(expectedDiscussions);
        assertEquals(discussionServiceImplMock.sortedByRecent(expectedDiscussions, pageNo, offset).toString(), expectedDiscussions.toString());
    }

    @Test
    public void testSortByRecentlyCommented() {
        when(discussionRepoMock.findByIdIn(discussionIds, page)).thenReturn(discussions);
        assertEquals(discussionServiceImplMock.sortByRecentlyCommented(discussions, pageNo, offset).toString(), discussions.toString());
    }
}
