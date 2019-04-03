package com.objectfrontier.training.java.base;

abstract class Shape {

    abstract void CalculateArea(double area);
    abstract void CalculatePerimeter(double perimeter);
}

class Square extends Shape {

    @Override
    void CalculateArea(double area) {
        System.out.println("Area of the Sqaure = " + area * area);
    }

    @Override
    void CalculatePerimeter(double perimeter) {
        System.out.println("Perimeter of the Sqaure = " + 4 * perimeter);
    }
}

class Circle extends Shape {

    @Override
    void CalculateArea(double area) {
        System.out.println("Area of the Circle = " + (3.14 * (area * area)));
    }

    @Override
    void CalculatePerimeter(double perimeter) {
        System.out.println("Perimeter of the Circle = " + ((2 * 3.14) * perimeter));
    }
}

public class Abstract {

    public static void main(String[] args) {

        Circle calcCircle = new Circle();
        Square calcSquare = new Square();
        calcCircle.CalculateArea(4);
        calcSquare.CalculateArea(4);
        calcCircle.CalculatePerimeter(4);
        calcSquare.CalculatePerimeter(4);
    }
}
