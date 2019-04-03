package com.objectfrontier.training.jdbc.servlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;

public class AddressServletTest {

    @BeforeTest
    private void init() throws SQLException {
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        ConnectionManager con = new ConnectionManager();
        Connection connection = con.get();
        connection.createStatement().execute("ALTER TABLE address_service AUTO_INCREMENT = 1");
        connection.close();
    }

    @Test(dataProvider = "dpCreate", priority = 1 )
    public void testCreate(Address input, Address expected, List<ErrorCode> expectedErrors) {

        RequestHelper helper = new RequestHelper();
        try {

            Address created = helper.setSecured(false)
                    .setMethod(HttpMethod.PUT)
                    .setInput(input)
                    .requestObject("address", Address.class);

            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if (e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), expectedErrors);
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    private Object[][] dpCreate() throws SQLException {

        Address input = new Address("gandhi st","chennai",123456);
        Address expected = new Address(1,"gandhi st","chennai",123456);
        Address negActual = new Address(null, null, 0);
        List<ErrorCode> expectedErrors = new ArrayList<>();
        ErrorCode error = ErrorCode.STREET_NULL;
        expectedErrors.add(error);
        error = ErrorCode.CITY_NULL;
        expectedErrors.add(error);
        error = ErrorCode.POSTAL_NULL;
        expectedErrors.add(error);

        return new Object[][] {
            {input, expected, null },
            {negActual, null, expectedErrors}
        };
    }

    @Test(dataProvider = "dpRead", priority = 2)
    public void testRead(int input, Address expectedAddress, List<ErrorCode> expectedList) {

        RequestHelper helper =  new RequestHelper();
        try {

            RequestHelper.setBaseUrl("http://localhost:8080/ws/");
            Address readedvalue = helper.setSecured(false)
                    .setMethod(HttpMethod.GET)
                    .requestObject("address?id=" + input, Address.class);

            Assert.assertEquals(JsonConverter.toJson(readedvalue)
                    , JsonConverter.toJson(expectedAddress));

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

        int addressId= 1;
        Address expectedAddress = new Address(1,"gandhi st","chennai",123456);
        int addressID = 6;
        List<ErrorCode> expectedList  = new ArrayList<>();
        expectedList.add(ErrorCode.ID_NULL);

        return new Object[][] { {addressId , expectedAddress, null},
            {addressID, null,  expectedList}
        };
    }

    @Test(dataProvider = "dpReadAll_Positive", priority = 3)
    public void testReadAll_Positive(List<Address>  expectedAddress) {

        RequestHelper helper =  new RequestHelper();
        try {

            List<Address> listAddress = helper.setSecured(false)
                    .setMethod(HttpMethod.GET)
                    .requestObject("address", Address.class, true);

            Assert.assertEquals(JsonConverter.toJson(listAddress)
                    , JsonConverter.toJson(expectedAddress));

        } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }

    @DataProvider
    Object[][] dpReadAll_Positive() throws SQLException {

        List<Address> addressList = new ArrayList<>();
        Address expectedAddress = new Address(1,"gandhi st","chennai",123456);
        addressList.add(expectedAddress);

        return new Object[][] {
            {addressList},
        };
    }

    @Test(dataProvider = "dpReadAll_Negative", priority = 6)
    public void testReadAll_Negative(List<ErrorCode>  expectedError) {

        RequestHelper helper =  new RequestHelper();
        try {

             helper.setSecured(false)
                    .setMethod(HttpMethod.GET)
                    .requestObject("address", Address.class, true);

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

    @Test(dataProvider = "dpUpdate", priority = 4)
    public void testUpdate(Address input, Address expected,  List<ErrorCode> expectedErrors) {

        RequestHelper helper = new RequestHelper();
        try {

            RequestHelper.setBaseUrl("http://localhost:8080/ws/");
            Address created = helper.setSecured(false)
                    .setMethod(HttpMethod.POST)
                    .setInput(input)
                    .requestObject("address", Address.class);
            System.out.println(created.toString());
            Assert.assertEquals(JsonConverter.toJson(created), JsonConverter.toJson(expected));

        } catch (Exception e) {
            if (e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), expectedErrors);
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    @DataProvider
    private Object[][] dpUpdate() throws SQLException {

        //        Address positiveAddress
        Address address = new Address(1, null , null, 0);
        Address address1 = new Address(154, "veerabhatren", "chennai", 60021);
        Address address2 = new Address(1,"gandhi st","chennai",123456);

        List<ErrorCode> expectedErrors  = new ArrayList<>();
        ErrorCode expected1 = ErrorCode.STREET_NULL;
        expectedErrors.add(expected1);
        ErrorCode expected2 = ErrorCode.CITY_NULL;
        expectedErrors.add(expected2);
        ErrorCode expected3 = ErrorCode.POSTAL_NULL;
        expectedErrors.add(expected3);
        ErrorCode expected4 = ErrorCode.ID_NULL;
        List<ErrorCode> expectedErrorsForNull  = new ArrayList<>();
        expectedErrorsForNull.add(expected4);

        return new Object[][] {
            {address, null, expectedErrors},
            {address1, null,  expectedErrorsForNull},
            {address2, address2, null}
        };
    }

    @Test(priority = 5 , dataProvider = "dpDelete")
    private void testDelete(int input, int expected, List<ErrorCode> expectedErrors) {

        RequestHelper helper = new RequestHelper();
        try {

            int id = helper.setSecured(false)
                    .setMethod(HttpMethod.POST)
                    .requestObject("address?id=" + input, int.class);
            AssertJUnit.assertEquals(JsonConverter.toJson(id), JsonConverter.toJson(expected));
        } catch (Exception e) {
            if(e instanceof AppException) {
                Assert.assertEquals(((AppException) e).getErrorCodes(), expectedErrors);
            }
        }
    }

    @DataProvider
    private Object[][] dpDelete() throws SQLException {

        int id = 1;
        int negId = 23;
        List<ErrorCode> expectedErrors = new ArrayList<>();
        ErrorCode error = ErrorCode.ID_NULL;
        expectedErrors.add(error);

        return new Object[][] {
            {id, id, null },
            {negId, id, expectedErrors}
        };
    }
}
