package com.objectfrontier.training.java.col;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable, Comparable<Person> {

    static List<Person> result = Person.createRoster();

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public Person(String nameArg, LocalDate birthdayArg,
        Sex genderArg, String emailArg) {
        name = nameArg;
        birthday = birthdayArg;
        gender = genderArg;
        emailAddress = emailArg;
    }

    public int getAge() {
        return birthday
            .until(IsoChronology.INSTANCE.dateNow())
            .getYears();
    }

    public void printPerson() {
      System.out.println(name + ", " + this.getAge());
    }

    public Sex getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static List<Person> createRoster() {

        List<Person> roster = new ArrayList<>();
        roster.add(
            new Person(
            "Fred",
            IsoChronology.INSTANCE.date(1980, 6, 20),
            Person.Sex.MALE,
            "fred@example.com"));
        roster.add(
            new Person(
            "Jane",
            IsoChronology.INSTANCE.date(1990, 7, 15),
            Person.Sex.FEMALE, "jane@example.com"));
        roster.add(
            new Person(
            "George",
            IsoChronology.INSTANCE.date(1991, 8, 13),
            Person.Sex.MALE, "george@example.com"));
        roster.add(
            new Person(
            "Bob",
            IsoChronology.INSTANCE.date(2000, 9, 12),
            Person.Sex.MALE, "bob@example.com"));

        return roster;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", birthday=" + birthday + ", gender=" + gender + ", emailAddress="
                + emailAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (gender != other.gender)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int compareTo(Person o) {
        return this.getAge() - o.getAge();
    }

//    @Override
//    public boolean equals(Object b) {
//
//        boolean b1 = (name.equals(((Person) b).name) && (gender.equals(((Person) b).gender)) && (birthday.equals(((Person)b).birthday) && (emailAddress.equals(((Person)b).emailAddress))));
//        return b1;
//    }

}
