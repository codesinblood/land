package com.objectfrontier.training.java.base;
public class SomethingIsWrong {

    private class Rectangle {

        int width;
        int height;

        int area() {
        return height * width;
        }
    }

    public static void main(String[] args) {

        SomethingIsWrong outer = new SomethingIsWrong();
        SomethingIsWrong.Rectangle myRect = outer.new Rectangle();
        myRect.width = 40;
        myRect.height = 50;
        System.out.println("myRectangle area is " + myRect.area());
    }
}
