package com.objectfrontier.land.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * Model class which holds the fields of the LAND user
 */

@Entity(name = "User")
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "last_logged_in")
    private Timestamp lastLoggedIn;
    @Column(name = "is_active")
    private boolean activeStatus;
    
    public User() {
        super();
    }

    public User(long id, String firstName, String lastName, String email, Timestamp lastLoggedIn, boolean activeStatus) {

        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lastLoggedIn = lastLoggedIn;
        this.activeStatus = activeStatus;
    }

    public User(String firstName, String lastName, String email, Timestamp lastLoggedIn, boolean activeStatus) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lastLoggedIn = lastLoggedIn;
        this.activeStatus = activeStatus;
    }

}
