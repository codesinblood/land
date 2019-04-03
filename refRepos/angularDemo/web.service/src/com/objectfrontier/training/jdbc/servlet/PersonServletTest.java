package com.objectfrontier.training.jdbc.servlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class PersonServletTest {

    @BeforeTest
    private void init() throws SQLException {
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        ConnectionManager con = new ConnectionManager();
        Connection connection = con.get();
        connection.createStatement().execute("DELETE FROM person_service ");
        connection.createStatement().execute("ALTER TABLE person_service AUTO_INCREMENT = 1");
        connection.createStatement().execute("DELETE FROM address_service ");
        connection.createStatement().execute("ALTER TABLE address_service AUTO_INCREMENT = 1");
        connection.close();
    }

    @Test(priority = 1, dataProvider = "dpCreate")
    public void testCreate(Person input, Person expected, List<ErrorCode> expectedList) {

        RequestHelper helper = new RequestHelper();
        try {

            Person created = helper.setSecured(false)
                    .setMethod(HttpMethod.PUT)
                    .setInput(input)
                    .requestObject("person", Person.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if (e instanceof AppException) {
                System.out.println(((AppException) e).getErrorCodes());
                Assert.assertEquals(((AppException) e).getErrorCodes() , expectedList);
            }else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    private Object[][] dpCreate() throws SQLException {

        Person input = new Person("jagan", "ja", "ja1", "03-02-1585",  new Address("gandhi st", "chennai", 123456));
        Person expected = new Person(1, "jagan", "ja", "ja1", "03-02-1585",  new Address(1, "gandhi st", "chennai", 123456));
        Person input1 = new Person(null, null, "ja",null,  new Address("gandhi st", "chennai", 123456));

        List<ErrorCode> expectedList = new ArrayList<>();
        expectedList.add(ErrorCode.F_NAME_NULL);
        expectedList.add(ErrorCode.L_NAME_NULL);
        expectedList.add(ErrorCode.DOB_NULL);


        return new Object[][] {
            {input, expected, null },
            {input1, null, expectedList }
        };
    }

    @Test(dataProvider = "dpRead", priority = 2)
    public void testRead(int input, boolean readedFlag, Person expectedPerson, List<ErrorCode> expectedList) {

        RequestHelper helper =  new RequestHelper();
        try {

            Person readedvalue = helper.setSecured(false)
                    .setMethod(HttpMethod.GET)
                    .requestObject("person?id=" + input + "&&flag=" + readedFlag , Person.class);

            Assert.assertEquals(JsonConverter.toJson(readedvalue)
                    , JsonConverter.toJson(expectedPerson));

        } catch (Exception e) {
            if (e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes() , expectedList);
            }else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    Object[][] dpRead() throws SQLException {

        int personId= 1;
        Person expectedPerson = new Person(1, "jagan", "ja", "ja1", "1585-02-03",  new Address(1, "gandhi st", "chennai", 123456));
        List<ErrorCode> expectedList  = new ArrayList<>();
        expectedList.add(ErrorCode.ID_NULL);

        return new Object[][] { {personId, true , expectedPerson, null},
            {100, true,  null,  expectedList}
        };
    }

    @Test(priority = 3, dataProvider = "dpUpdate")
    public void testUpdate(Person input, Person expected, List<ErrorCode> expectedList) {

        RequestHelper helper = new RequestHelper();
        try {

            Person created = helper.setSecured(false)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("person?isUpdate=true", Person.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if (e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes() , expectedList);
            }else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    private Object[][] dpUpdate() throws SQLException {

        Person input = new Person(1, "jagan11", "ja1", "ja1a1", "03-02-1585", new Address(1, "gaandhi st", "cheennai", 1234567));
        Person expected = new Person(1, "jagan11", "ja1", "ja1a1", "03-02-1585", new Address(1, "gaandhi st", "cheennai", 1234567));
        Person input1 = new Person(1, "jagan11", "ja1", "ja1", "1585-02-01", new Address(1, "gaandhi st", "cheennai", 1234567));
        List<ErrorCode> expectedList = new ArrayList<>();
        expectedList.add(ErrorCode.DATE_FORMAT_ERROR);
        return new Object[][] {
            {input, expected, null },
            {input1, null,  expectedList }
        };
    }

    @Test(dataProvider = "dpReadAll_Positive", priority = 4)
    public void testReadAll_Positive(List<Person>  expectedAddress) {

        RequestHelper helper =  new RequestHelper();
        try {

            List<Person> listPerson = helper.setSecured(false)
                    .setMethod(HttpMethod.GET)
                    .requestObject("person", Person.class, true);

            Assert.assertEquals(JsonConverter.toJson(listPerson)
                    , JsonConverter.toJson(expectedAddress));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @DataProvider
    Object[][] dpReadAll_Positive() throws SQLException {

        List<Person> personList = new ArrayList<>();
        Person expected = new Person(1, "jagan11", "ja1", "ja1a1", "1585-02-03", new Address(1, "gaandhi st", "cheennai", 1234567));
        personList.add(expected);

        return new Object[][] {
            {personList},
        };
    }

    @Test(priority = 5 , dataProvider = "dpDelete")
    private void testDelete(Person input, int expected, List<ErrorCode> expectedList) {

        RequestHelper helper = new RequestHelper();
        try {
            int actual = helper.setSecured(false)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("person", int.class);
            Assert.assertEquals(JsonConverter.toJson(actual), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if (e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes() , expectedList);
            }else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    private Object[][] dpDelete() throws SQLException {

        Person person = new Person();
        person.setId(1);
        Address address = new Address();
        address.setId(1);
        person.setAddress(address);
        List<ErrorCode> expectedList = new ArrayList<>();
        expectedList.add(ErrorCode.ID_NULL);

        return new Object[][] {
            {person, 1, null },
            {person, 0, expectedList }
        };
    }


    @Test(dataProvider = "dpReadAll_Negative", priority = 6)
    public void testReadAll_Negative(List<ErrorCode>  expectedError) {

        RequestHelper helper =  new RequestHelper();
        try {

            helper.setSecured(false)
            .setMethod(HttpMethod.GET)
            .requestObject("person", Person.class, true);

        } catch (Exception e) {
            Assert.assertEquals(JsonConverter.toJson(((AppException)e).getErrorCodes())
                    , JsonConverter.toJson(expectedError));
        }
    }

    @DataProvider
    Object[][] dpReadAll_Negative() throws SQLException {

        List<ErrorCode> addressList = new ArrayList<>();
        addressList.add(ErrorCode.LIST_EMPTY);

        return new Object[][] {
            {addressList},
        };
    }
}
