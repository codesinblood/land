package com.objectfrontier.training.web.service;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.pojo.Address;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.servlet.RequestHelper;

public class ObjectFactory {

    private static ConnectionManager connectionManager;
    private static Person person;
    private static Address address;
    private static PersonService personService;
    private static AddressService addressService;
    private static RequestHelper requestHelper;

    private ObjectFactory() {
        super();
    }

    static {
        connectionManager = new ConnectionManager();
        person = new Person();
        address = new Address();
        personService = new PersonService();
        addressService = new AddressService();
        requestHelper = new RequestHelper();
    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static Person getPerson() {
        return person;
    }

    public static Address getAddress() {
        return address;
    }

    public static PersonService getPersonService() {
        return personService;
    }

    public static AddressService getAddressService() {
        return addressService;
    }

    public static RequestHelper getRequestHelper() {
        return requestHelper;
    }
}
