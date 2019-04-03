/*Consider the following Person:
    new Person("Bob", IsoChronology.INSTANCE.date(2000, 9, 12), Person.Sex.MALE, "bob@example.com")
 Check if the above Person is in the roster list obtained from Person class*/

package com.objectfrontier.training.java.col;

import java.time.chrono.IsoChronology;
import java.util.List;

public class ContainsDemo {

    private List<Person> getPersonsList() {

        List<Person> personsList = Person.createRoster();
        return personsList;
    }

    public static void main(String[] args) {

        ContainsDemo cd = new ContainsDemo();

        List<Person> personsList = cd.getPersonsList();

        Person newPerson = new Person("Bob", IsoChronology.INSTANCE.date(2000, 9, 12), Person.Sex.MALE, "bob@example.com");


        boolean result = personsList.contains(newPerson);

        System.out.println(result);
    }


}
