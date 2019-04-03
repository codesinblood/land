package com.objectfrontier.training.java.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.objectfrontier.training.java.jdbc.connection.ConnectionManager;
import com.objectfrontier.training.java.jdbc.exception.CustomException;

public class PersonService {

    public static void main(String[] args) throws SQLException {

        ConnectionManager conManager = new ConnectionManager();
        Connection connection = null;
        PersonService personService = new PersonService();
        try {

            Scanner scan = new Scanner(System.in);
            connection = conManager.getConnection();
            connection.setAutoCommit(false);
            Person inputPerson = Input.getPersonInput();
            Person createdPerson = personService.createPerson(connection, inputPerson);
            log("%s", createdPerson);

            System.out.println("Whether Address want to be read (true/false)");
            boolean flag = scan.nextBoolean();
            Person readPerson = personService.readPerson(connection, Input.getId(), flag);
            log("%s", readPerson);
            List<Person> list = personService.readAllPerson(connection);
            log("%s", list);

            Person updatedPerson = personService.update(connection,
            Input.updateInput());
            log("%s", updatedPerson);

            personService.delete(connection, Input.deleteId());

            connection.commit();
            scan.close();
        } catch (Exception e) {
            connection.rollback();
            log("%s", e.getMessage());
        } finally {
            conManager.release(connection);
        }
    }

    public int delete(Connection connection, Person person) throws SQLException, CustomException {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("DELETE       ")
                   .append("FROM person  ")
                   .append("WHERE id = ? ");

        PreparedStatement ps = connection.prepareStatement(createQuery.toString());
        ps.setLong(1, person.getId());
        int affectedRows = ps.executeUpdate();
        AddressService addService = new AddressService();
        addService.deleteAddress(connection, person.getAddress().getId());
        if (affectedRows == 0) {
            throw new CustomException("Id not exist");
        }
        return affectedRows;
    }

    public Person update(Connection connection, Person inputPerson) throws SQLException, CustomException {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("UPDATE person                           ")
                   .append("SET name = ?, email = ?, birth_date = ? ")
                   .append("WHERE id = ?                            ");

        PreparedStatement ps = connection.prepareStatement(createQuery.toString());
        ps.setString(1, inputPerson.getName());
        ps.setString(2, inputPerson.getEmail());
        ps.setDate(3, inputPerson.getBirthDate());
        ps.setLong(4, inputPerson.getId());
        int affectedRows = ps.executeUpdate();
        AddressService addService = new AddressService();
        addService.updateAddress(connection, inputPerson.getAddress());

        if (affectedRows == 0) {
            throw new CustomException("Id not exist");
        }
        return inputPerson;
    }

    public List<Person> readAllPerson(Connection connection) throws SQLException, CustomException {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT id, `name`, email, address_id, birth_date, created_date ")
                   .append("FROM person                                                    ");

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(createQuery.toString());

        List<Person> list = new ArrayList<>();
        while (rs.next()) {

            Person person = constructPerson(rs);
            constructAddress(connection, rs, person);
            list.add(person);
        }

        if (rs.wasNull() == true) {
            throw new CustomException("List is not exist");
        }
        return list;
    }

    private void constructAddress(Connection connection, ResultSet rs, Person person) throws SQLException, CustomException {

        Address address = new Address();
        address.setId(rs.getLong("address_id"));
        person.setAddress(address);
        AddressService addService = new AddressService();
        addService.readAddress(connection, person.getAddress().getId());
    }

    private Person constructPerson(ResultSet rs) throws SQLException {

        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setEmail(rs.getString("email"));
        person.setBirthDate(rs.getDate("birth_date"));
        person.setCreatedDate(rs.getTimestamp("created_date"));
        return person;
    }

    public Person readPerson(Connection connection, long id, boolean flag) throws SQLException, CustomException {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT id,  `name`, email, address_id, birth_date, created_date ")
                   .append("FROM person                                                     ")
                   .append("WHERE id = ?                                                    ");
        PreparedStatement ps = connection.prepareStatement(createQuery.toString());
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
            throw new CustomException("Id not exist");
        }
    }

    public Person createPerson(Connection connection, Person inputPerson) throws SQLException {

        AddressService addService = new AddressService();
        addService.createAddress(connection, inputPerson.getAddress());
        StringBuilder createQuery = new StringBuilder();
        createQuery.append("INSERT INTO  person (`name`, email, address_id, birth_date, created_date) ")
                   .append("VALUES (?, ?, ?, ?, ?)                                                    ");

        PreparedStatement ps = connection.prepareStatement(createQuery.toString(), Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, inputPerson.getName());
        ps.setString(2, inputPerson.getEmail());
        ps.setLong(3, inputPerson.getAddress().getId());
        ps.setDate(4, inputPerson.getBirthDate());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        ps.setTimestamp(5, date);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        inputPerson.setCreatedDate(date);
        inputPerson.setId(rs.getLong(1));
        return inputPerson;
    }

    private static void log(String format, Object args) {

        System.out.format(format + "\n", args);
    }
}
