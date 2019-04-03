package com.objectfrontier.training.java.col;

import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.stream.Collectors;

public class DuplicateRemoval {

    private List<Person> getPersonsList() {

        List<Person> personsList = Person.createRoster();

        personsList.add(new Person("Bob",
                                   IsoChronology.INSTANCE.date(2000, 9, 12),
                                   Person.Sex.MALE,
                                   "bob@example.com"));

        return personsList;
    }

    private List<Person> getDistinctList(List<Person> personsList) {

        List<Person> distinctList = personsList.stream().distinct().collect(Collectors.toList());

        return distinctList;
    }

    private void printDistinctList(List<Person> distinctList) {

        for (Person person : distinctList) {
            person.printPerson();
        }
    }

    public static void main(String[] args) {

        DuplicateRemoval obj = new DuplicateRemoval();

        List<Person> personsList = obj.getPersonsList();

        List<Person> distinctList = obj.getDistinctList(personsList);

        obj.printDistinctList(distinctList);
    }
}
