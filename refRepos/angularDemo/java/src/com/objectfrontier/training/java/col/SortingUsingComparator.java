package com.objectfrontier.training.java.col;

import java.util.Collections;
import java.util.List;

public class SortingUsingComparator {

    private List<Person> getList() {

        List <Person> personList =  Person.createRoster();
        return personList;
    }

    private void printSortedList(List<Person> sortedList) {

        for (Person person : sortedList) {
            person.printPerson();
        }
    }

    public static void main(String[] args) {

        SortingUsingComparator sort = new SortingUsingComparator();

        // List personsList = getList();
        List <Person> personsList = sort.getList();

        // List sortedList = sortList(personsList);
        Collections.sort(personsList, new MyComparator());

        // printSortedList(sortedList);
        sort.printSortedList(personsList);
    }
}
