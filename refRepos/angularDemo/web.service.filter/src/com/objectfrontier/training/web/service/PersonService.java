package com.objectfrontier.training.web.service;

import static com.objectfrontier.training.web.service.Constants.BIRTH_DATE;
import static com.objectfrontier.training.web.service.Constants.CREATED_DATE;
import static com.objectfrontier.training.web.service.Constants.EMAIL;
import static com.objectfrontier.training.web.service.Constants.FIRST_NAME;
import static com.objectfrontier.training.web.service.Constants.ID;
import static com.objectfrontier.training.web.service.Constants.LAST_NAME;
import static com.objectfrontier.training.web.service.Statements.CREATE_PERSON;
import static com.objectfrontier.training.web.service.Statements.DELETE_PERSON;
import static com.objectfrontier.training.web.service.Statements.READALL_PERSON;
import static com.objectfrontier.training.web.service.Statements.READ_PERSON;
import static com.objectfrontier.training.web.service.Statements.UPDATE_ADMIN;
import static com.objectfrontier.training.web.service.Statements.UPDATE_PERSON;
import static com.objectfrontier.training.web.service.Statements.VALIDATE_EMAIL_CREATE;
import static com.objectfrontier.training.web.service.Statements.VALIDATE_EMAIL_UPDATE;
import static com.objectfrontier.training.web.service.Statements.VALIDATE_NAME_CREATE;
import static com.objectfrontier.training.web.service.Statements.VALIDATE_NAME_UPDATE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Address;
import com.objectfrontier.training.web.pojo.Person;

public class PersonService {

    public int delete(Connection connection, Person person) {

        log("%s", "Delete service started");
        int affectedRow = 0;
        try {

            PreparedStatement prepStatement = connection.prepareStatement(DELETE_PERSON);
            prepStatement.setLong(1, person.getId());
            affectedRow = prepStatement.executeUpdate();
            if (affectedRow == 0) {
                throw new AppException(ErrorCode.ID_NULL);
            }
            AddressService addressService = ObjectFactory.getAddressService();
            addressService.deleteAddress(connection, person.getAddress().getId());
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "Delete service Ended");
        return affectedRow;
    }

