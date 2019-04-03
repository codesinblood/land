package com.objectfrontier.training.web.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.ContentType;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.service.AuthenticationService;

@WebServlet
public class AuthenticationServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(ContentType.APPLICATION_JSON.toString());
        PrintWriter out=response.getWriter();
        Connection con = ConnectionManager.threadConnection.get();
        Person person;
            log("%s", "AuthenticationServlet Started");
            BufferedReader reader = request.getReader();
            Person inputPerson = JsonConverter.toObject(reader, Person.class);
            AuthenticationService authService = new AuthenticationService();
            person = authService.login(con, inputPerson);
            out.write(JsonConverter.toJson(person));
            log("%s", "Successfully Logged in");

            HttpSession session = request.getSession();
            session.setAttribute("person", person);
            Cookie sessionCookie = new Cookie("JSESSION_ID", session.getId());
            response.addCookie(sessionCookie);
            log("%s", "AuthenticationServlet Ended");
    }

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }

}