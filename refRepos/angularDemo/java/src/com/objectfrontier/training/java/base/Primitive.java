package com.objectfrontier.training.java.base;
public class Primitive {

    //void execute () {
    public static void main(String[] args) {

        //Object pt = getPrimitiveType();
        //Class cn = pt.getClassName();
        //Console console = system.getConsole();
        //console.print(cn);
        Class cn = int.class;
        String s = cn.getName();
        System.out.println("int is the class of " + s);
        System.out.println("float is the class of " + float.class.getName());
        cn = short.class;
        System.out.println(cn);
        System.out.println("short is the class of " + cn);
        System.out.println("double is the class of " + double.class);
        System.out.println("char is the class of " + char.class);
        System.out.println("byte is the class of " + byte.class);
        System.out.println("boolean is the class of " + boolean.class);
    }
}
