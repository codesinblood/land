package com.objectfrontier.land.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * Model class which holds the fields of the Comment Notification
 */

@Entity(name = "CommentNotification")
@Table(name = "comment_notification")
@Data
@NoArgsConstructor
public class CommentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "discussion_comment_id")
    private DiscussionComment discussionComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "has_seen")
    private boolean hasSeen;

    /**
     * @param discussionComment
     * @param user
     * @param hasSeen
     */
    public CommentNotification(DiscussionComment discussionComment, User user, boolean hasSeen) {
        super();
        this.discussionComment = discussionComment;
        this.user = user;
        this.hasSeen = hasSeen;
    }
}
