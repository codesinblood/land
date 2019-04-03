package com.objectfrontier.land.dto.forum;

import java.util.List;

import com.objectfrontier.land.model.DiscussionFollower;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO containing the follower field and list of discussionComment dto
 */

@Data
@NoArgsConstructor
public class DiscussionCommentWithFollowerDTO {

    @ApiModelProperty(notes = "DiscussionFollower object which tells if the person following the discussion")
    private DiscussionFollower discussionFollower;
    
    @ApiModelProperty(notes = "List of comments for the given discussion")
    private List<DiscussionCommentDTO> discussionCommentDTOs;

    /**
     * @param discussionFollower
     * @param discussionCommentDTOs
     */
    public DiscussionCommentWithFollowerDTO(DiscussionFollower discussionFollower,
            List<DiscussionCommentDTO> discussionCommentDTOs) {
        super();
        this.discussionFollower = discussionFollower;
        this.discussionCommentDTOs = discussionCommentDTOs;
    }
}
