// 5. For a given month of the current year, lists all of the Mondays in that month with date in mm-dd-yyyy format.

package com.objectfrontier.training.java.date;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FindMonday {

    public static void main(String[] args) {

        FindMonday find = new FindMonday();

        Year year = Year.now();
        int currentYear = year.getValue();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the month in numerical");
        int month = sc.nextInt();
        sc.close();
        int length = YearMonth.of(currentYear, month).lengthOfMonth();

        find.findUsingCalender(currentYear, month - 1, length);
        find.findUsingLocalDate(currentYear, month, length);
    }

    private void findUsingLocalDate(int currentYear, int month, int length) {

        System.out.println("Result Using LocalDate");
        System.out.println("MM-DD-YYYY");
        for (int i = 1; i < length; i++) {
            LocalDate date = YearMonth.of(currentYear, month).atDay(i);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
            System.out.println(date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        }
        }
    }

    private void findUsingCalender(int currentYear, int month, int length) {

        Calendar c = Calendar.getInstance();
        c.set(currentYear, month, 0);

        SimpleDateFormat df1 = new SimpleDateFormat("MM-dd-yyyy");

        System.out.println("Result Using Calender");
        System.out.println("MM-DD-YYYY");
        for (int i = 1; i <= length; i++) {
            c.set(Calendar.DAY_OF_MONTH, i);
            if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                Date d = c.getTime();
                System.out.println(df1.format(d));
            }
        }

    }
}
