package com.objectfrontier.land.model;

import com.objectfrontier.land.common.MailContext;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author isaac.murugaian
 */

@Data
@Getter
@Setter
public class MailDetails {

    String[] toSender;
    String subject;
    String mailInfo;
    String cc;
    MailContext context;

}
