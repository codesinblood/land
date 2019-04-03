package com.objectfrontier.training.java.date;

import java.time.Clock;
import java.time.LocalDateTime;

public class MilliNano {

    public static void main(String[] args) {

        System.out.println("Nano Second - " + LocalDateTime.now().getNano());
        System.out.println("Milli Second - " + System.currentTimeMillis());
        System.out.println(Clock.systemUTC().millis());
    }

}
