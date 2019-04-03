package com.objectfrontier.land.service;

import com.objectfrontier.land.common.exception.EmailException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.config.MailConfiguration;
import com.objectfrontier.land.model.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author isaac.murugaian
 * @since v1.0
 */

@Service
public class MailService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailConfiguration mailConfiguration;

    private String mailContentKey = "mailInfo";

    /**
     * send mail method send mail
     *
     * @param - send mail holds mail ids, subject, context and mailInfo
     */

    public void sendMail(MailDetails mailDetails) {

        try {

            JavaMailSender mailConfig = mailConfiguration.javaMailSender();

            MimeMessage message = mailConfig.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context dymanicContentInMail = new Context();
            dymanicContentInMail.setVariable(mailContentKey, mailDetails.getMailInfo());
            String textBody = templateEngine.process(mailDetails.getContext().getTemplateName(), dymanicContentInMail);

            helper.setTo(mailDetails.getToSender());
            helper.setSubject(mailDetails.getSubject());
            helper.setText(textBody, true);

            mailConfig.send(message);

        } catch (MessagingException | MailException e) {
            throw new EmailException(ErrorCode.MAIL_SERVER_ERROR);
        } catch (TemplateInputException e) {
            throw new EmailException(ErrorCode.MAIL_SERVER_ERROR);
        }
    }
}
