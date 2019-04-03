package com.objectfrontier.training.java.base;
public class CharInterface implements CharSequence {

    @Override
    public char charAt(int index) {
        String s = "vinoth";
        char c = s.charAt(index);
        return c;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return "a";
    }

    @Override
    public int length() {
        return 5;
    }

    @Override
    public String toString() {
        return "vinoth";
    }

    public static void main(String[] args) {

        CharInterface c = new CharInterface();
        System.out.println(c);
        System.out.println(c.charAt(5));
    }
}
