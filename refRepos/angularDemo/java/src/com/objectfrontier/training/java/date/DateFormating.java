// Formatting current date to following formats: 2001-07-04T12:08:56.235-0700 and 2001.07.04 at 12:08:56 PDT

package com.objectfrontier.training.java.date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormating {

    public static void main(String[] args) {

        ZonedDateTime date =  ZonedDateTime.now();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy.MM.dd 'at' hh:mm:ss z");

        System.out.println(date);
        System.out.println(date.format(df));
        System.out.println(date.format(df1));
    }
}
