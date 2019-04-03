package com.objectfrontier.land.dto;

import java.util.List;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class TopicDTO {

    private long id;

    private String name;

    private String description;

    private int displayOrder;

    private List<UserDTO> authors;

    private List<SubtopicDTO> subTopics;

    private List<OnlineTestDTO> onlineTests;

    private List<ReferenceDTO> references;

    private List<ExerciseDTO> exercises;

    private List<EvaluationDTO> evaluations;

    private List<EvaluationParamDTO> evaluationParams;

}
