package com.objectfrontier.training.java.col;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class RetainAll {

    private List<Person> getOldList() {

        List<Person> personList = Person.createRoster();
        return personList;
    }

    private List<Person> getNewList() {


        ArrayList<Person> newRoster = new ArrayList<>(4);
        newRoster.add(new Person("John",
                                 IsoChronology.INSTANCE.date(1980, 6, 20),
                                 Person.Sex.MALE,
                                 "john@example.com"));
        newRoster.add(new Person("Jade",
                                 IsoChronology.INSTANCE.date(1990, 7, 15),
                                 Person.Sex.FEMALE,
                                 "jade@example.com"));
        newRoster.add(new Person("Donald",
                                 IsoChronology.INSTANCE.date(1991, 8, 13),
                                 Person.Sex.MALE,
                                 "donald@example.com"));
        newRoster.add(new Person("Bob",
                                 IsoChronology.INSTANCE.date(2000, 9, 12),
                                 Person.Sex.MALE,
                                 "bob@example.com"));

        return newRoster;
    }

    private void printList(List<Person> oldList) {

            for (Person person : oldList) {
                person.printPerson();
        }
    }

    public static void main(String[] args) {

        RetainAll obj = new RetainAll();

        List<Person> oldList = obj.getOldList();

        List<Person> newList = obj.getNewList();

        oldList.removeAll(newList);

        obj.printList(oldList);
    }
}
