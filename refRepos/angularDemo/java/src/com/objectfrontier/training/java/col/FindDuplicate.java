/*List<Integer> randomNumbers = Array.asList( {1, 6, 10, 1, 25, 78, 10, 25} );

  Get the non-duplicate values from the above list using java.util.Stream API*/

package com.objectfrontier.training.java.col;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicate {


    private List<Integer> createRandomNumberList() {

        List<Integer> randomNumbersList = Arrays.asList( 1, 6, 10, 1, 25, 78, 10, 25 ) ;

        return randomNumbersList;

    }


    private List<Integer> findNonDuplicates(List<Integer> randomNumbersList) {

        List<Integer> nonDuplicateList = randomNumbersList.stream()
        .distinct()
        .collect(Collectors.toList());
        return nonDuplicateList;
    }


    private void printNonDuplicateList(List<Integer> nonDuplicateList) {

        for (Integer number : nonDuplicateList) {
            System.out.println(number);
        }
    }


    public static void main(String[] args) {

        FindDuplicate number = new FindDuplicate();

        // List randomNumbersList = createRandomNumberList();
        List<Integer> randomNumbersList = number.createRandomNumberList();

        // List nonDuplicateList = findNonDuplicates(randomNumbersList);
        List<Integer> nonDuplicateList = number.findNonDuplicates(randomNumbersList);

        //printNonDuplicateList(nonDuplicateList);
        number.printNonDuplicateList(nonDuplicateList);

    }



}
