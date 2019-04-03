package com.objectfrontier.training.java.col;

import java.time.chrono.IsoChronology;
import java.util.List;

public class RemoveObject {

    private List<Person> getOldList() {

        List<Person> personList = Person.createRoster();
        return personList;
    }

    private void printList(List<Person> personList) {

        for (Person person : personList) {
            person.printPerson();
        }
    }
    public static void main(String[] args) {

        RemoveObject obj = new RemoveObject();

         List<Person> personList = obj.getOldList();


         Person newPerson = new Person("Bob",
                                       IsoChronology.INSTANCE.date(2000, 9, 12),
                                       Person.Sex.MALE,
                                       "bob@example.com");
         personList.remove(newPerson);

         obj.printList(personList);

    }
}
