package com.objectfrontier.training.java.io;

import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamToStringConversion {

    public static void main(String[] args) throws Exception {

        InputStream i = new FileInputStream("D:\\dev\\training\\vinoth.ari\\java\\sample.txt");
        byte[] b = new byte[i.available()];
        i.read(b);
        String s = new String(b);
        System.out.println(s);
        i.close();

//        InputStream i1 = new FileInputStream(s);


    }
}
