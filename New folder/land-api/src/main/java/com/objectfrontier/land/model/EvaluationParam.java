package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.EVALUATION_ID;
import static com.objectfrontier.land.common.Constant.EVALUATION_PARAM;
import static com.objectfrontier.land.common.Constant.EVALUATION_PARAM_LIST;
import static com.objectfrontier.land.common.Constant.EVALUATION_PARAM_TABLE;
import static com.objectfrontier.land.common.Constant.EVALUATION_WEIGHTAGE;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.MAX_MARK;
import static com.objectfrontier.land.common.Constant.PARAM;
import static com.objectfrontier.land.common.Constant.PARAM_ID;

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
import javax.persistence.Table;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Entity(name = EVALUATION_PARAM)
@Table(name = EVALUATION_PARAM_TABLE)
@Data
public class EvaluationParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = PARAM)
    private String param;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = EVALUATION_WEIGHTAGE)
    private double weightage;

    @Column(name = MAX_MARK)
    private double marMark;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = EVALUATION_PARAM_LIST
        , joinColumns = {@JoinColumn(name = PARAM_ID)}
        , inverseJoinColumns = {@JoinColumn(name = EVALUATION_ID)}
    )
    private List<Evaluation> evaluationLists;

}
