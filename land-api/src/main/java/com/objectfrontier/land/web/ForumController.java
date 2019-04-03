package com.objectfrontier.land.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.objectfrontier.land.common.utility.ForumUtil;
import com.objectfrontier.land.common.utility.ModelMapperUtil;
import com.objectfrontier.land.dto.forum.DiscussionCommentDTO;
import com.objectfrontier.land.dto.forum.DiscussionCommentWithFollowerDTO;
import com.objectfrontier.land.dto.forum.DiscussionDTO;
import com.objectfrontier.land.dto.forum.DiscussionFollowerDTO;
import com.objectfrontier.land.dto.forum.DiscussionTopicDTO;
import com.objectfrontier.land.dto.forum.DiscussionTopicLinkDTO;
import com.objectfrontier.land.dto.forum.TopicDTO;
import com.objectfrontier.land.model.CommentNotification;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.model.DiscussionFollower;
import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;
import com.objectfrontier.land.service.impl.CommentNotificationServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionCommentServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionFollowerServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionServiceImpl;
import com.objectfrontier.land.service.impl.DiscussionTopicServiceImpl;
import com.objectfrontier.land.service.impl.TopicServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author rebekkal.pangras
 * @author SriramNarayanan
 * @author mani.chellapandian
 * @author jayanth.subramanian
 * @since 1.0.0
 * <p>
 * This Controller manages the request and response for the all the Forum
 * related service
 */
