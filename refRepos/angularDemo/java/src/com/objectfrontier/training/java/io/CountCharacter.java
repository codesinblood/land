//Write an example that counts the number of times a particular character, such as 'e', appears in a file.

package com.objectfrontier.training.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CountCharacter {

    public static void main(String[] args) throws IOException {

        int count = 0;
        Reader fileReader = new FileReader("D:/dev/training/vinoth.ari/java/xanadu.txt");

//        Path path = Paths.get("xanadu.txt");
//        Path p = path.toAbsolutePath();
//        OutputStream i = new FileOutputStream(p.toString());

        BufferedReader bufferReader = new BufferedReader(fileReader);

        char[] characters = new char[1000];

        char[] inputChar = args[0].toCharArray();

        bufferReader.read(characters);

        for (char character : characters) {
            if (character == inputChar[0]) {
                count++;
            }
        }
        System.out.println("'e' is Appeared  " + count + " times in a given file");

        fileReader.close();
        bufferReader.close();
    }
}
