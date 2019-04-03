package com.objectfrontier.training.java.util;

import java.util.Arrays;
import java.util.List;

import com.objectfrontier.training.java.col.Person;

public class CollectionToArrayConversion {

    public static void main(String[] args) {

        // getting a list of persons from Person class
        List<Person> personList = Person.createRoster();

        // converting the person list into array
        Person[] personArray = listToArrayConversion(personList);

        // printing the array of persons
        printingPersonArray(personArray);

        // converting person array into list and printing the list of persons
        arrayToListConversion(personArray);
    }

    private static void printingPersonArray(Person[] personArray) {

        for (Person person : personArray) {

            person.printPerson();
        }
    }

    private static void arrayToListConversion(Person[] personArray) {

        List<Person> personList1 = Arrays.asList(personArray);

        personList1.stream()
                   .forEach(person -> person.printPerson());
    }

    private static Person[] listToArrayConversion(List<Person> personList) {

        return personList.toArray(new Person[personList.size()]);
    }
}
