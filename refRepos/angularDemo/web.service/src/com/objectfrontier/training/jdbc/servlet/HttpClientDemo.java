package com.objectfrontier.training.jdbc.servlet;

public class HttpClientDemo {

    private void run(String[] args) throws Exception {

        RequestHelper init = new RequestHelper();
        String url = "http:localhost:8080";
        log(init.requestString(url));
    }

    public static void main(String[] args) {
        try {
            new HttpClientDemo().run(args);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void log(Object o) {
        if (o instanceof Throwable) {
            ((Throwable)o).printStackTrace(System.err);
        } else {
            System.out.println(o);
        }
    }
}
