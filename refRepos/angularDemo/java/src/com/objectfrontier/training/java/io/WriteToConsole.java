// Write some String content to console using appropriate Writer.

package com.objectfrontier.training.java.io;

import java.io.PrintWriter;

public class WriteToConsole {

    public static void main(String[] args) {

        PrintWriter console = new PrintWriter(System.out);

        console.println("karthick");
        console.close();
    }
}
