package com.objectfrontier.training.web.service;

import static com.objectfrontier.training.web.service.Constants.BIRTH_DATE;
import static com.objectfrontier.training.web.service.Constants.CREATED_DATE;
import static com.objectfrontier.training.web.service.Constants.EMAIL;
import static com.objectfrontier.training.web.service.Constants.FIRST_NAME;
import static com.objectfrontier.training.web.service.Constants.ID;
import static com.objectfrontier.training.web.service.Constants.LAST_NAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Person;

public class AuthenticationService {

    public Person login(Connection connection, Person inputPerson) {

        log("%s", "Login Service Started");
        Person person;
        try {
            PreparedStatement ps = connection.prepareStatement(Statements.VALIDATE_USER);
            ps.setString(1, inputPerson.getEmail());
            ps.setString(2, inputPerson.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                person = constructPerson(rs);
            } else {
                throw new AppException(ErrorCode.INVALID_USER);
            }
        } catch (SQLException e) {
            throw new AppException(ErrorCode.SERVER, e);
        }
        log("%s", "Login Service Ended");
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
            person.setAdmin(rs.getBoolean(Constants.IS_ADMIN));
            person.setPassword(rs.getString(Constants.PASSWORD));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
        }
        return person;
    }

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}
