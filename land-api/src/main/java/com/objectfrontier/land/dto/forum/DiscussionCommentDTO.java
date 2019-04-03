package com.objectfrontier.land.dto.forum;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing some fields from DiscussionComment class
 */

@Data
public class DiscussionCommentDTO {

    @ApiModelProperty(notes = "The database generated discussion comment ID")
    private long id;
    
    @ApiModelProperty(notes = "Contains the comment of the discussion")
    private String comment;
    
    @ApiModelProperty(notes = "Discussion related to a topic")
    private DiscussionDTO discussion;
    
    @ApiModelProperty(notes = "Contains the user who made the comment")
    private UserDTO commenter;
    
    @ApiModelProperty(notes = "Boolean to mark the comment as correct or wrong")
    private boolean status;
    
    @ApiModelProperty(notes = "Contains the created date of the comment")
    private Date createdDate;
}
