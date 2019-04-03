package com.objectfrontier.land.dto;

import lombok.Data;

@Data
public class MediaDTO {

    private long id;

    private String type;

    private String name;

    private Object encodedMedia;
    
    private String mediaPath;
}
