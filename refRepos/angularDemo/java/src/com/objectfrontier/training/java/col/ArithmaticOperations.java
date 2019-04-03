/* Consider a following code snippet:

    List<Integer> randomNumbers = Array.asList( {1, 6, 10, 25, 78} );

Find the sum of all the numbers in the list using java.util.Stream
Find the maximum of all the numbers in the list using java.util.Stream
Find the minimum of all the numbers in the list using java.util.Stream */

package com.objectfrontier.training.java.col;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArithmaticOperations {

    private List<Integer> createRandomNumberList() {

        List<Integer> randomNumbersList = Arrays.asList( 1, 6, 10, 25, 78 ) ;

        return randomNumbersList;
    }

    private int findMax(List<Integer> randomNumbersList) {

        int max = randomNumbersList.stream()
                .max(Comparator.naturalOrder())
                .get();
        return max;
    }

    private int findMin(List<Integer> randomNumbersList) {

        int min = randomNumbersList.stream()
                .min(Comparator.naturalOrder())
                .get();
        return min;
    }

    private int findSum(List<Integer> randomNumbersList) {

        int sum = randomNumbersList.stream()
                .reduce(Integer :: sum)
                .get();
        return sum;
    }

    private void printReults(int max, int min, int sum) {

        System.out.println("MAX = " + max);
        System.out.println("MIN = " + min);
        System.out.println("SUM = " + sum);
    }

    public static void main(String[] args) {

        ArithmaticOperations operation = new ArithmaticOperations();

        // List randomNumbersList = createRandomNumberList();
        List<Integer> randomNumbersList  = operation.createRandomNumberList();

        // int max = findMax(randomNumbersList);
        int max = operation.findMax(randomNumbersList);

        // int min = findMin(randomNumbersList);
        int min = operation.findMin(randomNumbersList);

        // int sum = findSum(randomNumbersList);
        int sum = operation.findSum(randomNumbersList);

        operation.printReults(max,min,sum);

//        Integer sum = randomNumbers.stream()
//                                   .collect(Collectors.summingInt(m -> m.intValue()));
//
//        Integer sum1 = randomNumbers.stream()
//                                    .reduce((a,b) -> a + b)
//                                    .get();

    }
}
