package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.AUTHOR_FINAL;
import static com.objectfrontier.land.common.Constant.COURSE;
import static com.objectfrontier.land.common.Constant.COURSE_ID;
import static com.objectfrontier.land.common.Constant.COURSE_TABLE;
import static com.objectfrontier.land.common.Constant.CREATED_DATE;
import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.IS_SELF_ASSIGNABLE;
import static com.objectfrontier.land.common.Constant.NAME;
import static com.objectfrontier.land.common.Constant.PUBLISHED;
import static com.objectfrontier.land.common.Constant.RESOURCE_ID;
import static com.objectfrontier.land.common.Constant.USER_ID;
import static com.objectfrontier.land.common.Constant.USER_ROLE_COURSE;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jayanth.subramanian
 * @since v1.0
 * Class defining the fields for the Course entity and maps it to the respective DB table
 */

@Entity(name = COURSE)
@Table(name = COURSE_TABLE)
@Data
public class Course {

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = NAME, length = 30)
    private String name;

    @Column(name = DESCRIPTION, length = 100)
    private String description;

    @Column(name = IS_SELF_ASSIGNABLE)
    private boolean isSelfAssignable;

    @Column(name = CREATED_DATE)
    private Date createdDate;

    @Column(name = AUTHOR_FINAL)
    private boolean authorFinal;

    @Column(name = PUBLISHED)
    private boolean published;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = RESOURCE_ID)
    private Media media;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = USER_ROLE_COURSE,
        joinColumns = {@JoinColumn(name = COURSE_ID)}, inverseJoinColumns = {@JoinColumn(name = USER_ID)}
    )
    private List<User> authors;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = COURSE_ID)
    private List<Topic> topics;

}
