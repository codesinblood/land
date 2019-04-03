package com.objectfrontier.training.jdbc.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.pojo.CsvParser;
import com.objectfrontier.training.jdbc.pojo.Person;
import com.objectfrontier.training.jdbc.service.PersonService;

public class CreateBulkTest {

    ConnectionManager conManager;
    private PersonService personService;

    @BeforeClass
    private void init() {

        this.conManager = new ConnectionManager();
        this.personService = new PersonService();
    }

    @Test(dataProvider = "createBulkData")
    private void createPositiveTestBulk(Person person, Person expectedPerson) throws SQLException, ParseException, IOException {

        System.out.println(System.currentTimeMillis());
       Connection conn = conManager.getConnection ();
        System.out.println(conn.toString());
        Person createdPerson = personService.createPerson(conn, person);
        Assert.assertEquals(createdPerson.toString(), expectedPerson.toString());
        conManager.releaseConnection(conn, true);
    }

    @DataProvider(parallel = true)
    private Object[][] createBulkData() throws SQLException, IOException, URISyntaxException {

        Object[][] personList = CsvParser.createParser();
        return personList;
    }

    @AfterClass
    private void resetId() throws SQLException, IOException {

        Connection conn = conManager.getConnection();
        conn.createStatement().execute("DELETE from person_service");
        conn.createStatement().execute("ALTER TABLE person_service AUTO_INCREMENT = 1");
        conn.createStatement().execute("DELETE from address_service");
        conn.createStatement().execute("ALTER TABLE address_service AUTO_INCREMENT = 1");
        conn.close();
    }
}
