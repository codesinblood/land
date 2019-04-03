package com.objectfrontier.land.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since v1.0
 * <p>
 * Model class which holds the fields of the Discussion Comment
 */

@Entity(name = "DiscussionComment")
@Table(name = "discussion_comment")
@Data
@NoArgsConstructor
public class DiscussionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User commenter;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private boolean status;
}
