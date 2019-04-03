// ISO standard - Format and print current date into ISO date format

package com.objectfrontier.training.java.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ISOFormat {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();
        System.out.println("ISO_DATE         - " + currentDate.format(DateTimeFormatter.ISO_DATE));
        System.out.println("ISO_LOCAL_DATE   - " + currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_ORDINAL_DATE - " + currentDate.format(DateTimeFormatter.ISO_ORDINAL_DATE));
        System.out.println("ISO_WEEK_DATE    - " + currentDate.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println("BASIC_ISO_DATE   - " + currentDate.format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
