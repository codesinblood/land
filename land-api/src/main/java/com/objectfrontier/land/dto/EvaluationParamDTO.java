package com.objectfrontier.land.dto;

import java.util.List;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class EvaluationParamDTO {

    private long id;

    private String param;

    private String description;

    private double weightage;

    private double marMark;

    private List<EvaluationDTO> evaluationLists;

}
