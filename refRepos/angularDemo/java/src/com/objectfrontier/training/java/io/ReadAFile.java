// Read a any text file using BufferedReader and print the content of the file

package com.objectfrontier.training.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadAFile {

    public static void main(String[] args) throws IOException {

        Reader file = new FileReader("D:/dev/training/vinoth.ari/java/xanadu.txt");

        BufferedReader buffer = new BufferedReader(file);

        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
        }

        file.close();
        buffer.close();
    }
}
