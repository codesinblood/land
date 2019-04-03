// Read a file using java.io.File

package com.objectfrontier.training.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadFileUsingFile {

    public static void main(String[] args) throws IOException {

        File file = new File("D:/dev/training/vinoth.ari/java/file.write.txt");

        Reader fileReader = new FileReader(file);

        BufferedReader buffer = new BufferedReader(fileReader);

//        char[] c = new char[(int) file.length()];
//
//        buffer.read(c);
//
//        for (char d : c) {
//            System.out.print(d);
//        }

        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
        }

        buffer.close();
        fileReader.close();
    }
}