    public Person adminUpdate(Connection connection, Person inputPerson) {

        try {

            log("%s", "User Update service Started");
            validateNull(inputPerson);
            long birthDate = validateFieldsOnUpdate(connection, inputPerson);

            AddressService addressService = ObjectFactory.getAddressService();
            addressService.updateAddress(connection, inputPerson.getAddress());

            PreparedStatement prepStmt = connection.prepareStatement(UPDATE_ADMIN);
            prepStmt.setString(1, inputPerson.getFirstName());
            prepStmt.setString(2, inputPerson.getLastName());
            prepStmt.setString(3, inputPerson.getEmail());
            prepStmt.setDate(4, new Date(birthDate));
            prepStmt.setString(5, inputPerson.getPassword());
            prepStmt.setBoolean(6, inputPerson.getAdmin());
            prepStmt.setLong(7, inputPerson.getId());
            int affectedRows = prepStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new AppException(ErrorCode.ID_NULL);
            }
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "User Update service Ended");
        return inputPerson;
    }

    public Person userUpdate(Connection connection, Person inputPerson) {

        try {
            log("%s", "Admin Update service Started");
            validateNull(inputPerson);
            long birthDate = validateFieldsOnUpdate(connection, inputPerson);
            AddressService addressService = ObjectFactory.getAddressService();
            addressService.updateAddress(connection, inputPerson.getAddress());

            PreparedStatement prepStmt = connection.prepareStatement(UPDATE_PERSON);
            prepStmt.setString(1, inputPerson.getFirstName());
            prepStmt.setString(2, inputPerson.getLastName());
            prepStmt.setString(3, inputPerson.getEmail());
            prepStmt.setDate(4, new Date(birthDate));
            prepStmt.setString(5, inputPerson.getPassword());
            prepStmt.setLong(6, inputPerson.getId());
            int affectedRows = prepStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new AppException(ErrorCode.ID_NULL);
            }
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "Admin Update service Ended");
        return inputPerson;
    }


    public List<Person> readAllPerson(Connection connection) {

        log("%s", "ReadAll service Started");
        List<Person> list = null;
        try {
            PreparedStatement prepStmt = connection.prepareStatement(READALL_PERSON);
            ResultSet resultSet = prepStmt.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                Person person = constructPerson(resultSet);
                constructAddress(connection, resultSet, person);
                list.add(person);
            }

            if (list.isEmpty()) {
                throw new AppException(ErrorCode.LIST_EMPTY);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "ReadAll service Ended");
        return list;
    }

    private Person constructAddress(Connection connection, ResultSet resultSet, Person person) {

        try {
            AddressService addressService = ObjectFactory.getAddressService();
            Address address = addressService.readAddress(connection, resultSet.getLong("address_id"));
            person.setAddress(address);
        } catch(SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return person;
    }

    private Person constructPerson(ResultSet rs){

        Person person = null;
        try {
            person = ObjectFactory.getPerson();
            person.setId(rs.getInt(ID));
            person.setFirstName(rs.getString(FIRST_NAME));
            person.setLastName(rs.getString(LAST_NAME));
            person.setEmail(rs.getString(EMAIL));
            person.setBirthDate(rs.getDate(BIRTH_DATE).toString());
            person.setCreatedDate(rs.getTimestamp(CREATED_DATE));
            person.setAdmin(rs.getBoolean(Constants.IS_ADMIN));
            person.setPassword(rs.getString(Constants.PASSWORD));
        } catch(SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return person;
    }

    public Person readPerson(Connection connection, long id, boolean flag) {

        try {

            log("%s", "Read service Started");
            System.out.println("Read Method in service");
            PreparedStatement prepStmt = connection.prepareStatement(READ_PERSON);
            prepStmt.setLong(1, id);
            ResultSet resultSet = prepStmt.executeQuery();

            Person person = null;
            if (resultSet.next()) {
                person = constructPerson(resultSet);
                if (Boolean.FALSE.equals(flag)) {
                    return person;
                } else {
                    constructAddress(connection, resultSet, person);
                    log("%s", "Read service Ended");
                    return person;
                }
            } else {
                throw new AppException(ErrorCode.ID_NULL);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    public Person createPerson(Connection connection, Person inputPerson) {

        log("%s", "Create service Started");
        try {
            validateNull(inputPerson);
            long birthDate = validateFieldsOnCreate(connection, inputPerson);

            AddressService addressService = ObjectFactory.getAddressService();
            addressService.createAddress(connection, inputPerson.getAddress());

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, inputPerson.getFirstName());
            preparedStatement.setString(2, inputPerson.getLastName());
            preparedStatement.setString(3, inputPerson.getEmail());
            preparedStatement.setLong(4, inputPerson.getAddress().getId());
            preparedStatement.setDate(5, new Date(birthDate));
            Timestamp createdDate = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(6, createdDate);
            preparedStatement.setString(7, inputPerson.getPassword());
            preparedStatement.setBoolean(8, inputPerson.getAdmin());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (Boolean.TRUE.equals(resultSet.wasNull())) {
                throw new AppException(ErrorCode.ID_NOT_AUTOGENERATED);
            }
            resultSet.next();
            inputPerson.setCreatedDate(createdDate);
            inputPerson.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "Create service Ended");
        return inputPerson;
    }

    private void validateNull(Person inputPerson) {

        List<ErrorCode> errorCodes = new ArrayList<>();
        if (isBlank(inputPerson.getFirstName())) {
            errorCodes.add(ErrorCode.F_NAME_NULL);
        }
        if (isBlank(inputPerson.getLastName())) {
            errorCodes.add(ErrorCode.L_NAME_NULL);
        }
        if (isBlank(inputPerson.getBirthDate())) {
            errorCodes.add(ErrorCode.DOB_NULL);
        }
        if(isBlank(inputPerson.getPassword())) {
            errorCodes.add(ErrorCode.PASSWORD_NULL);
        }
        if(Objects.isNull(inputPerson.getAdmin())) {
            errorCodes.add(ErrorCode.IS_ADMIN_NULL);
        }
        if(Boolean.FALSE.equals(errorCodes.isEmpty())) {
            throw new AppException(errorCodes);
        }

    }

    public long validateFieldsOnCreate(Connection connection, Person inputPerson) {

        List<ErrorCode> errorList = new ArrayList<>();
        try {
            PreparedStatement prepStmt = connection.prepareStatement(VALIDATE_NAME_CREATE);
            prepStmt.setString(1, inputPerson.getFirstName());
            prepStmt.setString(2, inputPerson.getLastName());
            ResultSet resultSet = prepStmt.executeQuery();
            if (Boolean.TRUE.equals(resultSet.next())) {
                errorList.add(ErrorCode.F_L_NAME_UNIQUE);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        try {
            PreparedStatement prepStmt = connection.prepareStatement(VALIDATE_EMAIL_CREATE);
            prepStmt.setString(1, inputPerson.getEmail());
            ResultSet resultSet = prepStmt.executeQuery();
            if (Boolean.TRUE.equals(resultSet.next())) {
                errorList.add(ErrorCode.EMAIL_DUP);
            }
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        long birthDate = dateFormatValidation(inputPerson, errorList);

        if(Boolean.FALSE.equals(errorList.isEmpty())) {
            throw new AppException(errorList);
        }
        return birthDate;
    }

    private long dateFormatValidation(Person inputPerson, List<ErrorCode> errorList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setLenient(false);

        long birthDate = 0;
        try {
            java.util.Date date = dateFormat.parse(inputPerson.getBirthDate());
            birthDate = date.toInstant().toEpochMilli();
        } catch (ParseException e) {
            errorList.add(ErrorCode.DATE_FORMAT_ERROR);
        }
        return birthDate;
    }

    public long validateFieldsOnUpdate(Connection connection, Person inputPerson) {

        List<ErrorCode> errorList = new ArrayList<>();
        try {
            PreparedStatement prepareStmt = connection.prepareStatement(VALIDATE_NAME_UPDATE);
            prepareStmt.setString(1, inputPerson.getFirstName());
            prepareStmt.setString(2, inputPerson.getLastName());
            prepareStmt.setLong(3, inputPerson.getId());
            ResultSet resultSet = prepareStmt.executeQuery();
            if (Boolean.TRUE.equals(resultSet.next())) {
                errorList.add(ErrorCode.F_L_NAME_UNIQUE);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        try {
            PreparedStatement prepStmt = connection.prepareStatement(VALIDATE_EMAIL_UPDATE);
            prepStmt.setString(1, inputPerson.getEmail());
            prepStmt.setLong(2, inputPerson.getId());
            ResultSet resultSet = prepStmt.executeQuery();
            if (Boolean.TRUE.equals(resultSet.next())) {
                errorList.add(ErrorCode.EMAIL_DUP);
            }
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        long birthDate = dateFormatValidation(inputPerson, errorList);

        if(Boolean.FALSE.equals(errorList.isEmpty())) {
            throw new AppException(errorList);
        }
        return birthDate;
    }

    private boolean isBlank(String input) {
        if (Objects.nonNull(input)) {
            return input.trim().length() <= 0;
        }
        return true;
    }

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}
