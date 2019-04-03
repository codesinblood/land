package com.objectfrontier.training.java.col;

import java.util.List;
import java.util.stream.Collectors;

import com.objectfrontier.training.java.col.Person.Sex;

// Class FilterPerson
public class FilterPerson {

    private List<Person> getPersonList() {

        List <Person> personList =  Person.createRoster();
        return personList;
    }

    private List<Person> filterDetails(List <Person> personList) {

        // List filteredList = filter(age > 21, gender = MALE);
        List <Person> filteredList =  personList.stream()
                                                .filter(person -> person.getGender() == Sex.MALE && person.getAge() > 21)
                                                .collect(Collectors.toList());
        return filteredList;
    }

    private void printFilteredList(List<Person> filteredList) {

        for (Person person : filteredList) {
            person.printPerson();
        }
    }

    // void execute()
    public static void main(String[] args) {

        FilterPerson person = new FilterPerson();

        // List personList = getPersonList();
        List <Person> personList = person.getPersonList();

        // List filteredList = getFilteredDetails(personList);
        List <Person> filteredList = person.filterDetails(personList);

        //console.print(filteredList);
        person.printFilteredList(filteredList);
    }
}
