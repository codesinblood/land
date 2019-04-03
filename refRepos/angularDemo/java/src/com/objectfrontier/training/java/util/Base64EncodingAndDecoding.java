package com.objectfrontier.training.java.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Scanner;

public class Base64EncodingAndDecoding {

    public static void main(String[] args) throws UnsupportedEncodingException {

        Scanner input = new Scanner(System.in);

        // getting password from the user to perform base64 encoding and decoding
        String password = input.nextLine();
        input.close();

        // base64 encoding operation on given password
        String encodedPassword = passwordBase64Encoding(password);

        // base64 decoding operation on given password
        passwordBase64Decoding(encodedPassword);
    }

    private static void passwordBase64Decoding(String encodedPassword) {

        byte[] passwordBytes = Base64.getDecoder()
                                     .decode(encodedPassword);

        String decodedPassword = new String(passwordBytes);

        System.out.println("Password after Base64 decoding is " + decodedPassword);
    }

    private static String passwordBase64Encoding(String password) {

        String encodedPassword = Base64.getEncoder()
                                       .encodeToString(password.getBytes());

        System.out.println("Password after Base64 encoding is " + encodedPassword);

        return encodedPassword;
    }
}
