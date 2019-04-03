package com.objectfrontier.land.dto;

import java.sql.Time;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class EvaluationDTO {

    private long id;

    private int maxAttempt;

    private long passCriteria;

    private Time fastrackRecapDuration;

    private Time fastrackReviewDuration;

    private Time fulltrackRecapDuration;

    private Time fulltrackReviewDuration;

    private int displayOrder;

}
