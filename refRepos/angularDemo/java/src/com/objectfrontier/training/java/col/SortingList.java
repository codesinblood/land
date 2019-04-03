package com.objectfrontier.training.java.col;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

    public class SortingList {

        private List<Person> getList() {

            List <Person> personList =  Person.createRoster();
            return personList;
        }

        private List<Person> sortList(List<Person> personsList) {

            // List sortedList = sortList(personsList.getAge().reversed());
            List <Person> sortedList = personsList.stream()
                                                  .sorted(Comparator.comparing(Person :: getAge).reversed())
                                                  .collect(Collectors.toList());
            return sortedList;

        }

        private void printSortedList(List<Person> sortedList) {

            for (Person person : sortedList) {
                person.printPerson();
            }
        }

        public static void main(String[] args) {

            SortingList sort = new SortingList();

            // List personsList = getList();
            List <Person> personsList = sort.getList();

            // List sortedList = sortList(personsList);
            List <Person> sortedList = sort.sortList(personsList);

            // printSortedList(sortedList);
            sort.printSortedList(sortedList);
        }
    }
