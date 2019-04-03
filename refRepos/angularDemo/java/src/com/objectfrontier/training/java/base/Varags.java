package com.objectfrontier.training.java.base;
public class Varags {

   void var(String str, int ...a) {

        System.out.println("String: " + str);
        System.out.println("Number of arguments is: "+ a.length);
        for (int i: a){
            System.out.println(i);
        }
    }

/*      void var1(int ...a, String str) {

        system.out.println("String: " + str);
        System.out.println("Number of arguments is: "+ a.length);
        for (int i: a){
            System.out.println(i);
        }
    }
*/

    public static void main(String[] args) {

        Varags v1 = new Varags();
        v1.var("Vinoth", 100, 200);
        v1.var("OFS", 1, 2, 3, 4, 5);
        v1.var("Employee");

        /*      Varags v1 = new Varags();
        v1.var(100, 200, "Vinoth");
        v1.var(1, 2, 3, 4, 5, "OFS");
        v1.var("Employee");
*/
    }
}
