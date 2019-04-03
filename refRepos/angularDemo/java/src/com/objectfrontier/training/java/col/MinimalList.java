package com.objectfrontier.training.java.col;

import java.util.List;
import java.util.stream.Collectors;

// Class PrintMinimalList
public class MinimalList {

    String name;
    String emailid;

    private List<Person> getList() {

        List<Person> personsList = Person.createRoster();
        return personsList;
    }

    private List<String> getMimimalList(List<Person> personsList) {

        // List minimalList = toMimimal(personsList.getName(), personList.getEmail());
        List<String> minimalList = personsList.stream()
                                              .map(a -> (toMinimal(a.getName(),a.getEmailAddress())))
                                              .collect(Collectors.toList());
        return minimalList;

    }

    private String toMinimal(String name, String emailid) {

        this.emailid = emailid;
        this.name = name;
        return name + " " + emailid;
    }

  private void printMinimalList(List<String> minimalList) {

      for (String person : minimalList) {
          System.out.println(person);
      }
  }


  // void execute() {
    public static void main(String[] args) {

        MinimalList minimalPerson = new MinimalList();

        // List personsList = getList();
        List<Person> personsList = minimalPerson.getList();

        // List minimalList = getMimimalList(personsList);
        List<String> minimalList=  minimalPerson.getMimimalList(personsList);

        // printMimimalList(personsList);
        minimalPerson.printMinimalList(minimalList);

    }
}
