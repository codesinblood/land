/*
 * MailConfiguration class is used to access all the properties using the spring environment object
 */

package com.objectfrontier.land.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author subbarama.r
 * @since Feb 11, 2019
 */

@Component
public class MailConfiguration {

    private static final String PROP_USER = "mail.user";
    private static final String PROP_PASSWORD = "mail.password";
    private static final String PROP_HOST = "mail.host";
    private static final String PROP_PORT = "mail.port";
    private static final String PROP_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String PROP_PROTO = "protocol";
    private static final String PROP_TLS = "tls";
    private static final String PROP_AUTH = "auth";
    private static final String PROP_SMTP_AUTH = "mail.smtp.auth";
    private static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    @Autowired
    private Environment environment;

    /**
     * accessing properties from application.properties using environment object
     */

    @Bean
    public JavaMailSenderImpl javaMailSender() {

        String user = environment.getProperty(PROP_USER);
        String password = environment.getProperty(PROP_PASSWORD);
        String host = environment.getProperty(PROP_HOST);
        int port = Integer.parseInt(environment.getProperty(PROP_PORT));
        String protocol = environment.getProperty(PROP_PROTO);
        Boolean auth = Boolean.parseBoolean(environment.getProperty(PROP_AUTH));
        Boolean tls = Boolean.parseBoolean(environment.getProperty(PROP_TLS));

        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setUsername(user);
        sender.setPassword(password);
        sender.setHost(host);
        sender.setPort(port);
        sender.setProtocol(protocol);

        Properties sendProperties = new Properties();

        sendProperties.setProperty(PROP_SMTP_AUTH, auth.toString());
        sendProperties.setProperty(PROP_SMTP_STARTTLS, tls.toString());
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, protocol.toString());
        sender.setJavaMailProperties(sendProperties);
        return sender;
    }
}
