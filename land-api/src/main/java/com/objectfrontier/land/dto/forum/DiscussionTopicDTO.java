package com.objectfrontier.land.dto.forum;

import com.objectfrontier.land.model.Discussion;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing discussion class and some fields from
 * DiscussionTopicLink class
 */

@Data
public class DiscussionTopicDTO {

    @ApiModelProperty(notes = "The database generated discussionTopicLink ID")
    private long id;
    
    @ApiModelProperty(notes = "Discussion related to a topic")
    private Discussion discussion;
    
    @ApiModelProperty(notes = "Topic related to a discussion")
    private TopicDTO topic;
}
