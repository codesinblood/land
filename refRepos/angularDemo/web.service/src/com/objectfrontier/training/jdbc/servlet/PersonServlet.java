package com.objectfrontier.training.jdbc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.pojo.Person;
import com.objectfrontier.training.jdbc.service.PersonService;

public class PersonServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = setResponseType(response);
        Connection connection = null;
        ConnectionManager connectionManager = new ConnectionManager();
        connection = connectionManager.get();
        PersonService personService = new PersonService();
        String id = request.getParameter("id");
        String readFlag = request.getParameter("flag");
        try {

            if (id != null) {
                Person readedPerson = personService.readPerson(connection, Integer.parseInt(id), Boolean.parseBoolean(readFlag));
                out.write(JsonConverter.toJson(readedPerson));
            } else {
                List<Person> readedList = personService.readAllPerson(connection);
                out.write(JsonConverter.toJson(readedList));
            }
            response.setStatus(HttpStatus.SC_OK);
            connectionManager.release(connection, true);
        } catch (Exception e) {
            out.write(JsonConverter.toJson(((AppException)e).getErrorCodes()));
                connectionManager.release(connection, false);
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = setResponseType(response);
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        connection = connectionManager.get();
        try {
//            response.setStatus(305);
            BufferedReader reader = request.getReader();
            Person input = JsonConverter.toObject(reader, Person.class);
            Person person = new PersonService().createPerson(connection, input);
            out.write(JsonConverter.toJson(person));
            response.setStatus(HttpStatus.SC_OK);
            connectionManager.release(connection, true);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            if (e instanceof AppException) {
                out.write(JsonConverter.toJson(((AppException) e).getErrorCodes()));
            }
            connectionManager.release(connection, false);
            }
//            out.write(e.getMessage());
            }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = setResponseType(response);
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        connection = connectionManager.get();
        try {
            BufferedReader reader = request.getReader();
            Person input = JsonConverter.toObject(reader, Person.class);

            if (Objects.isNull(request.getParameter("isUpdate"))) {
                int affectedRow = new PersonService().delete(connection, input);
                out.write(JsonConverter.toJson(affectedRow));
            } else {
            Person updatedPerson = new PersonService().update(connection, input);
            out.write(JsonConverter.toJson(updatedPerson));
            }
            response.setStatus(HttpStatus.SC_OK);
            connectionManager.release(connection, true);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//            if( e instanceof AppException) {
            out.write(JsonConverter.toJson(((AppException)e).getErrorCodes()));
//            } else {
//                throw new AppException(ErrorCode.SERVER, e);
//            }
            connectionManager.release(connection, false);
        }
    }

    private PrintWriter setResponseType(HttpServletResponse response) throws IOException {

        response.setContentType(ContentType.APPLICATION_JSON.toString());
        PrintWriter out=response.getWriter();
        return out;
    }

}

