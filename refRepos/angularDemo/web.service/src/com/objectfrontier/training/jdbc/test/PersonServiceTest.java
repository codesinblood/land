package com.objectfrontier.training.jdbc.test;

import static com.objectfrontier.training.jdbc.exceptions.ErrorCode.DATE_FORMAT_ERROR;
import static com.objectfrontier.training.jdbc.exceptions.ErrorCode.DOB_NULL;
import static com.objectfrontier.training.jdbc.exceptions.ErrorCode.EMAIL_DUP;
import static com.objectfrontier.training.jdbc.exceptions.ErrorCode.F_NAME_NULL;
import static com.objectfrontier.training.jdbc.exceptions.ErrorCode.L_NAME_NULL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;
import com.objectfrontier.training.jdbc.service.PersonService;

public class PersonServiceTest {

    ConnectionManager conManager;
    private PersonService personService;

    @BeforeTest
    private void initClass() throws SQLException, IOException {
        this.conManager = new ConnectionManager();
        this.personService = new PersonService();
    }

    @Test(priority = 1, dataProvider = "createPositiveData")
    private void createPositiveTest(Person person, Person expected)  {

        Connection connection = conManager.get();
        Person createdPerson = personService.createPerson(connection, person);
        Assert.assertEquals(createdPerson.toString(), expected.toString());
        conManager.release(connection, true);
    }

    @DataProvider
    private Object[][] createPositiveData() {

        Address address = new Address("gandhi st", "chennai", 123456);
        Person person = new Person("jagan", "ja", "ja1", "03-02-1585", address);
        Person expected = new Person(1, "jagan", "ja", "ja1", "03-02-1585", address);
        return new Object[][] { { person, expected } };
    }

    @Test(priority = 3, dataProvider = "readPositiveData")
    private void readPositiveTest(long id, boolean flag, Person expected) {

        Connection connection = conManager.get();
        Person readedPerson = personService.readPerson(connection, id, flag);
        Assert.assertEquals(readedPerson.toString(), expected.toString());
        conManager.release(connection, true);
    }

    @DataProvider
    private Object[][] readPositiveData(){

        long id = 1;
        boolean flag = true;

        Address address = new Address("gandhi st", "chennai", 123456);
        Person expected = new Person(1, "jagan", "ja", "ja1", "1585-02-03", address);
        return new Object[][] { { id, flag, expected } };
    }

    @Test(priority = 4, dataProvider = "readAllPositiveData")
    private void readAllPositiveTest(List<Person> expected) {

        Connection connection = conManager.get();
        List<Person> actual = personService.readAllPerson(connection);
        Assert.assertEquals(actual.toString(), expected.toString());
        conManager.release(connection, true);
    }

    @DataProvider
    private Object[][] readAllPositiveData() {

        List<Person> expectedList = new ArrayList<>();

        Address address = new Address("gandhi st", "chennai", 123456);
        Person expected = new Person(1, "jagan", "ja", "ja1", "1585-02-03", address);
        expectedList.add(expected);
        return new Object[][] { { expectedList } };
    }

    @Test(priority = 5, dataProvider = "updatePositiveData")
    private void updatePositiveTest(Person person, Person expected) {

        Connection connection = conManager.get ();
        Person updatePerson = personService.update(connection, person);
        Assert.assertEquals(updatePerson.toString(), expected.toString());
        conManager.release(connection, true);
    }

    @DataProvider
    private Object[][] updatePositiveData() throws SQLException {

        Address address = new Address(1, "gandhi st", "chennai", 123456);
        Person person = new Person(1, "jagan1", "ja1", "ja11", "03-02-1585", address);

        Address address1 = new Address("gandhi st", "chennai", 123456);
        Person expected = new Person(1, "jagan1", "ja1", "ja11", "03-02-1585", address1);
        return new Object[][] { { person, expected } };
    }

    @Test(priority = 7, dataProvider = "deletePositiveData", enabled = true)
    private void deletePositiveTest(Person person, int expected) {

        Connection connection = conManager.get ();
        int row = personService.delete(connection, person);
        Assert.assertEquals(row, expected);
        conManager.release(connection, true);
    }

    @DataProvider
    private Object[][] deletePositiveData()  {

        Person person = new Person();
        person.setId(1);
        Address address = new Address();
        address.setId(1);
        person.setAddress(address);
        int expected = 1;

        return new Object[][] { { person, expected } };
    }

    @Test(priority = 2, dataProvider = "dpCreateFnNull_negative")
    private void testCreate_negative(Person person, ErrorCode expected) {

        Connection connection = conManager.get();
        try {
            personService.createPerson(connection, person);
        } catch (AppException e) {
            Assert.assertEquals(e.getMessage(), expected.getMsg());
            conManager.release(connection, false);
        }
    }

