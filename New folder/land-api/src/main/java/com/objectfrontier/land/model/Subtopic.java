package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.FASTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.FULLTRACK_DURATION;
import static com.objectfrontier.land.common.Constant.RESOURCE_ID;
import static com.objectfrontier.land.common.Constant.SUBTOPIC;
import static com.objectfrontier.land.common.Constant.SUBTOPIC_ID;
import static com.objectfrontier.land.common.Constant.SUBTOPIC_TABLE;
import static com.objectfrontier.land.common.Constant.USER_ID;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author sugandapriya.l
 * @since v1.0
 */

@Entity(name = SUBTOPIC)
@Table(name = SUBTOPIC_TABLE)
@Data
public class Subtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = FASTRACK_DURATION)
    private Time fastrackDuration;

    @Column(name = FULLTRACK_DURATION)
    private Time fulltrackDuration;

    @Column(name = DISPLAY_ORDER)
    private int displayOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = RESOURCE_ID)
    private Media media;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ID)
    private User author;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = SUBTOPIC_ID)
    private List<SubtopicTag> subTopicTags;
}
