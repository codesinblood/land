package com.objectfrontier.training.java.col;

import java.util.List;
import java.util.stream.Collectors;

// Class FindPersons
public class FindPersons {

    private List<Person> getList() {

        List <Person> personList =  Person.createRoster();
        return personList;
    }

    private List<Person> filterList(List<Person> personsList) {

        List<Person> filterList = personsList.stream()
                                             .filter(person -> person.gender == Person.Sex.MALE)
                                             .collect(Collectors.toList());
        return filterList;
    }

    private void findPersons(List <Person> filterList) {

        // Person firstPerson = personsList.firstPerson();
        Person firstPerson = filterList.stream()
                                        .findFirst()
                                        .get();

        // Person randomPerson = personsList.randomPerson();
        Person randomPerson = filterList.stream()
                                         .findAny()
                                         .get();

        // Person lastPerson = personsList.lastPerson();
        Person lastPerson = filterList.get(filterList.size()-1);

        System.out.println(firstPerson.getName());
        System.out.println(randomPerson.getName());
        System.out.println(lastPerson.getName());
    }

    // void execute()
    public static void main(String[] args) {

        FindPersons person = new FindPersons();

        // List personsList = getList();
        List <Person> personsList = person.getList();

     // List filterList = filterList();
        List <Person> filterList = person.filterList(personsList);

        // findPersons(personsList)
        person.findPersons(filterList);
    }
}
