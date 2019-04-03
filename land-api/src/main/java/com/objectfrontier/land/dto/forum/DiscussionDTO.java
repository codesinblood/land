package com.objectfrontier.land.dto.forum;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing some fields from Discussion class
 */

@Data
@NoArgsConstructor
public class DiscussionDTO {

    @ApiModelProperty(notes = "The database generated discussion ID")
    private long id;

    @ApiModelProperty(notes = "Contains the title of the discussion")
    private String title;

    @ApiModelProperty(notes = "Contains the description of the discussion")
    private String description;

    @ApiModelProperty(notes = "Contains the user detail who created the discussion")
    private UserDTO discussionCreator;

    @ApiModelProperty(notes = "Contains the created date of the discussion")
    private Date createdDate;

    @ApiModelProperty(notes = "Contains the list of topics for which the discussion is created")
    private List<DiscussionTopicLinkDTO> discussionTopics;

    /**
     * @param id
     * @param title
     * @param description
     * @param discussionCreator
     * @param createdDate
     * @param discussionTopics
     */
    public DiscussionDTO(long id, String title, String description, UserDTO user, Date createdDate,
            List<DiscussionTopicLinkDTO> discussionTopicLinks) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.discussionCreator = user;
        this.createdDate = createdDate;
        this.discussionTopics = discussionTopicLinks;
    }
}
