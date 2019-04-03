package com.objectfrontier.land.dto;

import lombok.Data;

/**
 * @author karthik.n
 * @since v1.0
 */

@Data
public class ReferenceDTO {

    private long id;

    private String description;

    private String link;

    private int displayOrder;
}
