package com.objectfrontier.training.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.chrono.IsoChronology;

import com.objectfrontier.training.java.col.Person;

public class ObjectStreams {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File textFile = new File("D:\\temp\\sample.txt");
        OutputStream o = new FileOutputStream(textFile);
        ObjectOutputStream oos = new ObjectOutputStream(o);

        oos.writeObject(new Person(
                            "Bob",
                            IsoChronology.INSTANCE.date(2000, 9, 12),
                            Person.Sex.MALE, "bob@example.com"));
        oos.flush();
        oos.close();
        o.flush();
        o.close();

        InputStream i = new FileInputStream(textFile);
        ObjectInputStream ois = new ObjectInputStream(i);

        Person personObject = (Person) ois.readObject();

        System.out.println(personObject);

        ois.close();
        i.close();
    }
}
