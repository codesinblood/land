package com.objectfrontier.training.jdbc.pojo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    @JsonIgnore
    private Timestamp createdDate;

    public Person() {
        super();
    }

    public Person(String firstName, String lastName, String email, String birthDate, Address address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }
    public Person(long id, String firstName, String lastName, String email, String birthDate, Address address) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    private Address address;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", birthDate=" + birthDate + ", address=" + address + "]";
    }
}
