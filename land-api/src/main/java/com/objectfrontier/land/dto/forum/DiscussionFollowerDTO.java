package com.objectfrontier.land.dto.forum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing some fields from DiscussionFollower class
 */

@Data
public class DiscussionFollowerDTO {

    @ApiModelProperty(notes = "The database generated discussion follower ID")
    private long id;

    @ApiModelProperty(notes = "Discussion which is followed by a user")
    private DiscussionDTO discussion;

    @ApiModelProperty(notes = "User who follows a discussion")
    private UserDTO follower;
}
