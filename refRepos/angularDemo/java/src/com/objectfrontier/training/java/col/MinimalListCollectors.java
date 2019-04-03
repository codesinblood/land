package com.objectfrontier.training.java.col;

import java.util.List;
import java.util.stream.Collectors;

//Class MinimalPerson
public class MinimalListCollectors {

    public String name;
    public String emailid;

    private List<Person> getList() {

        List<Person> personsList = Person.createRoster();
        return personsList;
    }

    private String getMinimal(String name, String emailid) {

        this.emailid = emailid;
        this.name = name;
        return name + " " + emailid;
    }

    private List<String> getMinimalList(List<Person> personsList) {

        // List minimalList = getMinimal(name, emailAddress);
        List<String> minimalList =personsList.stream()
                            .map(a -> (getMinimal(a.name, a.emailAddress)))
                            .collect(Collectors.toList());
            return minimalList;
        }

//    private void printMinimal(List<String> minimalList) {
//
//        for (String person : minimalList) {
//            System.out.println(person);
//        }
//    }

    //void execute()
    public static void main(String[] args) {

        MinimalListCollectors person = new MinimalListCollectors();

        //List personsList = getList();
        List<Person> personsList = person.getList();

        // List minimalList = getMinimalList(personsList)
        List<String> minimalList = person.getMinimalList(personsList);

//        //printMinimal(minimalList)
//        obj.printMinimal(minimalList);
    }
}
