package com.objectfrontier.land.dto;

import java.sql.Time;
import java.util.List;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class SubtopicDTO {

    private long id;

    private String name; 

    private String description;

    private Time fastrackDuration;

    private Time fulltrackDuration;

    private MediaDTO media;

    private UserDTO author;

    private int displayOrder;

    private List<SubtopicTagDTO> subTopicTags;

}