@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {

    @Autowired
    DiscussionServiceImpl discussionService;

    @Autowired
    DiscussionFollowerServiceImpl discussionFollowerService;

    @Autowired
    DiscussionCommentServiceImpl discussionCommentService;

    @Autowired
    DiscussionTopicServiceImpl discussionTopicService;

    @Autowired
    CommentNotificationServiceImpl commentNotificationService;

    @Autowired
    TopicServiceImpl topicService;

    @ApiOperation(value = "FindCommentsByDiscussionId(Find the comments for a discussion)",
                  notes = "Find the comments for a discussion",
                  response = DiscussionCommentWithFollowerDTO.class)
    @GetMapping("/comment/discussion/{discussionId}/{userId}")
    public ResponseEntity<DiscussionCommentWithFollowerDTO> findCommentsByDiscussionId(
            @PathVariable("discussionId") long discussionId,
            @PathVariable("userId") long userId,
            @RequestParam("pageNo") int pageNumber,
            @RequestParam("offset") int discussionCommentCount) {

        List<DiscussionComment> discussionComments = discussionCommentService.findByDiscussionId(discussionId,
                pageNumber, discussionCommentCount);
        List<DiscussionCommentDTO> discussionCommentDTOs = ModelMapperUtil.mapAll(discussionComments,
                DiscussionCommentDTO.class);
        DiscussionFollower discussionFollower = discussionFollowerService.findByUserIdAndDiscussionId(userId,
                discussionId);
        DiscussionCommentWithFollowerDTO discussionCommentWithFollowerDTO = new DiscussionCommentWithFollowerDTO(
                discussionFollower, discussionCommentDTOs);

        return new ResponseEntity<DiscussionCommentWithFollowerDTO>(discussionCommentWithFollowerDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "CreateDiscussionFollower(Follow response)",
                  notes = "Follow response",
                  response = DiscussionFollowerDTO.class)
    @PostMapping("/follower")
    public ResponseEntity<DiscussionFollowerDTO> createDiscussionFollower(
            @RequestBody DiscussionFollowerDTO discussionFollower) {

        DiscussionFollower createdDiscussionFollower = discussionFollowerService
                .create(ModelMapperUtil.map(discussionFollower, DiscussionFollower.class));
        return new ResponseEntity<DiscussionFollowerDTO>(
                ModelMapperUtil.map(createdDiscussionFollower, DiscussionFollowerDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "DeleteDiscussionFollower(Unfollow response)",
                  notes = "Unfollow response")
    @DeleteMapping("/follower/{id}")
    public ResponseEntity<Void> deleteDiscussionFollower(@PathVariable("id") long id) {

        discussionFollowerService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "createComment(Post a comment)",
                  notes = "Post a comment",
                  response = DiscussionCommentDTO.class)
    @PostMapping("/comment")
    public ResponseEntity<DiscussionCommentDTO> createComment(@RequestBody DiscussionCommentDTO discussionCommentDTO) {

        DiscussionComment discussionComment = ModelMapperUtil.map(discussionCommentDTO, DiscussionComment.class);
        discussionComment.setCreatedDate(new Date(System.currentTimeMillis()));

        DiscussionComment createdComment = discussionCommentService.create(discussionComment);

        Long discussionId = createdComment.getDiscussion().getId();

        List<DiscussionFollower> discussionFollowers = discussionFollowerService.findByDiscussionId(discussionId);

        List<CommentNotification> commentNotifications = ForumUtil.convertToCommentNotification(createdComment,
                discussionFollowers);

        commentNotificationService.createAll(commentNotifications);

        return new ResponseEntity<DiscussionCommentDTO>(ModelMapperUtil.map(createdComment, DiscussionCommentDTO.class),
                HttpStatus.OK);
    }

    @ApiOperation(value = "CreateDiscussion(Create a discussion)",
                  notes = "Create a discussion",
                  response = DiscussionDTO.class)
    @PostMapping("/discussion")
    public ResponseEntity<DiscussionDTO> createDiscussion(@RequestBody DiscussionDTO discussionDTO) {

        Discussion discussion = ModelMapperUtil.map(discussionDTO, Discussion.class);
        discussion.setCreatedDate(new Date(System.currentTimeMillis()));

        Discussion createdDiscussion = discussionService.create(discussion);

        DiscussionFollower discussionFollower = new DiscussionFollower(createdDiscussion,
                createdDiscussion.getDiscussionCreator());
        discussionFollowerService.create(discussionFollower);

        List<DiscussionTopicLinkDTO> discussionTopicLinkDTOs = discussionDTO.getDiscussionTopics();
        List<DiscussionTopic> discussionTopicLinks = ForumUtil.convertToDiscussionTopicLinks(createdDiscussion,
                discussionTopicLinkDTOs);
        List<DiscussionTopic> createdDiscussionTopics = discussionTopicService.createAll(discussionTopicLinks);

        List<DiscussionTopicLinkDTO> createdDiscussionTopicLinkDTOs = ModelMapperUtil.mapAll(createdDiscussionTopics,
                DiscussionTopicLinkDTO.class);

        DiscussionDTO createdDiscussionDTO = ModelMapperUtil.map(createdDiscussion, DiscussionDTO.class);
        createdDiscussionDTO.setDiscussionTopics(createdDiscussionTopicLinkDTOs);

        return new ResponseEntity<DiscussionDTO>(createdDiscussionDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Find discussions containing the searchInput in its title or description.",
                  notes = "All discussions that contains the searchInput in its title or description.",
                  response = DiscussionDTO.class)
    @GetMapping("/discussion/search/{searchInput}")
    public ResponseEntity<List<DiscussionDTO>> searchDiscussion(@PathVariable("searchInput") String searchInput,
                                                                @RequestParam("pageNo") int pageNumber,
                                                                @RequestParam("offset") int offset) {

        List<DiscussionDTO> discussionDtos = ModelMapperUtil
                .mapAll(discussionService.search(searchInput, pageNumber, offset), DiscussionDTO.class);
        return new ResponseEntity<List<DiscussionDTO>>(discussionDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Read discussions under a topic, paginated.",
                  notes = "All discussions under a topic is retrieved according to the paginatation arguements.",
                  response = DiscussionTopicDTO.class)
    @GetMapping("/topic/{id}")
    public ResponseEntity<List<DiscussionTopicDTO>> findDiscussionByTopic(@PathVariable("id") long id,
                                                                          @RequestParam("pageNo") int pageNumber,
                                                                          @RequestParam("offset") int discussionCount) {

        List<DiscussionTopic> discussionUnderEachTopic = discussionTopicService.findByTopicId(id, pageNumber,
                discussionCount);
        return new ResponseEntity<List<DiscussionTopicDTO>>(
                ModelMapperUtil.mapAll(discussionUnderEachTopic, DiscussionTopicDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "findAllTopicInCourse",
                  notes = "To find all the topic in the course",
                  response = TopicDTO.class)
    @GetMapping("/course/{id}")
    public ResponseEntity<List<TopicDTO>> findAllTopicInCourse(@PathVariable("id") long id) {

        List<Topic> topics = topicService.findByCourseId(id);
        return new ResponseEntity<List<TopicDTO>>(ModelMapperUtil.mapAll(topics, TopicDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "FindDiscussionsWithFollowerFilter",
                  notes = "Get the discussions that the user follows",
                  response = DiscussionDTO.class)
    @GetMapping("/discussion/following/{courseId}/{userId}")
    public ResponseEntity<List<DiscussionDTO>> findDiscussionsThatAreFollowed(@PathVariable("courseId") long courseId,
            @PathVariable("userId") long userId,
            @RequestParam("pageNo") int pageNumber,
            @RequestParam("offset") int discussionCount) {

        List<Discussion> discussions = findDiscussionsByCourseId(courseId);

        List<DiscussionFollower> discussionFollowers = discussionFollowerService.findByDiscussionsAndUserId(discussions,
                userId, pageNumber, discussionCount);

        List<Discussion> discussionsFollowed = new ArrayList<>();
        discussionFollowers.forEach(discussionFollower -> discussionsFollowed.add(discussionFollower.getDiscussion()));

        List<DiscussionDTO> discussionDTOs = ModelMapperUtil.mapAll(discussionsFollowed, DiscussionDTO.class);
        return new ResponseEntity<List<DiscussionDTO>>(discussionDTOs, HttpStatus.OK);
    }

    @ApiOperation(value = "FindDiscussionsSortedByRecent",
                 notes = "Get the discussions sort by recent",
                 response = DiscussionDTO.class)
    @GetMapping("/discussion/recent/{courseId}")
    public ResponseEntity<List<DiscussionDTO>> findDiscussionsSortByRecent(@PathVariable("courseId") long courseId,
            @RequestParam("pageNo") int pageNumber,
            @RequestParam("offset") int discussionCount) {

        List<Discussion> discussions = findDiscussionsByCourseId(courseId);

        List<Discussion> sortedDiscussions = discussionService.sortedByRecent(discussions, pageNumber, discussionCount);
        List<DiscussionDTO> discussionDTOs = ModelMapperUtil.mapAll(sortedDiscussions, DiscussionDTO.class);
        return new ResponseEntity<List<DiscussionDTO>>(discussionDTOs, HttpStatus.OK);
    }

    @ApiOperation(value = "readDiscussionsSortedByRecentlyCommented",
                  notes = "Get the discussions sort by recently commented",
                  response = DiscussionDTO.class)
    @GetMapping("/discussion/recent/comment/{courseId}")
    public ResponseEntity<List<DiscussionDTO>> findDiscussionsSortByRecentComment(
            @PathVariable("courseId") long courseId,
            @RequestParam("pageNo") int pageNumber,
            @RequestParam("offset") int discussionCount) {

        List<Discussion> discussions = findDiscussionsByCourseId(courseId);

        List<DiscussionComment> comments = discussionCommentService.findByDiscussionIdsOrderByCreatedDate(discussions);

        List<Discussion> sortedDiscussions = new ArrayList<>();
        comments.forEach(comment -> sortedDiscussions.add(comment.getDiscussion()));

        List<Discussion> sortedDisctinctDiscussions = sortedDiscussions.stream().distinct()
                .collect(Collectors.toList());

        List<Discussion> recentlyCommentedDiscussions = ForumUtil.getPage(sortedDisctinctDiscussions, pageNumber,
                discussionCount);

        List<DiscussionDTO> discussionDTOs = ModelMapperUtil.mapAll(recentlyCommentedDiscussions, DiscussionDTO.class);
        return new ResponseEntity<List<DiscussionDTO>>(discussionDTOs, HttpStatus.OK);
    }

    private List<Discussion> findDiscussionsByCourseId(long courseId) {

        List<Topic> topicsInGivenCourse = topicService.findByCourseId(courseId);

        List<DiscussionTopic> discussionsForThePrimaryTopics = discussionTopicService
                .findByListOfPrimaryTopic(topicsInGivenCourse);

        List<Discussion> discussions = new ArrayList<>();
        discussionsForThePrimaryTopics.forEach(discussion -> discussions.add(discussion.getDiscussion()));
        return discussions;
    }
}
