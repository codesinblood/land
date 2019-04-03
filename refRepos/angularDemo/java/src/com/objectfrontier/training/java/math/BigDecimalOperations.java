package com.objectfrontier.training.java.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class BigDecimalOperations {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // getting two BigDecimal numbers from developer
        BigDecimal decimalNumber = input.nextBigDecimal();
        BigDecimal decimalNumber1 = input.nextBigDecimal();

        generalOperationsOnBigDecimal(decimalNumber, decimalNumber1);

        input.close();
    }

    private static void generalOperationsOnBigDecimal(BigDecimal decimalNumber, BigDecimal decimalNumber1) {

        // round a BigDecimal number with specified precision
        BigDecimal roundedDecimalNumber = decimalNumber.round(new MathContext(5));
        System.out.println("The rounded decimal number with precision is " + roundedDecimalNumber);
        System.out.println("The rounded decimal number with set scale is " + decimalNumber.setScale(2, RoundingMode.CEILING));

        // absolute value of given BigDecimal number
        System.out.println("The absolute value is " + decimalNumber.abs());

        // ceiling value of given BigDecimal number
        System.out.println("The ceiling value of " + decimalNumber + " is " + Math.ceil(decimalNumber.doubleValue()));

        // floor value of given BigDecimal number
        System.out.println("The floor value of " + decimalNumber + " is " + Math.floor(decimalNumber.doubleValue()));

        // rint value of given BigDecimal number
        System.out.println("The rint value of " + decimalNumber + " is " + Math.rint(decimalNumber.doubleValue()));

        // maximum value among two BigDecimal numbers
        System.out.println("The Maximum value among " + decimalNumber + " and " + decimalNumber1 + " is " + decimalNumber.max(decimalNumber1));

        // minimum value among two BigDecimal numbers
        System.out.println("The Minimum value among " + decimalNumber + " and " + decimalNumber1 + " is " + decimalNumber.min(decimalNumber1));
    }
}
