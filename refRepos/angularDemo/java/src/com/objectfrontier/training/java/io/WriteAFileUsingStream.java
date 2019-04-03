// Write some String content to a file using appropriate OutputStream

package com.objectfrontier.training.java.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteAFileUsingStream {

    public static void main(String[] args) throws IOException {

        OutputStream file = new FileOutputStream("D:/dev/training/vinoth.ari/java/file.write.txt");

        BufferedOutputStream buffer = new BufferedOutputStream(file);

        DataOutputStream data = new DataOutputStream(file);

        String string1 = "This is Sample Output Stream Using Buffer";
        data.writeBytes(string1);

        String string2 = "\nThis is Sample Output Stream Using Bytes";
        byte[] bytes =  string2.getBytes();

        buffer.write(bytes);

        buffer.close();
        data.close();
        file.close();

        System.out.println("successfully Writed");
    }
}
