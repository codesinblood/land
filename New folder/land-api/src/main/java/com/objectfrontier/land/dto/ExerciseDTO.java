package com.objectfrontier.land.dto;

import java.sql.Time;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class ExerciseDTO {

    private long id;

    private String name;

    private String description;

    private Time fastrackDuration;

    private Time fulltrackDuration;

    private Time reviewFastrackDuration;

    private Time reviewFulltrackDuration;

    private Time recapFastrack;

    private Time recapFulltrack;

    private MediaDTO media;

    private int displayOrder;
}
