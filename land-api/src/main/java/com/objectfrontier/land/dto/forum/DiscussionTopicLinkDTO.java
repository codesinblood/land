package com.objectfrontier.land.dto.forum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing some fields from DiscussionTopicLink class
 */

@Data
@NoArgsConstructor
public class DiscussionTopicLinkDTO {

    @ApiModelProperty(notes = "The database generated discussionTopicLink ID")
    private long id;
    
    @ApiModelProperty(notes = "Topic related to a discussion")
    private TopicDTO topic;
    
    @ApiModelProperty(notes = "Boolean to check if the topic is primary for a given discussion")
    private boolean isPrimary;

    /**
     * @param id
     * @param topic
     * @param isPrimary
     */
    public DiscussionTopicLinkDTO(long id, TopicDTO topic, boolean isPrimary) {
        super();
        this.id = id;
        this.topic = topic;
        this.isPrimary = isPrimary;
    }
}
