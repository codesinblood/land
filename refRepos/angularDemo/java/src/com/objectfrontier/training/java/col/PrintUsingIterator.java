package com.objectfrontier.training.java.col;

import java.util.Iterator;
import java.util.List;

public class PrintUsingIterator {

    private List<Person> getPersonsList() {

        List <Person> personList =  Person.createRoster();
        return personList;
    }

    private void printList(List<Person> personsList) {

        Iterator<Person> iterator = personsList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        }


    public static void main(String[] args) {

        PrintUsingIterator list = new PrintUsingIterator();


        // List personsList = getPersonsList();
        List <Person> personsList = list.getPersonsList();

        // printList(personsList);
        list.printList(personsList);
    }

}
