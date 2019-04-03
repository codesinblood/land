package com.objectfrontier.land;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author hariraj.sampath
 * @since 1.0.0
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@CrossOrigin(value = "*")
public class LandApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandApiApplication.class, args);
    }
}
