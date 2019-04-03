// Create and print the time stamp for current date.

package com.objectfrontier.training.java.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeStamp {

    public static void main(String[] args) {

        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp timeStamp = Timestamp.valueOf(dateTime);
        System.out.println(timeStamp);
        System.out.println(timeStamp.getTime());
        Timestamp timeStamp1 = new Timestamp(System.currentTimeMillis());
        System.out.println(timeStamp1);
        System.out.println(timeStamp1.getTime());
    }
}
