// 11. Write a program to get the number of files and number of directories in a given directory

package com.objectfrontier.training.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountFilesAndDirectories {

    private static int directoryCount = 0;
    private static int fileCount = 0;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // getting the pathname from the user
        String pathName = input.nextLine();

        Path path = Paths.get(pathName);

        // converting path to a file
        File newFile = path.toFile();

        // getting all the files and directories in a file
        getFilesAndDirectories(newFile);

        printFilesAndDirectoriesCount();
    }

    private static void printFilesAndDirectoriesCount() {

        System.out.println("Total number of directories " + directoryCount);
        System.out.println("Total number of Files " + fileCount);
    }

    private static void getFilesAndDirectories(File newFile) {

        File[] filesAndDirectories = newFile.listFiles();

        for (File file : filesAndDirectories) {

            if (file.isDirectory()) {
                directoryCount ++;
                getFilesAndDirectories(file);
            }

            if (file.isFile()) {
                fileCount ++;
            }
        }
    }
}
