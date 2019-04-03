package com.objectfrontier.training.java.io;

import java.util.Collections;
import java.util.List;

import com.objectfrontier.training.java.col.MyComparator;
import com.objectfrontier.training.java.col.Person;

public class ObjectComparasion {

    public static void main(String[] args) {

        // getting a list of persons from Person class
        List<Person> personList = Person.createRoster();

        // sorting and printing the person list using comparator
        sortByAgeUsingComparator(personList);

        // sorting and printing the person list using comparable
        sortByAgeUsingComparable(personList);
    }

    private static void sortByAgeUsingComparable(List<Person> personList) {

        Collections.sort(personList);

        personList.stream()
                  .forEach(person -> person.printPerson());
    }

    private static void sortByAgeUsingComparator(List<Person> personList) {

        personList.sort(new MyComparator());

        personList.stream()
                  .forEach(person -> person.printPerson());
    }
}
