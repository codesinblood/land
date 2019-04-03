package com.objectfrontier.training.java.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//class Program
public class JavaRun {

    //void execute(String fullyQualifiedClassName)
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        invokeMethod(args);
    }

    private static void invokeMethod(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        //String className = Program.getClassName();
        String className = args[0];


        //Class clazz = Class.getClass(className);
        Class inputClass = Class.forName(className);

        //Method method = Class.getMainMethod(clazz);
        Method method = inputClass.getMethod("main", String[].class);

        //output = method.invoke();
        //Console console = System.getConsole();
        //console.print(output);
        String[] args1 = new String[args.length - 1];
        for (int i = 0; i < args1.length; i++) {
                args1[i] = args[i+1];
        }

        method.invoke(null, (Object)args1);
    }
}
