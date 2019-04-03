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

    public int delete(Connection connection, Person person) {

        int affectedRows = 0;
        try {

            PreparedStatement ps = connection.prepareStatement(DELETE_PERSON);
            ps.setLong(1, person.getId());
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new AppException(ErrorCode.ID_NULL);
            }
            AddressService addService = new AddressService();
            addService.deleteAddress(connection, person.getAddress().getId());
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return affectedRows;
    }

    public Person update(Connection connection, Person inputPerson) {

        try {

            validateNull(inputPerson);
            long birthDate = validateFields(connection, inputPerson);
//            validateNameUnique(inputPerson, connection);
//            validateUniqueEmail(inputPerson, connection);
//            long birthDate = checkBirthDate(inputPerson);

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
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return inputPerson;
    }

    public List<Person> readAllPerson(Connection connection) {

        List<Person> list = null;
        try {
            ResultSet rs = connection.createStatement().executeQuery(READALL_PERSON);

            list = new ArrayList<>();
            while (rs.next()) {

                Person person = constructPerson(rs);
                constructAddress(connection, rs, person);
                list.add(person);
            }

            if (list.isEmpty()) {
                throw new AppException(ErrorCode.LIST_EMPTY);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return list;
    }

    private Person constructAddress(Connection connection, ResultSet rs, Person person) {

        try {
        Address address = new Address();
        AddressService addService = new AddressService();
        address = addService.readAddress(connection, rs.getLong("address_id"));
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
        person = new Person();
        person.setId(rs.getInt(ID));
        person.setFirstName(rs.getString(FIRST_NAME));
        person.setLastName(rs.getString(LAST_NAME));
        person.setEmail(rs.getString(EMAIL));
        person.setBirthDate(rs.getDate(BIRTH_DATE).toString());
        person.setCreatedDate(rs.getTimestamp(CREATED_DATE));
        } catch(SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return person;
    }

    public Person readPerson(Connection connection, long id, boolean flag) {

        try {

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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
    }

    public Person createPerson(Connection connection, Person inputPerson) {

        try {
            validateNull(inputPerson);
            long birthDate = validateFields(connection, inputPerson);


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
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
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
        if(errorCodes.isEmpty() == false) {
            throw new AppException(errorCodes);
        }

    }
        public long validateFields(Connection connection, Person inputPerson) {

            List<ErrorCode> errorList = new ArrayList<>();
        try {
            StringBuilder NameUniqueQuery = new StringBuilder()
                    .append("select first_name, last_name from person_service where first_name = ? and last_name = ? and id != ? ");
            PreparedStatement ps = connection.prepareStatement(NameUniqueQuery.toString());
            ps.setString(1, inputPerson.getFirstName());
            ps.setString(2, inputPerson.getLastName());
            ps.setLong(3, inputPerson.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                errorList.add(ErrorCode.F_L_NAME_UNIQUE);
            }
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        try {
            StringBuilder createQuery = new StringBuilder().append("select email from person_service where email = ?  and id != ?");
            PreparedStatement ps = connection.prepareStatement(createQuery.toString());
            ps.setString(1, inputPerson.getEmail());
            ps.setLong(2, inputPerson.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                errorList.add(ErrorCode.EMAIL_DUP);
            }
        }catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        long birthDate = 0;
        try {
            java.util.Date date = sdf.parse(inputPerson.getBirthDate());
            birthDate = date.toInstant().toEpochMilli();
        } catch (ParseException e) {
            errorList.add(ErrorCode.DATE_FORMAT_ERROR);
        }
        //        validateNameUnique(inputPerson, connection);
        //        validateUniqueEmail(inputPerson, connection);
        //        long birthDate = checkBirthDate(inputPerson);

        if(errorList.isEmpty() == false) {
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

//    private void validateNameUnique(Person inputPerson, Connection connection) {
//
//        try {
//            StringBuilder NameUniqueQuery = new StringBuilder()
//                    .append("select first_name, last_name from person_service where first_name = ? and last_name = ? ");
//            PreparedStatement ps = connection.prepareStatement(NameUniqueQuery.toString());
//            ps.setString(1, inputPerson.getFirstName());
//            ps.setString(2, inputPerson.getLastName());
//            ResultSet rs = ps.executeQuery();
//            if (rs.next() == true) {
//                throw new AppException(ErrorCode.F_L_NAME_UNIQUE);
//            }
//        } catch (SQLException e) {
//            log("%s", e.getMessage());
//            throw new AppException(ErrorCode.SERVER, e);
//        }
//    }

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}
