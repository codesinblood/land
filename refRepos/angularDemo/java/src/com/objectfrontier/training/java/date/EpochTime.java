// Print the Epoch time

package com.objectfrontier.training.java.date;

import java.time.Instant;
import java.time.LocalDate;

public class EpochTime {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();
        Instant epoch = Instant.EPOCH;
        Instant currentEpoch = Instant.now();
        long currentEpoch1 = currentDate.toEpochDay();

        System.out.println(epoch);
        System.out.println(currentEpoch);
        System.out.println(currentEpoch.getEpochSecond());
        System.out.println(currentEpoch.toEpochMilli());
        System.out.println(currentEpoch1);
    }
}
