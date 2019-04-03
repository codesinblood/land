package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.EXERCISE;
import static com.objectfrontier.land.common.Constant.EXERCISE_TABLE;
import static com.objectfrontier.land.common.Constant.FASTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.FULLTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.NAME;
import static com.objectfrontier.land.common.Constant.RECAP_FASTRACK;
import static com.objectfrontier.land.common.Constant.RECAP_FULLTRACK;
import static com.objectfrontier.land.common.Constant.RESOURCE_ID;
import static com.objectfrontier.land.common.Constant.REVIEW_FASTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.REVIEW_FULLTRACK_DURATION;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author venkatesh.k
 * @author karthik.n
 * @since v1.0
 */

@Entity(name = EXERCISE)
@Table(name = EXERCISE_TABLE)
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = RESOURCE_ID)
    private Resource resource;

    @Column(name = FASTRACK_DURATION)
    private Time fastrackDuration;

    @Column(name = FULLTRACK_DURATION)
    private Time fulltrackDuration;

    @Column(name = REVIEW_FASTRACK_DURATION)
    private Time reviewFastrackDuration;

    @Column(name = REVIEW_FULLTRACK_DURATION)
    private Time reviewFulltrackDuration;

    @Column(name = RECAP_FASTRACK)
    private Time recapFastrack;

    @Column(name = RECAP_FULLTRACK)
    private Time recapFulltrack;

    @Column(name = DISPLAY_ORDER)
    private int displayOrder;

}
