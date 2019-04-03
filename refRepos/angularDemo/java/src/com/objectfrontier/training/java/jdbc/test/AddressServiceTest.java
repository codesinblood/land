package com.objectfrontier.training.java.jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.java.jdbc.connection.ConnectionManager;
import com.objectfrontier.training.java.jdbc.service.Address;
import com.objectfrontier.training.java.jdbc.service.AddressService;

public class AddressServiceTest {
private ConnectionManager con;
private AddressService addressService;

    @BeforeClass
    private void init() {
     this.con = new ConnectionManager();
     this.addressService = new AddressService();
    }


    @Test(priority = 1, dataProvider = "createPositiveData")
    private void createPositiveTest(Connection connection, Address address, Address expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            Address createAddress = addressService.createAddress(connection, address);
            Assert.assertEquals(createAddress.toString(), expected.toString());
            connection.commit();
        } catch(Exception e) {
            Assert.fail("error");
            connection.rollback();
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 2, dataProvider = "readPositiveData")
    private void readPositiveTest(Connection connection, Long id, Address expected) throws SQLException {

        try {
          Address readedAddress = addressService.readAddress(connection, id);
          Assert.assertEquals(readedAddress.toString(), expected.toString());
      } catch(Exception e) {
          Assert.fail("error");
      }
      finally {
          con.release(connection);
      }
    }

  @Test(priority = 3,dataProvider = "readAllPositiveData")
  private void readAllPositiveTest(Connection connection, List<Address> expected) throws SQLException {

      try {

          List<Address> actual = addressService.readAllAddress(connection);
          Assert.assertEquals(actual.toString(), expected.toString());
      } catch(Exception e) {
          Assert.fail(e.getMessage());
      }
      finally {
          con.release(connection);
      }
  }

    @Test(priority = 4,dataProvider = "updatePositiveData")
  private void updatePositiveTest(Connection connection, Address address, Address expected) throws SQLException {

      try {
          connection.setAutoCommit(false);
          Address updateAddress = addressService.updateAddress(connection, address);
          Assert.assertEquals(updateAddress.toString(), expected.toString());
          connection.commit();
      } catch(Exception e) {
          Assert.fail("error");
          connection.rollback();
      }
      finally {
          con.release(connection);
      }
  }

    @Test(priority = 5,dataProvider = "deletePositiveData")
  private void deletePositiveTest(Connection connection, Long id, int expected) throws SQLException {

      try {
          connection.setAutoCommit(false);
          int affectedRows = addressService.deleteAddress(connection, id);
          Assert.assertEquals(affectedRows, expected);
          connection.commit();
      } catch(Exception e) {
          Assert.fail(e.getMessage());
          connection.rollback();
      }
      finally {
          con.release(connection);
      }
  }

    @Test(priority = 6,dataProvider = "createNegativeData")
    private void createNegativeTest(Connection connection, Address address, String expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            addressService.createAddress(connection, address);
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



    @Test(priority = 7,dataProvider = "deleteNegativeData")
  private void deleteNegativeTest(Connection connection, long id, String expected) throws SQLException {

      try {
          connection.setAutoCommit(false);
          addressService.deleteAddress(connection, id);
          connection.rollback();
      } catch(Exception e) {

          Assert.assertEquals(e.getMessage(), expected);
          connection.commit();
      }
      finally {
          con.release(connection);
      }
  }

    @Test(priority = 8, dataProvider = "readNegativeData")
    private void readNegativeTest(Connection connection, long id, String expected) throws SQLException {

        try {
            connection.setAutoCommit(false);
            addressService.readAddress(connection, id);
            Assert.fail("Error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 9, dataProvider = "readAllNegativeData")
    private void readAllNegativeTest(Connection connection, String expected) throws SQLException {

        try {
            AddressService addService = new AddressService();
            addService.readAllAddress(connection);
            Assert.fail("error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }

    @Test(priority = 10, dataProvider = "updateNegativeData")
    private void updateNegativeTest(Connection connection, Address address, String expected) throws SQLException {

        try {
            addressService.updateAddress(connection, address);
            Assert.fail("error");
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expected);
        }
        finally {
            con.release(connection);
        }
    }

    @DataProvider
    private Object[][] deleteNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        long id = 7850;
        String expected = "Id not exist";

        return new Object[][] {
            {connection, id, expected}
        };
    }


    @DataProvider
    private Object[][] createPositiveData() throws SQLException {

        Connection connection = con.getConnection();
        Address address  = new Address();
        address.setStreet("vvs1");
        address.setCity("chennai");
        address.setPostalCode(12345);
        Address expected = new Address();
        expected.setStreet("vvs1");
        expected.setCity("chennai");
        expected.setPostalCode(12345);
        return new Object[][] {
            {connection, address, expected}
        };
    }

    @DataProvider
    private Object[][] readPositiveData() throws SQLException {

        Connection connection = con.getConnection();
        long id = 116;

        Address expected = new Address();
        expected.setCity("chennai");
        expected.setPostalCode(12345);
        expected.setStreet("vvs1");

        return new Object[][] {
            {connection, id, expected}
        };
    }

    @DataProvider
    private Object[][] readAllPositiveData() throws SQLException {

        Connection connection = con.getConnection();
        List<Address> expectedList = new ArrayList<>();


        Address expected = new Address();
        expected.setCity("chennai");
        expected.setPostalCode(12345);
        expected.setStreet("vvs1");
        expectedList.add(expected);

        return new Object[][] {
            {connection, expectedList}
        };
    }

    @DataProvider
    private Object[][] updatePositiveData() throws SQLException {

        Connection connection = con.getConnection();
        Address address = new Address();
        address.setId(115);
        address.setCity("chennais");
        address.setStreet("gandhu sts");
        address.setPostalCode(164646);

        Address expected = new Address();
        expected.setCity("chennais");
        expected.setStreet("gandhu sts");

        expected.setPostalCode(164646);
        return new Object[][] {
            {connection, address, expected}
        };
    }

    @DataProvider
    private Object[][] deletePositiveData() throws SQLException {

        Connection connection = con.getConnection();
        long id = 116;
        int expected = 1;

        return new Object[][] {
            {connection, id, expected}
        };
    }

    @DataProvider
    private Object[][] createNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        Address address = new Address();
//        address.setCity("banglore");
        address.setPostalCode(25260);
        address.setStreet("ghgg");

        String expected = "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";

        return new Object[][] {
            {connection, address, expected}
           };
    }

    @DataProvider
    private Object[][] readNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        long id = 11;

        String expected = "Id not exist";
        return new Object[][] {
            {connection, id, expected}
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

    @DataProvider
    private Object[][] updateNegativeData() throws SQLException {

        Connection connection = con.getConnection();
        Address address = new Address();
        address.setId(896);
        address.setCity("bangloree");
        address.setPostalCode(252600);
        address.setStreet("ghggd");
        String expected = "Id not exist";

        return new Object[][] {
            {connection, address,  expected}
        };
    }

}
