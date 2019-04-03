package com.objectfrontier.training.java.base;
import java.util.Arrays;

public class StringSorting {

    public static void main(String[] args) {

        String[] place = { "Madurai", "Thanjavur", "TRICHY", "Karur", "Erode", "trichy", "Salem"};
        Arrays.sort(place);
        System.out.println("Sorted Order : " + Arrays.toString(place));
        Arrays.sort(place, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case Insensitive order: " + Arrays.toString(place));
        for(int i = 0;i <= place.length;i = i + 2) {
            if (i % 2 == 0 && i != 0 ) {
                System.out.println(" Upper Case to the even indexed string " + place[i].toUpperCase());
            }
        }
    }
}
