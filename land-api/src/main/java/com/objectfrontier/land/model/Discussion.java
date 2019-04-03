package com.objectfrontier.land.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Model class which holds the fields of the Discussion
 */

@Entity(name = "Discussion")
@Table(name = "discussion")
@Data
@NoArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User discussionCreator;

    @Column(name = "created_date")
    private Date createdDate;

    /**
     * @param title
     * @param description
     * @param discussionCreator
     * @param createdDate
     */
    public Discussion(String title, String description, User user, Date createdDate) {
        super();
        this.title = title;
        this.description = description;
        this.discussionCreator = user;
        this.createdDate = createdDate;
    }
}
