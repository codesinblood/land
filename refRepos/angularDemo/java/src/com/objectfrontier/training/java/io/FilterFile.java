package com.objectfrontier.training.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilterFile {

    public static void main(String[] args) {

        Path p = Paths.get("D:\\dev\\training\\vinoth.ari\\java");

        File file = p.toFile();

        String[] f = file.list(new NameFilter());

        for (String string : f) {
            System.out.println(string);
        }
    }
}
