package com.objectfrontier.training.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;

    public class LogoutServlet extends HttpServlet {

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

                System.out.println("Logout Started");
                PrintWriter out=response.getWriter();
                HttpSession session=request.getSession(false);
                if(Objects.nonNull(session)) {
                    session.invalidate();
                    out.write("You are successfully logged out!");
                } else {
                    System.out.println("Not Authenticated");
                    throw new AppException(ErrorCode.UN_AUTHENTICATED);
                }


                out.close();
        }
    }
