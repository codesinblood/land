// 4. For a given year, print the length of each month within that year.

package com.objectfrontier.training.java.date;

import java.time.Month;
import java.time.Year;
import java.util.Scanner;

public class LengthOfMonths {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Year");
        int year = sc.nextInt();
        sc.close();

        Year givenYear = Year.of(year);
//        CharSequence ch = sc.next();


        Month[] months = Month.values();
        boolean leapOrNot = (givenYear).isLeap();


        for (Month month : months) {
            System.out.println(month + "-" + month.length(leapOrNot));
        }
    }
}
