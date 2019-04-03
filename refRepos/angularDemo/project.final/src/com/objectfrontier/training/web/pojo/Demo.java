package com.objectfrontier.training.web.pojo;

import java.sql.Connection;
import java.sql.SQLException;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.service.PersonService;

public class Demo {

    public static void main(String[] args) throws SQLException {

        ConnectionManager connect = new ConnectionManager();
        Connection connection = connect.get();
        PersonService p = new PersonService();
       Person input = new Person();
       input.setId(1);
       input.setFirstName("vinothxc");
       input.setLastName("ari");
       input.setEmail("winowino");
       input.setBirthDate("03-08-1996");
       input.setAdmin(true);
//
//    Person person =  p.createPerson(connection, input);
//        System.out.println(person);
     Person person = p.adminUpdate(connection, input);
    System.out.println(person);
        connection.commit();
        connection.close();
    }
}
