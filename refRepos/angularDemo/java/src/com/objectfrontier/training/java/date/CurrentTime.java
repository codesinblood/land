package com.objectfrontier.training.java.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
public class CurrentTime {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(OffsetDateTime.now());
        System.out.println(OffsetTime.now());
        System.out.println(ZonedDateTime.now());
        System.out.println(Calendar.getInstance().getTime());
    }
}
