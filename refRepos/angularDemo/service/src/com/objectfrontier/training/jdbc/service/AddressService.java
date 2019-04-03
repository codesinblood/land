package com.objectfrontier.training.jdbc.service;

import static com.objectfrontier.training.jdbc.service.Constants.CITY;
import static com.objectfrontier.training.jdbc.service.Constants.ID;
import static com.objectfrontier.training.jdbc.service.Constants.POSTAL_CODE;
import static com.objectfrontier.training.jdbc.service.Constants.STREET;
import static com.objectfrontier.training.jdbc.service.Statements.CREATE_ADDRESS;
import static com.objectfrontier.training.jdbc.service.Statements.DELETE_ADDRESS;
import static com.objectfrontier.training.jdbc.service.Statements.READALL_ADDRESS;
import static com.objectfrontier.training.jdbc.service.Statements.READ_ADDRESS;
import static com.objectfrontier.training.jdbc.service.Statements.UPDATE_ADDRESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;

public class AddressService {

    public  int deleteAddress(Connection connection, long id) throws SQLException, AppException {


        PreparedStatement ps = connection.prepareStatement(DELETE_ADDRESS);
        ps.setLong(1, id);
        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new AppException(ErrorCode.ID_NULL);
        }
        return affectedRows;

    }

    public  Address updateAddress(Connection connection, Address address) throws SQLException, AppException {

        checkNull(address);
        PreparedStatement ps = connection.prepareStatement(UPDATE_ADDRESS);
        ps.setString(1, address.getStreet());
        ps.setString(2, address.getCity());
        ps.setLong(3, address.getPostalCode());
        ps.setLong(4, address.getId());
        int affectedrows = ps.executeUpdate();
        if(affectedrows == 0) {
            throw new AppException(ErrorCode.ID_NULL);
        }

        return address;
    }

    public  List<Address> readAllAddress(Connection connection) throws SQLException, AppException {


        PreparedStatement ps = connection.prepareStatement(READALL_ADDRESS);
        ResultSet rs = ps.executeQuery();
        List <Address> addressList = new ArrayList<>();


        while (rs.next()) {
        Address address = constructAddress(rs);
        addressList.add(address);
        }

        if (addressList.isEmpty()) {
            throw new AppException(ErrorCode.LIST_EMPTY);
        } else {
            return addressList;
        }
    }

    public  Address readAddress(Connection connection, long id) throws AppException, SQLException {



        PreparedStatement ps = connection.prepareStatement(READ_ADDRESS);

        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        Address address = null;
        if(rs.next()) {
             address = constructAddress(rs);
        }
        else {
            throw new AppException(ErrorCode.ID_NULL);
        }
        return address;
    }

    private Address constructAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong(ID));
        address.setStreet(rs.getString(STREET));
        address.setCity(rs.getString(CITY));
        address.setPostalCode(rs.getInt(POSTAL_CODE));
        return address;
    }

    public  Address createAddress(Connection connection, Address address) throws SQLException, AppException {

        checkNull(address);


        PreparedStatement ps = connection.prepareStatement(CREATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, address.getStreet());
        ps.setString(2, address.getCity());
        ps.setInt(3, address.getPostalCode());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        address.setId(rs.getInt(1));
        return address;
    }

    private void checkNull(Address address) throws AppException {


        if(isBlank(address.getStreet())) {
            throw new AppException(ErrorCode.STREET_NULL);
        } if(isBlank(address.getCity())) {
            throw new AppException(ErrorCode.CITY_NULL);
        } if(address.getPostalCode() == 0 ) {
            throw new AppException(ErrorCode.POSTAL_NULL);
        }
    }

    private boolean isBlank(String input) {

        if(Objects.nonNull(input)) {
            return input.trim().length() <= 0;
        }
        return true;
    }

    public List<Address> search(Connection connection, String[] fieldName, String searchText) throws SQLException {

        StringBuilder searchStatement = new StringBuilder()
                .append("SELECT id, street, city, postal_code")
                .append("  FROM address_service")
                .append(" WHERE ");

        int fieldCount = fieldName.length - 1;

        for (int i = 0; i <= fieldCount; i++) {
            String columnName = fieldName[i];
            if (i == 0) {
                searchStatement.append(getStatement(columnName));
            } else {
                searchStatement.append(" OR");
                searchStatement.append(getStatement(columnName));
            }
        }

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        prepareStatement = connection.prepareStatement(searchStatement.toString());
        for (int i = 0; i <= fieldCount; i++) { prepareStatement.setString(i + 1, searchText + "%"); }
        resultSet = prepareStatement.executeQuery();

        Address searchAddress = null;
        List<Address> searchList = new ArrayList<>();
        while (resultSet.next()) {
            searchAddress = constructAddress(resultSet);
            searchList.add(searchAddress);
        }

        if(searchList.isEmpty() == false) {

            return searchList;
        } else {
            throw new AppException(ErrorCode.ADDR_EMPTY);
        }
    }

    private StringBuilder getStatement(String columnName) {

        switch (columnName) {

        case "street":
            StringBuilder searchStreet = new StringBuilder()
                    .append(" street LIKE ? ");
            return searchStreet;

        case "city":
            StringBuilder searchCity = new StringBuilder()
                    .append(" city LIKE ? ");
            return searchCity;

        case "postal_code":
            StringBuilder searchPostalCode = new StringBuilder()
                    .append(" postal_code LIKE ? ");
            return searchPostalCode;

        }
        return null;
    }
}
