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
import com.objectfrontier.training.web.servlet.HttpMethod;
import com.objectfrontier.training.web.servlet.JsonConverter;
import com.objectfrontier.training.web.servlet.RequestHelper;

public class AddressFilterTest extends BaseServletTest {

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
    public void testRead(Long input, Address expected, List<ErrorCode> errorList) throws Exception {

        try {

            Address actual = helper.setSecured(true)
                    .setMethod(HttpMethod.GET)
                    .setInput(null)
                    .requestObject("/do/address?id=" + input, Address.class);

            Assert.assertEquals(JsonConverter.toJson(actual), JsonConverter.toJson(expected));
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

        long id = 4;
        long id1 = 40;
        Address input = new Address(4, "gandhi st",  "chennai", 123456);
        List<ErrorCode> errorList = new ArrayList<>();
        errorList.add(ErrorCode.ID_NULL);
        return new Object[][] {
            {  id, input, null},
            {  id1, null, errorList}
        };
    }

    @Test(dataProvider = "dpCreate")
    public void testCreate(Address input, Address expected, List<ErrorCode> errorList) throws Exception {

        try {

            Address actual = helper.setSecured(true)
                    .setMethod(HttpMethod.PUT)
                    .setInput(input)
                    .requestObject("/do/address", Address.class);

            Assert.assertEquals(JsonConverter.toJson(actual), JsonConverter.toJson(expected));
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

        Address input = new Address("gandhi st",  "chennai", 123456);
        Address expected = new Address(8, "gandhi st",  "chennai", 123456);
        Address input1 = new Address(null,  "chennai", 123456);
        List<ErrorCode> errorList = new ArrayList<>();
        errorList.add(ErrorCode.STREET_NULL);
        return new Object[][] {
            {  input,  expected, null},
            {  input1, null, errorList}
        };
    }

    @Test(dataProvider = "dpUpdate")
    public void testUpdate(Address input, Address expected, List<ErrorCode> errorList) throws Exception {

        try {

            Address actual = helper.setSecured(true)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("/do/address?isUpdate=true", Address.class);

            Assert.assertEquals(JsonConverter.toJson(actual), JsonConverter.toJson(expected));
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

        Address input = new Address(7, "gandhi",  "chenna", 123456);
        Address expected = new Address(7, "gandhi",  "chenna", 123456);
        Address negativeInput = new Address(10,"gandhi st",  "chennai", 123456);
        List<ErrorCode> errorList = new ArrayList<>();
        errorList.add(ErrorCode.ID_NULL);
        return new Object[][] {
            {  input,  expected, null},
            {  negativeInput,  null, errorList},
        };
    }

    @Test(dataProvider = "dpDelete")
    public void testDelete(Address input, int expected, List<ErrorCode> errorList) throws Exception {

        try {
            int created = helper.setSecured(true)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("/do/address", int.class);

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

        Address address = new Address();
        address.setId(8);

        Address address1 = new Address();
        address1.setId(80);

        List<ErrorCode> expectedList = new ArrayList<>();
        expectedList.add(ErrorCode.ID_NULL);
        return new Object[][] {
            {  address,  1, null},
            { address1,  0, expectedList}
        };
    }

    @Test(dataProvider = "dpReadAll")
    public void testReadAll(List<Address>  expectedAddress, List<ErrorCode> errorList) {

        try {
            List<Address> actual = helper.setSecured(true)
                    .setMethod(HttpMethod.GET)
                    .setInput(null)
                    .requestList("/do/address", Address.class);

            Assert.assertEquals(JsonConverter.toJson(actual)
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

        List<Address> personList = new ArrayList<>();
        Address expected = new Address(4, "gandhi st", "chennai", 123456);
        personList.add(expected);

        return new Object[][] {
            {personList, null}
        };
    }
}
