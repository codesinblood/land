// 7. Date difference - Find the difference between two dates.

package com.objectfrontier.training.java.date;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;


public class DiffBetweenDates {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Year");
        int year = sc.nextInt();
        System.out.println("Enter the Month");
        int month = sc.nextInt();
        System.out.println("Enter the date");
        int date = sc.nextInt();
        sc.close();
        LocalDate givenDate = LocalDate.of(year, month, date);
        LocalDate currentDate = LocalDate.now();

        findDiff(givenDate, currentDate);

    }

    private static void findDiff(LocalDate givenDate, LocalDate currentDate) {

        Period period = Period.between(givenDate, currentDate);
        System.out.println("Given Date is "   + givenDate);
        System.out.println("Current Date is " + currentDate);
        System.out.println("Difference between the two date is");
        System.out.print(period.getYears()  + "Years ");
        System.out.print(period.getMonths() + "Months ");
        System.out.print(period.getDays()   + "Days");
    }
}
