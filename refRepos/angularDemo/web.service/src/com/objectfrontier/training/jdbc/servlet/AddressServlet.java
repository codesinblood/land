package com.objectfrontier.training.jdbc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.objectfrontier.training.jdbc.connectionManager.ConnectionManager;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.service.AddressService;

public class AddressServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = setResponseType(response);
        Connection connection = null;
        ConnectionManager connectionManager = new ConnectionManager();
        connection = connectionManager.get();
        AddressService addressService = new AddressService();
        String id = request.getParameter("id");
        try {

            if (id != null) {
                Address read = addressService.readAddress(connection, Integer.parseInt(id));
                out.write(JsonConverter.toJson(read));
            } else {
                List<Address> readAll = addressService.readAllAddress(connection);
                out.write(JsonConverter.toJson(readAll));
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
            Address input = JsonConverter.toObject(reader, Address.class);
            Address address = new AddressService().createAddress(connection, input);
            out.write(JsonConverter.toJson(address));
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
        String id = request.getParameter("id");
        try {
            if (id != null) {
                int address = new AddressService().deleteAddress(connection, Long.parseLong(id));
                out.write(JsonConverter.toJson(address));
            } else {
            BufferedReader reader = request.getReader();
            Address input = JsonConverter.toObject(reader, Address.class);
            Address address = new AddressService().updateAddress(connection, input);
            out.write(JsonConverter.toJson(address));
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

