package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.NAME;
import static com.objectfrontier.land.common.Constant.SUBTOPIC_TAG;
import static com.objectfrontier.land.common.Constant.SUBTOPIC_TAG_TABLE;

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

@Entity(name = SUBTOPIC_TAG)
@Table(name = SUBTOPIC_TAG_TABLE)
@Data
public class SubtopicTag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = NAME)
    private String name;

}
