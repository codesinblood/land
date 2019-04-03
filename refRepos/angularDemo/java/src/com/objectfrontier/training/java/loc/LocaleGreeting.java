package com.objectfrontier.training.java.loc;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleGreeting {

    public static void main(String[] args) {

        ResourceBundle englishLocale = ResourceBundle.getBundle("com.objectfrontier.training.java.loc.message", new Locale("en", "us"));
        ResourceBundle tamilLocale = ResourceBundle.getBundle("com.objectfrontier.training.java.loc.message", new Locale("ta", "in"));

        System.out.println(tamilLocale.getString("greeting"));
        System.out.println(tamilLocale.getString("welcome"));
        System.out.println(englishLocale.getString("greeting"));
        System.out.println(englishLocale.getString("welcome"));
    }
}
