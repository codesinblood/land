package com.objectfrontier.training.web.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.servlet.HttpMethod;
import com.objectfrontier.training.web.servlet.RequestHelper;

public class AuthenticationTest {

    @Test
    public void testAuth() {

        RequestHelper helper = new RequestHelper();
        try {
            RequestHelper.setBaseUrl("http://localhost:8080/ws/");
            String email = "ja1";
            String password = "demo";
            Person person = helper.setSecured(false)
                    .setMethod(HttpMethod.POST)
                    .requestObject("login?email=ja1&&password=demo", Person.class);
            System.out.println(person);
        } catch (Exception e) {
            if (e instanceof AppException) {
                e.printStackTrace();
                System.out.println(((AppException) e).getErrorCodes());
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }
}
