package com.objectfrontier.training.java.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class NumberFormatting {

    public static void main(String[] args) {


        Locale[] locales = { new Locale("en", "US"),
                             new Locale("en", "CA"),
                             new Locale("zh", "CN"),
                             new Locale("ru", "RU"),
                             new Locale("es", "ES"),
                             new Locale("fr", "FR"),
                             new Locale("en", "IN")};
        printNumberFormat(locales);
        printDateFormat(locales);
    }

    private static void printDateFormat(Locale[] locales) {

        for (Locale locale : locales) {

            DateFormat date = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
            System.out.println("The Date Format in  " + locale.getCountry() + " is " + date.format(Date.from(Instant.now())));
        }
    }

    private static void printNumberFormat(Locale[] locales) {

      for (Locale locale : locales) {

            NumberFormat nformat = NumberFormat.getInstance(locale);
            System.out.println("The Number Format in  " + locale.getCountry() + " is " + nformat.format(165456.4545));
        }
    }
}
