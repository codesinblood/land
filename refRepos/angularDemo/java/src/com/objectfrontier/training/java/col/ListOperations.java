package com.objectfrontier.training.java.col;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class ListOperations {

    private static List<Person> createNewRoster() {

        // ArrayList = newRosterList();
        ArrayList<Person> newRoster = new ArrayList<>(4);
        newRoster.add(
                new Person(
                "John",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "john@example.com"));

        newRoster.add(
                new Person("Jade",
                IsoChronology.INSTANCE.date(1990, 7, 15),
                Person.Sex.FEMALE,
                "jade@example.com"));

        newRoster.add(
                new Person("Donald",
                IsoChronology.INSTANCE.date(1991, 8, 13),
                Person.Sex.MALE,
                "donald@example.com"));

        newRoster.add(
                new Person("Bob",
                IsoChronology.INSTANCE.date(2000, 9, 12),
                Person.Sex.MALE,
                "bob@example.com"));
        return newRoster;
    }

    // execute
    public static void main(String[] args) {

        // List newList= createNewList();
        List<Person> newList = ListOperations.createNewRoster();

        //List oldList = getPersonsList();
        List<Person> oldList = Person.createRoster();

        // printNewList();
        newList.stream()
                       .forEach(newRosterList -> System.out.println("New List : " + newRosterList.getName()));

        // oldList = add(oldList);
        oldList.addAll(newList);

        // display the oldList after addition
        oldList.stream()
                    .forEach(rosterList -> System.out.println("Updated old List : " + rosterList.getName()));

        //  oldList = remove(oldList);
        oldList.removeAll(newList);

        // display the oldList after removal
        oldList.stream()
                    .forEach(removeRoasteResult -> System.out.println("Removal old List : " + removeRoasteResult.getName()));
    }
}
