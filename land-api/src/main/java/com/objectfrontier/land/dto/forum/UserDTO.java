package com.objectfrontier.land.dto.forum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rebekkal.pangras
 * @since 1.0.0
 * <p>
 * DTO class containing some fields from User class
 */
@Data
@NoArgsConstructor
public class UserDTO {

    @ApiModelProperty(notes = "The database generated User ID")
    private long id;
    
    @ApiModelProperty(notes = "Holds the first name of the User")
    private String firstName;
    
    @ApiModelProperty(notes = "Holds the last name of the User")
    private String lastName;
}
