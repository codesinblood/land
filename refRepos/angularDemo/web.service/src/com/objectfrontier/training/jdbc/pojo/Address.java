package com.objectfrontier.training.jdbc.pojo;

public class Address {

    public long id;
    private String street;
    private String city;
    private int postalCode;

    public Address(long id, String street, String city, int postalCode) {
        super();
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }
    public long getId() {
        return id;
    }
    public void setId(long l) {
        this.id = l;
    }
    public String getStreet() {
        return street;
    }
    public Address() {
        super();
    }
    public Address(String street, String city, int postalCode) {
        super();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
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
