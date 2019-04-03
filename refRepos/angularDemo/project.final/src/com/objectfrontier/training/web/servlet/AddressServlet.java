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

import org.apache.http.entity.ContentType;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.pojo.Address;
import com.objectfrontier.training.web.service.AddressService;
import com.objectfrontier.training.web.service.ObjectFactory;

public class AddressServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doGet Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        AddressService addressService = ObjectFactory.getAddressService();
        String id = request.getParameter("id");
        if (Objects.nonNull(id)) {
            Address read = addressService.readAddress(connection, Integer.parseInt(id));
            out.write(JsonConverter.toJson(read));
        } else {
            List<Address> readAll = addressService.readAllAddress(connection);
            out.write(JsonConverter.toJson(readAll));
        }
        log("%s","doGet Method Ended");
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doPut Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        BufferedReader reader = request.getReader();
        Address input = JsonConverter.toObject(reader, Address.class);
        AddressService addressService = ObjectFactory.getAddressService();
        Address address = addressService.createAddress(connection, input);
        out.write(JsonConverter.toJson(address));
        log("%s","doPut Method Ended");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log("%s","doPost Method Started");
        PrintWriter out = setResponseType(response);
        Connection connection = ConnectionManager.threadConnection.get();
        BufferedReader reader = request.getReader();
        Address input = JsonConverter.toObject(reader, Address.class);
        AddressService addressService = ObjectFactory.getAddressService();
        if (Objects.isNull(request.getParameter("isUpdate"))) {
            int affectedRow = addressService.deleteAddress(connection, input.getId());
            out.write(JsonConverter.toJson(affectedRow));
        } else {
            Address updatedPerson = addressService.updateAddress(connection, input);
            out.write(JsonConverter.toJson(updatedPerson));
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

