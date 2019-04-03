package com.objectfrontier.training.web.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Address;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.servlet.HttpMethod;
import com.objectfrontier.training.web.servlet.JsonConverter;
import com.objectfrontier.training.web.servlet.RequestHelper;

public class PersonFilterTest extends BaseServletTest {

    private static RequestHelper helper = new RequestHelper();

    @BeforeClass
    private void authenticate() {
        helper = super.login();
    }

    @AfterClass
    private void logout() {

        try {
            HttpResponse response = helper.setSecured(true).setMethod(HttpMethod.POST).setInput(null).requestRaw("/logout");
            Assert.assertEquals(RequestHelper.getStatus(response), 200);
        } catch (Exception e) {
            if (e instanceof AppException) {
                System.out.println(((AppException) e).getErrorCodes());
            }
        }
    }

    @Test(dataProvider = "dpRead")

    public void testRead(Long input, boolean flag, Person expected, List<ErrorCode> errorList) throws Exception {
        System.out.println("I m server");
        try {

            Person created = helper.setSecured(true)
                    .setMethod(HttpMethod.GET)
                    .setInput(null)
                    .requestObject("/do/person?id=5&&flag=true", Person.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(errorList, ((AppException) e).getErrorCodes());
            } else {
                Assert.fail(((AppException) e).getErrorCodes().toString());
            }
        }
    }

    @DataProvider
    public Object[][] dpRead() {

        List<ErrorCode> errorList = new ArrayList<>();
        errorList.add(ErrorCode.ID_NULL);
        long id =5;
        long negativeId =8;
        Person expected = new Person(5, "a",  "b", "c", "2018-11-27", "demo",  true,  new Address(4, "gandhi st",  "chennai", 123456));
        return new Object[][] {
            //            {  id, true, expected, null},
            {  negativeId, true, expected, errorList}
        };
    }

    @Test(dataProvider = "dpCreate")
    public void testCreate(Person input, Person expected, List<ErrorCode> errorList) throws Exception {

        System.out.println("I m server");
        try {

            Person created = helper.setSecured(true)
                    .setMethod(HttpMethod.PUT)
                    .setInput(input)
                    .requestObject("/do/person", Person.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(errorList, ((AppException) e).getErrorCodes());
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    public Object[][] dpCreate() {

        List<ErrorCode> errorList = new ArrayList<>();
        Person negativeInput = new Person(null,  null, "aedal", "08-02-1587", "demo1",  false,  new Address("gandhi st",  "chennai", 123456));
        errorList.add(ErrorCode.F_NAME_NULL);
        errorList.add(ErrorCode.L_NAME_NULL);
        Person input = new Person("j",  "k", "l", "01-02-1587", "demo1",  false,  new Address("gandhi st",  "chennai", 123456));
        Person expected = new Person(6, "j",  "k", "l", "01-02-1587", "demo1",  false,  new Address(7, "gandhi st",  "chennai", 123456));
        return new Object[][] {
            {  input,  expected, null},
            {  negativeInput, null, errorList}
        };
    }

    @Test(dataProvider = "dpUpdate")
    public void testUpdate(Person input, Person expected, List<ErrorCode> errorList) throws Exception {

        System.out.println("I m server");
        try {

            Person created = helper.setSecured(true)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("/do/person?isUpdate=true", Person.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), errorList);
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    public Object[][] dpUpdate() {

        List<ErrorCode> errorList = new ArrayList<>();
        Person negativeInput = new Person(4, "afaaf",  "aaa", "aedalaa", "08-02-1587", "demo1",  true,  new Address(6,"gandhi st",  "chennai", 123456));
        errorList.add(ErrorCode.UN_AUTHORIZED);
        Person input = new Person(4, "ja",  "k", "l", "01-02-1587", "demo1",  new Address(7, "gandhi st",  "chennai", 123456));
        Person expected = new Person(4, "ja",  "k", "l", "01-02-1587", "demo1",  new Address(7, "gandhi st",  "chennai", 123456));
        return new Object[][] {
            {  input,  expected, null},
            {  negativeInput,  null, errorList},
        };
    }

    @Test(dataProvider = "dpDelete")
    public void testDelete(Address input, int expected, List<ErrorCode> errorList) throws Exception {

        System.out.println("Delete Test");

        try {

            int created = helper.setSecured(true)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("/do/person", int.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), errorList);
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    public Object[][] dpDelete() {

        Person person = new Person();
        person.setId(4);
        Address address = new Address();
        address.setId(6);
        person.setAddress(address);

        Person person1 = new Person();
        person1.setId(5);
        Address address1 = new Address();
        address1.setId(4);
        person.setAddress(address);
        List<ErrorCode> expectedList = new ArrayList<>();
        //        expectedList.add(ErrorCode.ID_NULL);
        expectedList.add(ErrorCode.UN_AUTHORIZED);
        return new Object[][] {
            //            {  person,  1, null},
            {  person1,  0, expectedList}
        };
    }

    @Test(dataProvider = "dpReadAll")
    public void testReadAll(List<Person>  expectedAddress, List<ErrorCode> errorList) {

        try {

            RequestHelper.setBaseUrl("http://localhost:8080/ws/do/");
            List<Person> listPerson = helper.setSecured(true)
                    .setMethod(HttpMethod.GET)
                    .setInput(null)
                    .requestList("/do/person", Person.class);

            Assert.assertEquals(JsonConverter.toJson(listPerson)
                    , JsonConverter.toJson(expectedAddress));

        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), errorList);
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    Object[][] dpReadAll() throws SQLException {

        List<ErrorCode> expectedList = new ArrayList<>();
        expectedList.add(ErrorCode.UN_AUTHORIZED);
        List<Person> personList = new ArrayList<>();
        Person expected = new Person(5, "a", "b", "c", "2018-11-27","demo", true, new Address(4, "gandhi st", "chennai", 123456));
        personList.add(expected);

        return new Object[][] {
            //            {personList, null},
            {null, expectedList}
        };
    }
}

