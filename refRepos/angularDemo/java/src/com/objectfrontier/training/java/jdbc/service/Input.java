package com.objectfrontier.training.java.jdbc.service;

import java.sql.Date;
import java.util.Scanner;


public class Input {

    static Scanner input = new Scanner(System.in);

    public static Person getPersonInput() {

        Person person = new Person();

        log("%s", "Enter Name");
        person.setName(input.next());
        log("%s", "Enter email");
        person.setEmail(input.next());
        log("%s", "Enter dob ");
        Date birthDate = Date.valueOf(input.next());
        person.setBirthDate(birthDate);

        Address address = new Address();
        log("%s", "Enter street for Address");
        input.nextLine();
        address.setStreet(input.nextLine());
        log("%s", "Enter city for Address");
        address.setCity(input.next());
        log("%s", "Enter code for Address");
        address.setPostalCode(input.nextInt());
        person.setAddress(address);
        return person;
    }

    public static long getId() {

        log("%s", "Enter the id");
        long id = input.nextLong();
        return id;

    }

    public static Person deleteId() {

        Person person = new Person();
        log("%s", "Enter the id");
        person.setId(input.nextLong());
        Address address = new Address();
        log("%s", "Enter the Addr_id to want to update");
        address.setId(input.nextLong());
        person.setAddress(address);
        return person;


    }

    public static Person updateInput() {

        Person person = new Person();

        log("%s", "Enter Name");
        person.setName(input.next());
        log("%s", "Enter email");
        person.setEmail(input.next());
        log("%s", "Enter dob ");
        Date birthDate = Date.valueOf(input.next());
        person.setBirthDate(birthDate);

        Address address = new Address();

        log("%s", "Enter street for Address");
        input.nextLine();
        address.setStreet(input.nextLine());
        log("%s", "Enter city for Address");
        address.setCity(input.next());
        log("%s", "Enter code for Address");
        address.setPostalCode(input.nextInt());
        log("%s", "Enter the id to want to update");
        person.setId(input.nextLong());
        log("%s", "Enter the Addr_id to want to update");
        address.setId(input.nextLong());
        person.setAddress(address);
        return person;
    }
    private static void log(String format, Object args) {

        System.out.format(format + "\n", args);
    }

    public static Address getCreateAddressInput() {

        Address address = new Address();

        log("%s", "Enter street for Address");
        address.setStreet(input.next());
        log("%s", "Enter city for Address");
        address.setCity(input.next());
        log("%s", "Enter code for Address");
        address.setPostalCode(input.nextInt());
        return address;
    }

    public static long getReadAddressInput() {

        log("%s", "Enter the id");
        return input.nextLong();
    }

    public static Address getUpdateAddressInput() {

        Address address = new Address();

        log("%s", "Enter street for Address");
        address.setStreet(input.next());
        log("%s", "Enter city for Address");
        address.setCity(input.next());
        log("%s", "Enter code for Address");
        address.setPostalCode(input.nextInt());
        log("%s", "Enter the id");
        address.setId(input.nextLong());
        return address;
    }

}
