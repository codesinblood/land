package com.objectfrontier.training.jdbc.pojo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    private static final String COMMA_DELIMITER = ",";

    public static Object[][] createParser() throws IOException, URISyntaxException {

        Path fileName = Paths.get(ClassLoader.getSystemResource("person.csv").toURI());
        Stream<String> st = Files.lines(fileName);
        List<String[]> listStream = st.map(a -> a.split(COMMA_DELIMITER)).collect(Collectors.toList());
        List<Object> personList = new ArrayList<>();
        List<Object> expectedPersonList = new ArrayList<>();
        st.close();

        for (String[] strings : listStream) {
            Person actualPerson = new Person(strings[0], strings[1], strings[2], strings[3], new Address(strings[4], strings[5], Integer.parseInt(strings[6])));
            Person expectedPerson = actualPerson;
            personList.add(actualPerson);
            expectedPersonList.add(expectedPerson);
        }

        Object[][] twoDArray = new Object[personList.size()][2];

        for (int i = 0; i < personList.size(); i++) {
            twoDArray[i][0] = personList.get(i);
            twoDArray[i][1] = expectedPersonList.get(i);
        }
        return twoDArray;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        createParser();

    }
}
