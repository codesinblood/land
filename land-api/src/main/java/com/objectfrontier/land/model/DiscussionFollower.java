package com.objectfrontier.land.model;

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
 * @since 1.0.0
 * <p>
 * Model class which holds the fields of the Discussion Follower
 */

@Entity(name = "DiscussionFollower")
@Table(name = "discussion_follower")
@Data
@NoArgsConstructor
public class DiscussionFollower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User follower;

    /**
     * @param discussion
     * @param follower
     */
    public DiscussionFollower(Discussion discussion, User user) {
        super();
        this.discussion = discussion;
        this.follower = user;
    }
}
