package com.objectfrontier.training.java.col;

import java.util.List;

public class FindAverageAge {

    private List<Person> getList() {

        List<Person> personsList = Person.createRoster();
        return personsList;
    }

    private void findAvearge(List<Person> personsList) {

        // double average  =  personsList.getAge.getAverage();
        double avg = personsList.stream()
                .map(a -> a.getAge())
                .reduce(0, Integer :: sum) / personsList.size();
        System.out.println(avg);

        double avg1 =personsList.stream()
              .mapToDouble(x -> x.getAge())
              .average().getAsDouble();
        System.out.println(avg1);

        double avg2 =personsList.stream()
              .mapToInt(a -> a.getAge())
              .average().getAsDouble();
        System.out.println(avg2);
    }

    public static void main(String[] args) {

        FindAverageAge age = new FindAverageAge();

        // List personsList = getList();
        List<Person> personsList = age.getList();

        // findAverage(personsList)
        age.findAvearge(personsList);
    }
}
