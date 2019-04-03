package com.objectfrontier.training.java.io;

import java.io.File;
import java.io.FilenameFilter;

public class NameFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {

        boolean result;

        if (name.endsWith(".txt")) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
