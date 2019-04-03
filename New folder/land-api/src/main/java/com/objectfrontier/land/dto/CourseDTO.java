package com.objectfrontier.land.dto;

import java.util.Date;
import java.util.List;

import com.objectfrontier.land.model.User;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class CourseDTO {

    private long id;

    private String name;

    private String description;

    private boolean isSelfAssignable;

    private Date createdDate;

    private MediaDTO media;

    private boolean authorFinal;

    private boolean published;

    private List<TopicDTO> topics;

    private List<User> authors;

}
