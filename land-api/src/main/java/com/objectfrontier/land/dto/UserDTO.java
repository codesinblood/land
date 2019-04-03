package com.objectfrontier.land.dto;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @author gunasekaran.k
 * @since v1.0
 */

@Data
public class UserDTO {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private Timestamp lastLoggedIn;

    private boolean activeStatus;

}
