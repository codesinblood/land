package com.objectfrontier.training.java.jdbc.service;

public class Address {

    private long id;
    private String street;
    private String city;
    private int postalCode;





    public long getId() {
        return id;
    }
    public void setId(long l) {
        this.id = l;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postal_code) {
        this.postalCode = postal_code;
    }
    @Override
    public String toString() {
        return "Address [street=" + street + ", city=" + city + ", postalCode=" + postalCode + "]";
    }
}
