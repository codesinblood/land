// AddressServiceTest

package com.objectfrontier.training.jdbc.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.service.AddressService;

public class AddressServiceTest {

    private ConnectionManager connnectionManager;
    private AddressService addressService;
    private static boolean flag = true;
    private Connection connection;

    @BeforeTest
    private void init() throws SQLException, IOException {

        this.connnectionManager = new ConnectionManager();
        this.addressService = new AddressService();
    }

    @BeforeMethod
    private void getConnection() throws SQLException, IOException {
        this.connection = connnectionManager.getConnection();
    }

    @AfterMethod
    private void release() throws SQLException {
        connnectionManager.releaseConnection(connection, flag);
    }

    @AfterTest
    private void resetId() throws SQLException, IOException {

        this.connection= connnectionManager.getConnection();
        connection.createStatement().execute("ALTER TABLE address_service AUTO_INCREMENT = 1");
    }

    @Test(priority = 1, dataProvider = "dpCreate_positive")
    private void testCreate_positive( Address address, Address expected) throws SQLException {

            Address createAddress = addressService.createAddress(connection, address);
            AssertJUnit.assertEquals(createAddress.toString(), expected.toString());
            flag = true;
    }

    @DataProvider
    private Object[][] dpCreate_positive() throws SQLException {

        Address address = new Address("kattaboman street","chennai",600014);
        Address expected = new Address("kattaboman street","chennai",600014);

        return new Object[][] {
            {address, expected }
        };
    }

    @Test(priority = 3, dataProvider = "dpRead_positive")
    private void testRead_positive(Long id, Address expected) throws SQLException {

            Address readedAddress = addressService.readAddress(connection, id);
            AssertJUnit.assertEquals(readedAddress.toString(), expected.toString());
    }

    @DataProvider
    private Object[][] dpRead_positive() throws SQLException {

        long id = 1;
        Address expected = new Address("kattaboman street","chennai",600014);

        return new Object[][] {
            {id, expected }
        };
    }

    @Test(priority = 4, dataProvider = "dpReadAll_positive")
    private void testReadAll_positive(List<Address> expected) throws SQLException {

            List<Address> actual = addressService.readAllAddress(connection);
            AssertJUnit.assertEquals(actual.toString(), expected.toString());
    }

    @DataProvider
    private Object[][] dpReadAll_positive() throws SQLException {

        List<Address> expectedList = new ArrayList<>();

        Address expected = new Address("kattaboman street","chennai",600014);
        expectedList.add(expected);

        return new Object[][] {
            {expectedList}
        };
    }

    @Test(priority = 5, dataProvider = "dpUpdate_positive")
    private void testUpdate_positive(Address address, Address expected) throws SQLException {

            Address updateAddress = addressService.updateAddress(connection, address);
            AssertJUnit.assertEquals(updateAddress.toString(), expected.toString());
            flag = true;
    }

    @DataProvider
    private Object[][] dpUpdate_positive() throws SQLException {

        Address address = new Address(1,"abcstreet","chn",60032);
        Address expected = new Address(1,"abcstreet","chn",60032);

        return new Object[][] {
            {address, expected }
        };
    }

    @Test(priority = 9, dataProvider = "dpDelete_positive")
    private void testDelete_positive( Long id, int expected) throws SQLException {

             int affectedRows = addressService.deleteAddress(connection, id);
             AssertJUnit.assertEquals(affectedRows, expected);
             flag = true;
     }

     @DataProvider
    private Object[][] dpDelete_positive() throws SQLException {

        long id = 1;
        int expected = 1;

        return new Object[][] {
            { id, expected }
        };
    }

    @Test(priority = 2, dataProvider = "dpCreate_negative")
    private void testCreate_negative(Address address, String expected) throws SQLException {

        try {
            addressService.createAddress(connection, address);
        } catch (AppException exception) {
            AssertJUnit.assertEquals(exception.getErrorMsg(), expected);
            flag = false;
        }
    }

