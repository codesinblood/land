package com.objectfrontier.land.common;

/**
 * @author isaac.murugaian
 */

public enum MailContext {

    /**
     * This class is defined for mapping html template based on context
     */
    AUTHOR_INVITATION_TEMPLATE("mail.author.invitation"),
    PLAIN_TEXT_TEMPLATE("mail.simple.text");

    private String templateName;

    private MailContext(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }


}
