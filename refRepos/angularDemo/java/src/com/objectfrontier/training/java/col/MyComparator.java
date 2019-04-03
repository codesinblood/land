package com.objectfrontier.training.java.col;

import java.util.Comparator;

public class MyComparator implements Comparator<Person>{

    @Override
    public int compare(Person person1, Person person2) {

        return person2.getAge() - person1.getAge();
    }
}
