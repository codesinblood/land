package com.objectfrontier.land.model;

import static com.objectfrontier.land.common.Constant.DESCRIPTION;
import static com.objectfrontier.land.common.Constant.DISPLAY_ORDER;
import static com.objectfrontier.land.common.Constant.ID;
import static com.objectfrontier.land.common.Constant.LINK;
import static com.objectfrontier.land.common.Constant.REFERENCE;
import static com.objectfrontier.land.common.Constant.REFERENCE_TABLE;

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

@Entity(name = REFERENCE)
@Table(name = REFERENCE_TABLE)
@Data
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = LINK)
    private String link;

    @Column(name = DISPLAY_ORDER)
    private int displayOrder;
}
