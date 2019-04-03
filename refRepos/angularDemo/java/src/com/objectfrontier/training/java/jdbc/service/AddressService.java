package com.objectfrontier.training.java.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.objectfrontier.training.java.jdbc.connection.ConnectionManager;
import com.objectfrontier.training.java.jdbc.exception.CustomException;

public class AddressService {

    public static void main(String[] args) throws SQLException, CustomException {

        AddressService addressService = new AddressService();
        Scanner input = new Scanner(System.in);
        ConnectionManager conManager = new ConnectionManager();
        Connection connection = conManager.getConnection();

        Address inputAddress = Input.getCreateAddressInput();
        Address createdAddress = addressService.createAddress(connection, inputAddress);
        log("%s", createdAddress);

        long  readAddress = Input.getReadAddressInput();
        Address specificAddress = addressService.readAddress(connection, readAddress);
        log("%s", specificAddress);
        try {
        List<Address> addressList = addressService.readAllAddress(connection);
        log("%s", addressList);
        } catch (Exception e) {
            log("%s", e.getMessage());
        }

        Address  updateAddress = Input.getUpdateAddressInput();
        Address updatedAddress = addressService.updateAddress(connection, updateAddress);
        log("%s", updatedAddress);


        long deletAddress = Input.getReadAddressInput();
        addressService.deleteAddress(connection, deletAddress);

        connection.close();
        input.close();
    }

    public  int deleteAddress(Connection connection, long id) throws SQLException, CustomException {

        String readSql = "DELETE FROM address WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(readSql);
        ps.setLong(1, id);
        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new CustomException("Id not exist");
        }
        return affectedRows;

    }

    public  Address updateAddress(Connection connection, Address address) throws SQLException, CustomException {

        String readSql = "UPDATE address SET street = ?, city = ?, postal_code = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(readSql);
        ps.setString(1, address.getStreet());
        ps.setString(2, address.getCity());
        ps.setLong(3, address.getPostalCode());
        ps.setLong(4, address.getId());
        int affectedrows = ps.executeUpdate();
        if(affectedrows == 0) {
            throw new CustomException("Id not exist");
        }

        return address;
    }

    public  List<Address> readAllAddress(Connection connection) throws SQLException, CustomException {

        String readSql = "SELECT id, street, city, postal_code FROM address;";
        PreparedStatement ps = connection.prepareStatement(readSql);
        ResultSet rs = ps.executeQuery();
        List <Address> addressList = new ArrayList<>();


        while (rs.next()) {
        Address address = constructAddress(rs);
        addressList.add(address);
        }

        if (addressList.isEmpty()) {
            throw new CustomException("List is not exist");
        } else {
            return addressList;
        }
    }

    public  Address readAddress(Connection connection, long id) throws SQLException, CustomException {

        String readSql = "SELECT id, street, city, postal_code FROM address WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(readSql);

        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        Address address = null;
        if(rs.next()) {
             address = constructAddress(rs);
        } else {
            throw new CustomException("Id not exist");
        }
        return address;
    }

    private Address constructAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong("id"));
        address.setStreet(rs.getString("street"));
        address.setCity(rs.getString("city"));
        address.setPostalCode(rs.getInt("postal_code"));
        return address;
    }

    public  Address createAddress(Connection connection, Address address) throws SQLException {

        String createSql = "insert into address(street, city, postal_code) values(?,?,?)";

        PreparedStatement ps = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, address.getStreet());
        ps.setString(2, address.getCity());
        ps.setInt(3, address.getPostalCode());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        address.setId(rs.getInt(1));
        return address;
    }

    private static void log(String format, Object args) {

        System.out.format(format + "\n", args);
    }


}
