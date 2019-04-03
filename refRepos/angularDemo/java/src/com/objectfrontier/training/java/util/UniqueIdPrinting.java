package com.objectfrontier.training.java.util;

import java.util.UUID;

public class UniqueIdPrinting {

    public static void main(String[] args) {

        // generating a random UUID on every run
        generateRandomUUID();
    }

    private static void generateRandomUUID() {

        UUID randomUniversalUniqueID = UUID.randomUUID();

        System.out.println("UUID = " + randomUniversalUniqueID);
    }
}
