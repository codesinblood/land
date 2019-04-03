package com.objectfrontier.training.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("DemoServlet")
public class DemoServlet extends HttpServlet{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        // Now obtain a PrintWriter to insert HTML into
        PrintWriter out = res.getWriter();
        out.println("Hiiiii This is DemoServlet");
        out.println(Sample.get());



        out.println("<html><head><title>" +
                    "Hello World!</title></head>");
        out.println("<body><h1>Hello World!</h1></body></html>");
    }


}
