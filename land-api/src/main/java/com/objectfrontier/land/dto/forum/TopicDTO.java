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
public class TopicDTO {

    @ApiModelProperty(notes = "The database generated Topic ID")
    private long id;
    
    @ApiModelProperty(notes = "Holds the name of the Topic")
    private String name;
}
