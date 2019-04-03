package com.objectfrontier.land.common.utility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.objectfrontier.land.dto.forum.DiscussionTopicLinkDTO;
import com.objectfrontier.land.model.CommentNotification;
import com.objectfrontier.land.model.Discussion;
import com.objectfrontier.land.model.DiscussionComment;
import com.objectfrontier.land.model.DiscussionFollower;
import com.objectfrontier.land.model.DiscussionTopic;
import com.objectfrontier.land.model.Topic;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 */

public class ForumUtil {

    /**
     * @param createdComment     
     * @param discussionFollowers 
     * @return commentNotifications 
     */
    public static List<CommentNotification> convertToCommentNotification(DiscussionComment createdComment,
            List<DiscussionFollower> discussionFollowers) {

        List<CommentNotification> commentNotifications = discussionFollowers.stream()
                .map(discussionFollower -> new CommentNotification(createdComment,
                                                                   discussionFollower.getFollower(),
                                                                   false))
                .collect(Collectors.toList());
        return commentNotifications;
    }
    
    /**
     * @param createdDiscussion       
     * @param discussionTopicLinkDTOs 
     * @return discussionTopicLinks 
     */

    public static List<DiscussionTopic> convertToDiscussionTopicLinks(Discussion createdDiscussion,
            List<DiscussionTopicLinkDTO> discussionTopicLinkDTOs) {

        List<DiscussionTopic> discussionTopicLinks = discussionTopicLinkDTOs.stream()
                .map(discussionTopicLinkDTO -> new DiscussionTopic(createdDiscussion,
                        new ModelMapper().map(discussionTopicLinkDTO.getTopic(), Topic.class), discussionTopicLinkDTO.isPrimary()))
                .collect(Collectors.toList());
        return discussionTopicLinks;
    }

    /**
     * Pagination
     * @param sourceList
     * @param page
     * @param pageSize
     * @return List of data
     */
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page < 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize + " or page number: " + page);
        }

        int fromIndex = (page) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }
        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
}
