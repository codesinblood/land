package com.objectfrontier.training.java.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class CsvParser {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        String pathName = input.nextLine();
        input.close();

        readCsvFiles(pathName);
    }

    private static void readCsvFiles(String pathName) throws FileNotFoundException, IOException {

        StreamTokenizer tokenStreams = new StreamTokenizer(new FileReader(pathName));

        tokenStreams.eolIsSignificant(true);

        int token = tokenStreams.nextToken();

        while (token != StreamTokenizer.TT_EOF) {

                 if (token == StreamTokenizer.TT_WORD) { System.out.print(tokenStreams.sval); }
            else if (token == StreamTokenizer.TT_NUMBER) { System.out.print(tokenStreams.nval);}
            else if (token == StreamTokenizer.TT_EOL) { System.out.println(); }
            else { System.out.print((char)token); }

            token = tokenStreams.nextToken();
        }
    }
}
