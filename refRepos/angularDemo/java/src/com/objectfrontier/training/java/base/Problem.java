package com.objectfrontier.training.java.base;
public class Problem {

    static String s;

    static class Inner {

        void testMethod() {
            s = "Set from Inner";
        }
    }
}
//changed instance member to static.
//Because static class members can't access the non static members.
//Its only for compilation.
//There is no Main(). so executing is not applicable.
