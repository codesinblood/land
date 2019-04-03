package com.objectfrontier.training.web.service;

import static com.objectfrontier.training.web.service.Constants.CITY;
import static com.objectfrontier.training.web.service.Constants.ID;
import static com.objectfrontier.training.web.service.Constants.POSTAL_CODE;
import static com.objectfrontier.training.web.service.Constants.STREET;
import static com.objectfrontier.training.web.service.Statements.CREATE_ADDRESS;
import static com.objectfrontier.training.web.service.Statements.DELETE_ADDRESS;
import static com.objectfrontier.training.web.service.Statements.READALL_ADDRESS;
import static com.objectfrontier.training.web.service.Statements.READ_ADDRESS;
import static com.objectfrontier.training.web.service.Statements.UPDATE_ADDRESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Address;

public class AddressService {

    public  int deleteAddress(Connection connection, long id) {

        int affectedRows = 0;
        try {

            PreparedStatement ps = connection.prepareStatement(DELETE_ADDRESS);
            ps.setLong(1, id);
            affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new AppException(ErrorCode.ID_NULL);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return affectedRows;
    }

    public  Address updateAddress(Connection connection, Address address) {

        try {
            System.out.println(address.getPostalCode());
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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return address;
    }

    public  List<Address> readAllAddress(Connection connection) {

        try {
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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    public  Address readAddress(Connection connection, long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(READ_ADDRESS);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Address address = null;
            if(rs.next()) {
                address = constructAddress(rs);
            }
            else {
//                List<ErrorCode> errorCodes = new ArrayList<>();
//                errorCodes.add(ErrorCode.ID_NULL);
                throw new AppException(ErrorCode.ID_NULL);
//                throw new AppException(ErrorCode.ID_NULL);
            }
            return address;
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    private Address constructAddress(ResultSet rs) {

        try {
        Address address = new Address();
        address.setId(rs.getLong(ID));
        address.setStreet(rs.getString(STREET));
        address.setCity(rs.getString(CITY));
        address.setPostalCode(rs.getInt(POSTAL_CODE));
        return address;
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    public  Address createAddress(Connection connection, Address address) {

        try {
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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    private void checkNull(Address address) {
        List<ErrorCode> errorCodes = new ArrayList<>();
        if(isBlank(address.getStreet())) {
            errorCodes.add(ErrorCode.STREET_NULL);
        } if(isBlank(address.getCity())) {
            errorCodes.add(ErrorCode.CITY_NULL);
        } if(address.getPostalCode() == 0 ) {
            errorCodes.add(ErrorCode.POSTAL_NULL);
        }


        log("%s", errorCodes.isEmpty());
        if (errorCodes.isEmpty() == false) {
            errorCodes.forEach(x -> System.out.println(x));
            log("%s", errorCodes.toString());
            throw new AppException(errorCodes);
        }
    }

    private boolean isBlank(String input) {

        if(Objects.nonNull(input)) {
            return input.trim().length() <= 0;
        }
        return true;
    }

    public List<Address> search(Connection connection, String[] fieldName, String searchText) {

        try {
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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
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

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}
