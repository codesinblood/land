package com.objectfrontier.training.jdbc.service;

import static com.objectfrontier.training.jdbc.service.Constants.BIRTH_DATE;
import static com.objectfrontier.training.jdbc.service.Constants.CREATED_DATE;
import static com.objectfrontier.training.jdbc.service.Constants.EMAIL;
import static com.objectfrontier.training.jdbc.service.Constants.FIRST_NAME;
import static com.objectfrontier.training.jdbc.service.Constants.ID;
import static com.objectfrontier.training.jdbc.service.Constants.LAST_NAME;
import static com.objectfrontier.training.jdbc.service.Statements.CREATE_PERSON;
import static com.objectfrontier.training.jdbc.service.Statements.DELETE_PERSON;
import static com.objectfrontier.training.jdbc.service.Statements.READALL_PERSON;
import static com.objectfrontier.training.jdbc.service.Statements.READ_PERSON;
import static com.objectfrontier.training.jdbc.service.Statements.UPDATE_PERSON;

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

import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class PersonService {

    public int delete(Connection connection, Person person) throws SQLException, AppException {

        PreparedStatement ps = connection.prepareStatement(DELETE_PERSON);
        ps.setLong(1, person.getId());
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new AppException(ErrorCode.ID_NULL);
        }
        AddressService addService = new AddressService();
        addService.deleteAddress(connection, person.getAddress().getId());
        return affectedRows;
    }

    public Person update(Connection connection, Person inputPerson) throws SQLException, AppException, ParseException {

        validateNull(inputPerson);
        validateNameUnique(inputPerson, connection);
        validateUniqueEmail(inputPerson, connection);
        long birthDate = checkBirthDate(inputPerson);

        AddressService addService = new AddressService();
        addService.updateAddress(connection, inputPerson.getAddress());

        PreparedStatement ps = connection.prepareStatement(UPDATE_PERSON);
        ps.setString(1, inputPerson.getFirstName());
        ps.setString(2, inputPerson.getLastName());
        ps.setString(3, inputPerson.getEmail());
        ps.setDate(4, new Date(birthDate));
        ps.setLong(5, inputPerson.getId());
        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new AppException(ErrorCode.ID_NULL);
        }
        return inputPerson;
    }

    public List<Person> readAllPerson(Connection connection) throws SQLException, AppException {

        ResultSet rs = connection.createStatement().executeQuery(READALL_PERSON);

        List<Person> list = new ArrayList<>();
        while (rs.next()) {

            Person person = constructPerson(rs);
            constructAddress(connection, rs, person);
            list.add(person);
        }

        if (list.isEmpty()) {
            throw new AppException(ErrorCode.LIST_EMPTY);
        }
        return list;
    }

    private Person constructAddress(Connection connection, ResultSet rs, Person person)
            throws SQLException, AppException {

        Address address = new Address();
        AddressService addService = new AddressService();
        address = addService.readAddress(connection, rs.getLong("address_id"));
        person.setAddress(address);
        return person;
    }

    private Person constructPerson(ResultSet rs) throws SQLException {

        Person person = new Person();
        person.setId(rs.getInt(ID));
        person.setFirstName(rs.getString(FIRST_NAME));
        person.setLastName(rs.getString(LAST_NAME));
        person.setEmail(rs.getString(EMAIL));
        person.setBirthDate(rs.getDate(BIRTH_DATE).toString());
        person.setCreatedDate(rs.getTimestamp(CREATED_DATE));
        return person;
    }

    public Person readPerson(Connection connection, long id, boolean flag) throws SQLException, AppException {


        PreparedStatement ps = connection.prepareStatement(READ_PERSON);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        Person person = null;
        if (rs.next()) {
            person = constructPerson(rs);
            if (flag == false) {
                return person;
            } else {
                constructAddress(connection, rs, person);
                return person;
            }
        } else {
            throw new AppException(ErrorCode.ID_NULL);
        }
    }

    public Person createPerson(Connection connection, Person inputPerson) throws SQLException, AppException, ParseException {

        validateNull(inputPerson);
        validateNameUnique(inputPerson, connection);
        validateUniqueEmail(inputPerson, connection);
        long birthDate = checkBirthDate(inputPerson);

        AddressService addService = new AddressService();
        addService.createAddress(connection, inputPerson.getAddress());

        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, inputPerson.getFirstName());
        preparedStatement.setString(2, inputPerson.getLastName());
        preparedStatement.setString(3, inputPerson.getEmail());
        preparedStatement.setLong(4, inputPerson.getAddress().getId());
        preparedStatement.setDate(5, new Date(birthDate));
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(6, createdDate);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.wasNull() == true) {
            throw new AppException(ErrorCode.ID_NOT_AUTOGENERATED);
        }
        resultSet.next();
        inputPerson.setCreatedDate(createdDate);
        inputPerson.setId(resultSet.getLong(1));
        return inputPerson;
    }

    private long checkBirthDate(Person inputPerson) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {
            java.util.Date date = sdf.parse(inputPerson.getBirthDate());
            long birthDate = date.toInstant().toEpochMilli();
            return birthDate;
        } catch (ParseException e) {

            throw new AppException(ErrorCode.DATE_FORMAT_ERROR);
        }
    }

    private void validateUniqueEmail(Person inputPerson, Connection connection) throws SQLException, AppException {

        StringBuilder createQuery = new StringBuilder().append("select email from person_service where email = ?");
        PreparedStatement ps = connection.prepareStatement(createQuery.toString());
        ps.setString(1, inputPerson.getEmail());
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            throw new AppException(ErrorCode.EMAIL_DUP);
        }
    }

    private void validateNull(Person inputPerson) throws AppException {

        if (isBlank(inputPerson.getFirstName())) {
            throw new AppException(ErrorCode.F_NAME_NULL);
        }
        if (isBlank(inputPerson.getLastName())) {
            throw new AppException(ErrorCode.L_NAME_NULL);
        }
        if (isBlank(inputPerson.getBirthDate())) {
            throw new AppException(ErrorCode.DOB_NULL);
        }
    }

    private boolean isBlank(String input) {
        if (Objects.nonNull(input)) {
            return input.trim().length() <= 0;
        }
        return true;
    }

    private void validateNameUnique(Person inputPerson, Connection connection) throws SQLException, AppException {
        StringBuilder NameUniqueQuery = new StringBuilder()
                .append("select first_name, last_name from person_service where first_name = ? and last_name = ? ");
        PreparedStatement ps = connection.prepareStatement(NameUniqueQuery.toString());
        ps.setString(1, inputPerson.getFirstName());
        ps.setString(2, inputPerson.getLastName());
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            throw new AppException(ErrorCode.F_L_NAME_UNIQUE);
        }
    }

}
