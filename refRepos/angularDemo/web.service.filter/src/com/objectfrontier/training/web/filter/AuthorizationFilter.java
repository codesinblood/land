package com.objectfrontier.training.web.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.servlet.HttpMethod;

public class AuthorizationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    HttpSession session;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        System.out.println("Authorization filter started");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        if(person.getAdmin() == true) {
            chain.doFilter(request, response);
        } else {
            boolean hasRights =  userOperation(request, person);
            if(hasRights == false) {
                throw new AppException(ErrorCode.UN_AUTHORIZED);
            } else {
                chain.doFilter(request, response);
            }
        }
        log("%s", "AuthorizationFilter  Ended");
    }

    private boolean userOperation(HttpServletRequest request, Person person) {

        String reqMethod = request.getMethod();
        String update = request.getParameter("isUpdate");
        if(HttpMethod.PUT.toString().equals(reqMethod)) { // Restrict Create service
            return false;
        } else if (HttpMethod.POST.toString().equals(reqMethod) && Objects.isNull(update)) { // Restrict the Delete service
            return false;
        }  else if (HttpMethod.GET.toString().equals(reqMethod)) { // Allow to read
            return true;
        } else if(HttpMethod.POST.toString().equals(reqMethod) && Objects.nonNull(update)){ // Allow to update
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void destroy() {}

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}
/*       } else if (method == "GET" && Objects.isNull(id)) { // Restrict the ReadAll service
            return false;
        }  else if (method == "GET" && Long.parseLong( id) == person.getId()) { // Restrict Person to read other details
            return true;*/