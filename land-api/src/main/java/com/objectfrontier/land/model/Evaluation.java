package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.EVALUATION;
import static com.objectfrontier.land.common.Constant.EVALUATION_MAX_ATTEMPT;
import static com.objectfrontier.land.common.Constant.EVALUATION_PASS_CRITERIA;
import static com.objectfrontier.land.common.Constant.EVALUATION_TABLE;
import static com.objectfrontier.land.common.Constant.FASTRACK_RECAP_DURATION;
import static com.objectfrontier.land.common.Constant.FASTRACK_REVIEW_DURATION;
import static com.objectfrontier.land.common.Constant.FULLTRACK_RECAP_DURATION;
import static com.objectfrontier.land.common.Constant.FULLTRACK_REVIEW_DURATION;
import static com.objectfrontier.land.common.Constant.ID;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author isaac.murugaian
 * @author karthik.n
 * @since v1.0
 */

@Entity(name = EVALUATION)
@Table(name = EVALUATION_TABLE)
@Data
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = EVALUATION_MAX_ATTEMPT)
    private int maxAttempt;

    @Column(name = EVALUATION_PASS_CRITERIA)
    private long passCriteria;

    @Column(name = FASTRACK_RECAP_DURATION)
    private Time fastrackRecapDuration;

    @Column(name = FASTRACK_REVIEW_DURATION)
    private Time fastrackReviewDuration;

    @Column(name = FULLTRACK_RECAP_DURATION)
    private Time fulltrackRecapDuration;

    @Column(name = FULLTRACK_REVIEW_DURATION)
    private Time fulltrackReviewDuration;

    @Column(name = DISPLAY_ORDER)
    private int displayOrder;

}
