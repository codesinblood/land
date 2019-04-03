package com.objectfrontier.training.java.base;
class Animal {

    int eyes;

    void move() {
        System.out.println("Animal is Moving");
    }
}

class Cat extends Animal {

    int legs;
    String color;

    void walk() {
        System.out.println("Cat is walked");
    }
}

class Snake extends Animal {

   String typeOfSnake;

    void move() {
        System.out.println("Snake is moved");
    }
}

class Dog extends Animal {

    int legs;
    String breed;

    void run() {
        System.out.println("Dog is running");
    }

    void bark(String owner) {
        System.out.println("Dog is sounded happy while seeing his owner");
    }

    void bark(String owner, String stranger) {
        System.out.println("Dog is sounded angrily while seeing strangers with owner");
    }
}

public class Inheritance {

    public static void main(String[] args) {

        Dog puppy = new Dog();
        puppy.bark("The owner","The stanger"); // OverLoading
        Animal snake1 = new Snake(); // Overriding
        snake1.move();
    }
}
