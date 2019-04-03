package com.objectfrontier.training.java.jdbc.service;

import java.sql.Date;
import java.sql.Timestamp;

public class Person {

    private long id;
    private String name;
    private String email;
    private Date birthDate;
    private Timestamp createdDate;
    private Address address;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp date) {
        this.createdDate = date;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", address="
                + address + "]";
    }
}