    @DataProvider
    private Object[][] dpCreateFnNull_negative() {

        Address address = new Address("gandhi1 st", "chennai1", 123456);
        Person person = new Person(null, "ja", "ja1", "03-02-1585", address);
        Person person2 = new Person("jagan", null, "ja1", "03-02-1585", address);
        Person person3 = new Person("jagan1", "ja1", "ja1", "03-02-1585", address);
        Person person4 = new Person("jagan1", "jaja1e", "jaj11", "1582-02-03", address);
        Person person5 = new Person("jagan1", "jaja1e", "jaj11", null, address);

        ErrorCode expected  = F_NAME_NULL;
        ErrorCode expected1 = L_NAME_NULL;
        ErrorCode expected2 = EMAIL_DUP;
        ErrorCode expected3 = DATE_FORMAT_ERROR;
        ErrorCode expected4 = DOB_NULL;

        return new Object[][] { { person, expected }, { person2, expected1 }, { person3, expected2 },
                { person4, expected3 }, { person5, expected4 } };
    }

    @Test(priority = 8, dataProvider = "deleteNegativeData")
    private void deleteNegativeTest(Person person, String expected) {

        Connection connection = conManager.get ();
        try {
            personService.delete(connection, person);
        } catch (AppException e) {
            Assert.assertEquals(e.getMessage(), expected);
            conManager.release(connection, false);
        }
    }

    @DataProvider
    private Object[][] deleteNegativeData() {

        Person person = new Person();
        person.setId(155);
        Address address = new Address();
        address.setId(725);
        person.setAddress(address);
        String expected = "Id Not exists";

        return new Object[][] { { person, expected } };
    }

    @Test(priority = 10, dataProvider = "readAllNegativeData")
    private void readAllNegativeTest(String expected)  {

        Connection connection = conManager.get ();
        try {
            personService.readAllPerson(connection);
        } catch (AppException e) {
            Assert.assertEquals(e.getMessage(), expected);
            conManager.release(connection, false);
        }
    }

    @DataProvider
    private Object[][] readAllNegativeData() {

        String expected = "List is empty";
        return new Object[][] { { expected } };
    }

    @Test(dataProvider = "readNegativeData")
    private void readNegativeTest(long id, boolean flag, String expected)  {

        Connection connection = conManager.get();
        try {
            personService.readPerson(connection, id, flag);
        } catch (AppException e) {
            Assert.assertEquals(e.getMessage(), expected);
            conManager.release(connection, false);
        }
    }

    @DataProvider
    private Object[][] readNegativeData() {

        long id = 1111;
        boolean flag = true;

        String expected = "Id Not exists";
        return new Object[][] { { id, flag, expected } };
    }

    @Test(priority = 6, dataProvider = "updateNegativeData")
    private void updateNegativeTest(Person person, String expected)  {

        Connection connection = conManager.get();
        try {
            personService.update(connection, person);
        } catch (AppException e) {
            Assert.assertEquals(e.getMessage(), expected);
            conManager.release(connection, false);
        }
    }

    @DataProvider
    private Object[][] updateNegativeData() {

        Address address = new Address(1, "gandhi st", "chennai", 123456);
        Person person = new Person(1, null, "ja11", "jaj1", "03-02-1585", address);
        Person person2 = new Person(1, "jagan1", null, "jaj1", "03-02-1585", address);
        Person person3 = new Person(1, "jagan1", "jaja", "ja11", "03-02-1585", address);
        Person person4 = new Person(1, "jagan1", "jaja1e", "jaj11", "1582-02-03", address);
        Person person5 = new Person(5, "jagan1", "jaja1e", "jaj11", "21-02-1998", address);

        Address address1 = new Address(1, null, "chenn", 600125);
        Person person6 = new Person(5, "jagan1", "jaja1e", "jaj11", "21-02-1998", address1);
        Address address2 = new Address(1, "veerabhatren", null, 600014);
        Person person7 = new Person(5, "jagan1", "jaja1e", "jaj11", "21-02-1998", address2);
        Address address3 = new Address(1, "veerabhatren", "chennai", 0);
        Person person8 = new Person(5, "jagan1", "jaja1e", "jaj11", "21-02-1998", address3);
        Address address4 = new Address(100, "veerabhatren", "chennai", 60021);
        Person person9 = new Person(5, "jagan1", "jaja1e", "jaj11", "21-02-1998", address4);

        String expected = "First_Name is Null";
        String expected1 = "Last_Name is Null";
        String expected2 = "Email has been duplicated";
        String expected3 = "DateFormat is Mismatched";
        String expected4 = "Id Not exists";

        String expected5 = "Street is null";
        String expected6 = "City is null";
        String expected7 = "Postal Code is null";
        String expected8 = "Id Not exists";

        return new Object[][] { { person, expected }, { person2, expected1 }, { person3, expected2 },
                { person4, expected3 }, { person5, expected4 }, { person6, expected5 }, { person7, expected6 },
                { person8, expected7 }, { person9, expected8 }, };
    }

    @AfterTest
    private void resetId() {

        Connection connection = conManager.get ();
        try {
            connection.createStatement().execute("ALTER TABLE person_service AUTO_INCREMENT = 1  ");
            connection.createStatement().execute("ALTER TABLE address_service AUTO_INCREMENT = 1 ");
            conManager.release(connection, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
