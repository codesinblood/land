// 6. Read a file using java.nio.Files using Paths

package com.objectfrontier.training.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileUsingPaths {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:/dev/training/vinoth.ari/java/xanadu.txt");

        BufferedReader buffer = Files.newBufferedReader(path);

        String line;

        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
        }

        buffer.close();
    }
}
