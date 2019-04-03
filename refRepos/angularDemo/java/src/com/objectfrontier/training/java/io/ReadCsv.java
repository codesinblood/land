package com.objectfrontier.training.java.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadCsv {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\dev\\training\\vinoth.ari\\java\\sample.csv");
        List<String> l = Files.readAllLines(path);

        for (String string : l) {
            System.out.println(string);
        }
    }
}
