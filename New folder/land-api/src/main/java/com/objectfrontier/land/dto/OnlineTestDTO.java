package com.objectfrontier.land.dto;

import java.sql.Time;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class OnlineTestDTO {

    private long id;

    private String description;

    private double passPercentage;

    private int fastrackMaxAttempt;

    private Time fastrackDuration;

    private int fulltrackMaxAttempt;

    private Time fulltrackDuration;

    private int displayOrder;
}
