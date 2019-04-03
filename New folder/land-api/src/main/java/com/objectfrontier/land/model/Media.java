package com.objectfrontier.land.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @author Sriram Narayanan
 * @since v1.0
 *        Model class which holds the fields of resource table.
 */
@Entity(name = "resource")
@Table(name = "resource")
@NoArgsConstructor
@Data
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated resource ID")
    @Column(name = "id")
    private long id;

    @ApiModelProperty(notes = "Contains the original file name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "Holds the local destination URL of the uploaded file")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(notes = "Holds the type of the uploaded file")
    @Column(name = "type")
    private String type;

    public Media(long id, String mediaName, String mediaUrl, String mediaType) {
        super();
        this.id = id;
        this.name = mediaName;
        this.url = mediaUrl;
        this.type = mediaType;
    }

    public Media(String mediaName, String mediaUrl, String mediaType) {
        super();
        this.name = mediaName;
        this.url = mediaUrl;
        this.type = mediaType;
    }
}

