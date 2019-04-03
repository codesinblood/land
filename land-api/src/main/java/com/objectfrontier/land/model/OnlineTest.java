package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.FASTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.FASTRACK_MAX_ATTEMPT;
import static com.objectfrontier.land.common.Constant.FULLTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.FULLTRACK_MAX_ATTEMPT;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.ONLINE_TEST;
import static com.objectfrontier.land.common.Constant.ONLINE_TEST_TABLE;
import static com.objectfrontier.land.common.Constant.PASS_PERCENTAGE;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Entity(name = ONLINE_TEST)
@Table(name = ONLINE_TEST_TABLE)
@Data
public class OnlineTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = PASS_PERCENTAGE)
    private double passPercentage;

    @Column(name = FASTRACK_MAX_ATTEMPT)
    private int fastrackMaxAttempt;

    @Column(name = FASTRACK_DURATION)
    private Time fastrackDuration;

    @Column(name = FULLTRACK_MAX_ATTEMPT)
    private int fulltrackMaxAttempt;

    @Column(name = FULLTRACK_DURATION)
    private Time fulltrackDuration;

    @Column(name = DISPLAY_ORDER)
    private int displayOrder;
}
