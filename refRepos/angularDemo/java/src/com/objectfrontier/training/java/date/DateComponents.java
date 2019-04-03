// 3. Print the components of the current date

package com.objectfrontier.training.java.date;

import java.time.LocalDate;

public class DateComponents {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();

        System.out.println(currentDate.getDayOfWeek());
        System.out.println(currentDate.getDayOfMonth());
        System.out.println(currentDate.getMonth());
        System.out.println(currentDate.getYear());
    }
}
