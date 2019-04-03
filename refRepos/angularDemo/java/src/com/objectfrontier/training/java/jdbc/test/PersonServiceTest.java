package com.objectfrontier.training.java.jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.java.jdbc.connection.ConnectionManager;
import com.objectfrontier.training.java.jdbc.service.Address;
import com.objectfrontier.training.java.jdbc.service.Person;
import com.objectfrontier.training.java.jdbc.service.PersonService;

public class PersonServiceTest {

    ConnectionManager con = new ConnectionManager();
    private PersonService personService;

    @BeforeClass
    private void init() {
     this.con = new ConnectionManager();
     this.personService = new PersonService();
    }

    @Test(priority = 1 , dataProvider = "createPositiveData")
    private void createPositiveTest(Connection connection, Person person, Person expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            Person createdPerson = personService.createPerson(connection, person);
            Assert.assertEquals(createdPerson.toString(), expected.toString());
            connection.commit();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 2, dataProvider = "readPositiveData")
    private void readPositiveTest(Connection connection, long id, boolean flag, Person expected) throws SQLException {

        try {
            Person readedPerson = personService.readPerson(connection, id, flag);
            Assert.assertEquals(readedPerson.toString(), expected.toString());
        } catch(Exception e) {
            Assert.fail("error");
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 3, dataProvider = "readAllPositiveData")
    private void readAllPositiveTest(Connection connection, List<Person> expected) throws SQLException {

        try {
            List<Person> actual = personService.readAllPerson(connection);
            Assert.assertEquals(actual.toString(), expected.toString());
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
        finally {
            con.release(connection);
        }
    }
//
    @Test(priority = 4, dataProvider = "updatePositiveData")
    private void updatePositiveTest(Connection connection, Person person, Person expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            Person updatePerson = personService.update(connection, person);
            Assert.assertEquals(updatePerson.toString(), expected.toString());
            connection.commit();
        } catch(Exception e) {
            Assert.fail("error");
            connection.rollback();
        }
        finally {
            con.release(connection);
        }
    }
//
    @Test(priority = 5, dataProvider = "deletePositiveData")
    private void deletePositiveTest(Connection connection, Person person, int expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            int row = personService.delete(connection, person);
            Assert.assertEquals(row, expected);
            connection.commit();
        } catch(Exception e) {
            Assert.fail("Error");
            connection.rollback();
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 6, dataProvider = "deleteNegativeData")
    private void deleteNegativeTest(Connection connection, Person person, String expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            personService.delete(connection, person);
            connection.rollback();
        } catch(Exception e) {

            Assert.assertEquals(e.getMessage(), expected);
            connection.commit();
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 7, dataProvider = "readAllNegativeData")
    private void readAllNegativeTest(Connection connection, String expected) throws SQLException {

        try {
            personService.readAllPerson(connection);
            Assert.fail("error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }
//
    @Test(priority = 8, dataProvider = "createNegativeData")
    private void createNegativeTest1(Connection connection, Person person, String expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            personService.createPerson(connection, person);
            Assert.fail("error");
            connection.commit();
        } catch (Exception e) {
            Assert.assertEquals(e.getClass().getName(), expected);
            connection.rollback();
        }
        finally {
            con.release(connection);
        }
    }
//
    @Test(priority = 9, dataProvider = "readNegativeData")
    private void readNegativeTest(Connection connection, long id, boolean flag, String expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            personService.readPerson(connection, id, flag);
            Assert.fail("Error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }
//
    @Test(priority = 10, dataProvider = "updateNegativeData")
    private void updateNegativeTest(Connection connection, Person person, String expected) throws SQLException {

        try {
            personService.update(connection, person);
            Assert.fail("error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }
//
    @DataProvider
    private Object[][] readPersonPositiveData() throws SQLException {

        Connection connection = con.getConnection();
        long id = 11;
        boolean flag = false;

        Person expected = new Person();
        expected.setId(11);
        expected.setName("Vinoth");
        expected.setEmail("v@gmail");
        expected.setBirthDate(Date.valueOf("1997-02-03"));
        return new Object[][] {
            {connection, id, flag, expected}
        };
    }
//
    @DataProvider
    private Object[][] createPositiveData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setName("Vinoth");
        person.setEmail("v11@gmail");
        person.setBirthDate(Date.valueOf("1997-02-03"));

        Address address = new Address();
        address.setCity("chennai");
        address.setPostalCode(123456);
        address.setStreet("gandhi st");
        person.setAddress(address);


        Person expected = new Person();
        expected.setId(36);
        expected.setName("Vinoth");
        expected.setEmail("v11@gmail");
        expected.setBirthDate(Date.valueOf("1997-02-03"));
        Address expeAddress = new Address();
        expeAddress.setCity("chennai");
        expeAddress.setPostalCode(123456);
        expeAddress.setStreet("gandhi st");
        expected.setAddress(address);
        return new Object[][] {
            {connection, person, expected}
        };
    }

    @DataProvider
    private Object[][] readPositiveData() throws SQLException {

        Connection connection = con.getConnection();

        long id = 33;
        boolean flag = true;

        Person expected = new Person();
        expected.setId(33);
        expected.setName("Vinoth");
        expected.setEmail("v1@gmail");
        expected.setBirthDate(Date.valueOf("1997-02-03"));
        Address expeAddress = new Address();
        expeAddress.setCity("chennai");
        expeAddress.setPostalCode(123456);
        expeAddress.setStreet("gandhi st");
        expected.setAddress(expeAddress);
        return new Object[][] {
            {connection, id, flag, expected}
        };
    }

    @DataProvider
    private Object[][] readAllPositiveData() throws SQLException {

        Connection connection = con.getConnection();

        List<Person> expectedList = new ArrayList<>();

        Person expected = new Person();
        expected.setId(33);
        expected.setName("Vinoth");
        expected.setEmail("v1@gmail");
        expected.setBirthDate(Date.valueOf("1997-02-03"));
        Address expeAddress = new Address();
        expeAddress.setCity("chennai");
        expeAddress.setPostalCode(123456);
        expeAddress.setStreet("gandhi st");
        expected.setAddress(expeAddress);
        expectedList.add(expected);

        Person expected1 = new Person();
        expected1.setId(36);
        expected1.setName("Vinoth");
        expected1.setEmail("v11@gmail");
        expected1.setBirthDate(Date.valueOf("1997-02-03"));
        Address expeAddress1 = new Address();
        expeAddress1.setCity("chennai");
        expeAddress1.setPostalCode(123456);
        expeAddress1.setStreet("gandhi st");
        expected1.setAddress(expeAddress1);
        expectedList.add(expected1);

        return new Object[][] {
            {connection, expectedList}
        };
    }

    @DataProvider
    private Object[][] updatePositiveData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setId(33);
        person.setName("Vino");
        person.setEmail("v@gmail");
        person.setBirthDate(Date.valueOf("1997-02-03"));
        Address address = new Address();
        address.setId(103);
        address.setCity("bangloree");
        address.setPostalCode(252600);
        address.setStreet("ghggd");
        person.setAddress(address);

        Person expected = new Person();
        expected.setId(33);
        expected.setName("Vino");
        expected.setEmail("v@gmail");
        expected.setBirthDate(Date.valueOf("1997-02-03"));
        Address expeAddress = new Address();
        expeAddress.setCity("bangloree");
        expeAddress.setPostalCode(252600);
        expeAddress.setStreet("ghggd");
        expected.setAddress(address);
        return new Object[][] {
            {connection, person, expected}
        };
    }

    @DataProvider
    private Object[][] deletePositiveData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setId(33);
        Address address = new Address();
        address.setId(103);
        person.setAddress(address);
        int expected = 1;

        return new Object[][] {
            {connection, person, expected}
        };
    }

    @DataProvider
    private Object[][] createNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setName("Vinoth");
        person.setEmail("v11@gmail");
        person.setBirthDate(Date.valueOf("1997-02-03"));

        Address address = new Address();
        address.setCity("banglore");
        address.setPostalCode(25260);
        address.setStreet("ghgg");
        person.setAddress(address);

        String expected = "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";

        return new Object[][] {
            {connection, person, expected}
        };

    }

    @DataProvider
    private Object[][] createNegativeData1() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setName("Vinoth");
//        person.setEmail("v@gmail");
        person.setBirthDate(Date.valueOf("1997-02-03"));

        Address address = new Address();
        address.setCity("banglore");
        address.setPostalCode(25260);
        address.setStreet("ghgg");
        person.setAddress(address);

        String expected = "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";

        return new Object[][] {
            {connection, person, expected}
        };

    }

    @DataProvider
    private Object[][] deleteNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setId(155);
        Address address = new Address();
        address.setId(725);
        person.setAddress(address);
        String expected = "Id not exist";

        return new Object[][] {
            {connection, person, expected}
        };
    }

    @DataProvider
    private Object[][] readNegativeData() throws SQLException {

        Connection connection = con.getConnection();

        long id = 11;
        boolean flag = true;

        String expected = "Id not exist";
        return new Object[][] {
            {connection, id, flag, expected}
        };
    }

    @DataProvider
    private Object[][] updateNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        Person person = new Person();
        person.setId(11);
        person.setName("Vinosss");
        person.setEmail("v1@gmail");
        person.setBirthDate(Date.valueOf("1997-02-03"));
        Address address = new Address();
        address.setId(72);
        address.setCity("bangloree");
        address.setPostalCode(252600);
        address.setStreet("ghggd");
        person.setAddress(address);
        String expected = "Id not exist";

        return new Object[][] {
            {connection, person,  expected}
        };
    }

    @DataProvider
    private Object[][] readAllNegativeData() throws SQLException {

        Connection connection = con.getConnection();

        String expected = "List is not exist";
        return new Object[][] {
            {connection, expected}
        };
    }

}


