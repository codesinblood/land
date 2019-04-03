package com.objectfrontier.training.java.util;
import java.text.MessageFormat;
import java.util.Date;

    public class StringFormat {

        public static void main(String[] args) {

            int primes = 90;
            String message = "On the test run at {1,date} on {1,time}, we found {0} prime numbers";
            MessageFormat mf = new MessageFormat(message);

            System.out.println(mf.format(new Object[] {primes,new Date()}));
        }
    }
