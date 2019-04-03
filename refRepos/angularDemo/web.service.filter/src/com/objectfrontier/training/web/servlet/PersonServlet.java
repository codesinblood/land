package com.objectfrontier.training.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.ContentType;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.service.PersonService;

public class PersonServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doGet Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        PersonService personService = new PersonService();
        String id = request.getParameter("id");
        String readFlag = request.getParameter("flag");
        if (Objects.nonNull(id)) {
            Person readedPerson = personService.readPerson(connection, Integer.parseInt(id), Boolean.parseBoolean(readFlag));
            out.write(JsonConverter.toJson(readedPerson));
        } else {
            List<Person> readedList = personService.readAllPerson(connection);
            out.write(JsonConverter.toJson(readedList));
        }
        log("%s","doGet Method Ended");
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doPut Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        BufferedReader reader = request.getReader();
        Person input = JsonConverter.toObject(reader, Person.class);
        Person person = new PersonService().createPerson(connection, input);
        out.write(JsonConverter.toJson(person));
        log("%s","doPut Method Ended");
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doPost Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        BufferedReader reader = request.getReader();
        Person input = JsonConverter.toObject(reader, Person.class);
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");

        if (Objects.isNull(request.getParameter("isUpdate"))) {
            int affectedRow = new PersonService().delete(connection, input);
            out.write(JsonConverter.toJson(affectedRow));
        } else if(person.getAdmin() == true) {
            Person updatedPerson = new PersonService().adminUpdate(connection, input);
            out.write(JsonConverter.toJson(updatedPerson));
        } else if(Objects.isNull(input.getAdmin())) {
            Person updatedPerson = new PersonService().userUpdate(connection, input);
            out.write(JsonConverter.toJson(updatedPerson));
        } else {
            throw new AppException(ErrorCode.UN_AUTHORIZED);
        }
        log("%s","doPost Method Ended");
    }

    private PrintWriter setResponseType(HttpServletResponse response) throws IOException {

        response.setContentType(ContentType.APPLICATION_JSON.toString());
        PrintWriter out=response.getWriter();
        return out;
    }

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}