    @DataProvider
    private Object[][] dpCreate_negative() {

        Address address = new Address(null, "chenn", 600125);
        Address addr = new Address("veerabhatren", null, 600014);

        String expected = "Street is null";
        String expect = "City is null";

        return new Object[][] {
            {address, expected},
            {addr, expect}
        };
    }

    @Test(priority = 10, dataProvider = "dpRead_negative")
    private void testRead_negative(long id, String expected) throws SQLException {

        try {
            addressService.readAddress(connection, id);
        } catch(AppException exception) {
            AssertJUnit.assertEquals(exception.getErrorMsg(), expected);
        }
    }

    @DataProvider
    private Object[][] dpRead_negative() throws SQLException {

        long id = 116;

        String expected = "Id Not exists";
        return new Object[][] {
            {id, expected}
        };
    }

    @Test(priority = 11, dataProvider = "dpReadAll_negative")
    private void testReadAll_negative(String expected) throws SQLException {

        try {
            addressService.readAllAddress(connection);
        } catch(AppException exception) {
            AssertJUnit.assertEquals(exception.getErrorMsg(), expected);
        }
    }

    @DataProvider
    private Object[][] dpReadAll_negative() throws SQLException {

        String expected = "List is empty";

        return new Object[][] {
            {expected }
        };
    }

    @Test(priority = 6, dataProvider = "dpUpdate_negative")
    private void testUpdate_negative(Address address, String expected) throws SQLException {

        try {
            addressService.updateAddress(connection, address);
        } catch(AppException exception) {
            AssertJUnit.assertEquals(exception.getErrorMsg(), expected);
        }
    }

    @DataProvider
    private Object[][] dpUpdate_negative() throws SQLException {

        Address address = new Address(1, null, "chenn", 600125);
        Address address1 = new Address(1, "veerabhatren", null, 600014);
        Address address2 = new Address(1, "veerabhatren", "chennai", 0);
        Address address3 = new Address(100, "veerabhatren", "chennai", 60021);

        String expected = "Street is null";
        String expected1 = "City is null";
        String expected2 = "Postal Code is null";
        String expected3 = "Id Not exists";

        return new Object[][] {
            {address, expected},
            {address1, expected1},
            {address2, expected2},
            {address3, expected3}
        };
 }

    @Test(priority = 12, dataProvider = "dpDelete_negative")
    private void testDelete_negative(long id, String expected) throws SQLException {

        try {
            addressService.deleteAddress(connection, id);
        } catch(AppException exception) {
            Assert.assertEquals(exception.getErrorMsg(), expected);
            flag = false;
        }
    }

    @DataProvider
    private Object[][] dpDelete_negative() throws SQLException {

        long id = 7850;
        String expected = "Id Not exists";

        return new Object[][] {
            { id, expected}
        };
    }

    @Test(priority = 7, dataProvider = "dpSearch_positive")
    private void testSearch_positive(String[] fieldName, String searchText, List<Address> expectedList) throws SQLException {

            List<Address> searchAddress = addressService.search(connection, fieldName, searchText);
            AssertJUnit.assertEquals(searchAddress.toString(), expectedList.toString());
            flag = true;
    }

    @DataProvider
    private Object[][] dpSearch_positive() throws SQLException {

     String searchText = "c";
     String[] fieldName = { "street", "city" };

     List<Address> expectedList = new ArrayList<>();
     Address expected = new Address("abcstreet","chn",60032);
     expectedList.add(expected);

     return new Object[][] {
             {fieldName, searchText, expectedList}
         };
     }

    @Test(priority = 8, dataProvider = "dpSearch_negative")
    private void testSearch_negative(String[] fieldName, String searchText, String expected)
            throws SQLException {

        try {
            addressService.search(connection, fieldName, searchText);
        } catch (AppException exception) {
            AssertJUnit.assertEquals(exception.getErrorMsg(), expected);
            flag = false;
        }
    }

    @DataProvider
    private Object[][] dpSearch_negative() throws SQLException {

        String searchText = "z";
        String[] fieldName = {"street", "city"};

        String expected = "Address not found";

        return new Object[][] {
            {fieldName, searchText, expected }
        };
    }
}
