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
 * @since v1.0
 * <p>
 * Model class which holds the fields of the Discussion Topic link
 */

@Entity(name = "DiscussionTopic")
@Table(name = "discussion_topic")
@Data
@NoArgsConstructor
public class DiscussionTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "is_primary")
    private boolean isPrimary;

    /**
     * @param discussion
     * @param topic
     * @param isPrimary
     */
    public DiscussionTopic(Discussion discussion, Topic topic, boolean isPrimary) {
        super();
        this.discussion = discussion;
        this.topic = topic;
        this.isPrimary = isPrimary;
    }
}
