package com.objectfrontier.training.web.test;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.objectfrontier.training.web.pojo.Person;
import com.objectfrontier.training.web.servlet.HttpMethod;
import com.objectfrontier.training.web.servlet.RequestHelper;

public class BaseServletTest {

    Person person = new Person("c", "demo"); //initialize your person with credentials
    protected RequestHelper login() { return login(person); }
    protected RequestHelper login(Person person) {

        try {

            RequestHelper helper = RequestHelper.create();
            //Prepare your login call here, if you have implemented it in a different HTTP Method
            HttpResponse response = helper.setMethod(HttpMethod.POST).setInput(person).requestRaw("/login");
            log(RequestHelper.asString(response));
            AssertJUnit.assertEquals(RequestHelper.getStatus(response), 200);
            helper.setSecureDetails(response);
            return helper;
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVER);
        }
    }

    private static Server server;
    private static int port = 8080;
    private static String contextPath = "/ws";

    @BeforeSuite
    protected void initServer() throws Exception {

        server = new Server(port);

        URL webXmlResource = server.getClass().getClassLoader().getResource("web.xml");
        System.out.println(webXmlResource);
        URI webResourceBase = webXmlResource.toURI().resolve("..").normalize();

        log("Using BaseResource: " + webResourceBase);
        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webResourceBase));

        context.setContextPath(contextPath);
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();

        String baseUrl = String.format("http://localhost:%s%s", port, contextPath);
        RequestHelper.setBaseUrl(baseUrl);
    }

    @AfterSuite
    protected void stopServer() throws Exception {
        server.stop();
    }

    void log(String message) {
        System.out.println(message);
    }
 }
