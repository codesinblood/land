package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.AUTHOR_ID;
import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.NAME;
import static com.objectfrontier.land.common.Constant.TOPIC;
import static com.objectfrontier.land.common.Constant.TOPIC_AUTHOR;
import static com.objectfrontier.land.common.Constant.TOPIC_ID;
import static com.objectfrontier.land.common.Constant.TOPIC_TABLE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author karthik.n
 * @author mani.chellapandian
 * @since v1.0
 */

@Entity(name = TOPIC)
@Table(name = TOPIC_TABLE)
@Data
public class Topic {

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated Topic ID")
    private long id;

    @Column(name = NAME)
    @ApiModelProperty(notes = "Holds the name of the Topic")
    private String name;

    @Column(name = DESCRIPTION)
    @ApiModelProperty(notes = "holds the description for each topic")
    private String description;

    @Column(name = DISPLAY_ORDER)
    @ApiModelProperty(notes = "it maintain display order")
    private int displayOrder;
    
    @Column(name = "course_id")
    @ApiModelProperty(notes = "it maintain display order")
    private long courseId;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = TOPIC_AUTHOR
            , joinColumns = {@JoinColumn(name = TOPIC_ID)}
            , inverseJoinColumns = {@JoinColumn(name = AUTHOR_ID)}
            )
    private List<User> authors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<Subtopic> subTopics;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<OnlineTest> onlineTests;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<Reference> references;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<Exercise> exercises;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<Evaluation> evaluations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = TOPIC_ID)
    private List<EvaluationParam> evaluationParams;
}
