package com.objectfrontier.training.java.col;

import java.util.List;

public class PrintUsingForEach {

    private List<Person> getPersonsList() {

        List <Person> personList =  Person.createRoster();
        return personList;
    }

    private void printList(List<Person> personsList) {

       personsList.stream()
                  .forEach(person -> System.out.println(person.getName()));
        }

    public static void main(String[] args) {

        PrintUsingForEach list = new PrintUsingForEach();

        // List personsList = getPersonsList();
        List <Person> personsList = list.getPersonsList();

        // printList(personsList);
        list.printList(personsList);
    }
}
