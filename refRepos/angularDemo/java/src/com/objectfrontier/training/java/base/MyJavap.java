package com.objectfrontier.training.java.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyJavap {

    private void run(String[] args) throws Exception {
        // get classname
        String className = args[0];

        // load class
        Class clazz = loadClass(className);

        // print class details
        printClassDetails(clazz);

        Field [] fields = clazz.getDeclaredFields();
        printFieldDetails(fields);

        Method[] methods = clazz.getDeclaredMethods();
        printMethodDetails(methods);

        Constructor[] constructors = clazz.getConstructors();
        printConstructorDetails(constructors);
    }


    private Class loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load class : " + className);
        }
    }

    private void printClassDetails(Class clazz) {
        // print compilation source
        // Compiled from "String.java"
        log("Compiled from \''%s.java'' ", clazz.getSimpleName());

        // print class header
        printClassHeader(clazz);

        // print fields
        // print constructors
        // print methods
        // print inner classes
        // print class footer
    }


    private void printClassHeader(Class clazz) {
        // public final class java.lang.String implements java.io.Serializable, java.lang.Comparable<java.lang.String>, java.lang.Ch
        int modifier = clazz.getModifiers();
        String modifierPart = parseModifier(modifier);
        String  qualifiedClassName = clazz.getName();

//          String  qualifiedClassName = clazz.toString();
        String inheritanceDetails = getInheritanceDetails(clazz);
        log("%s class %s %s", modifierPart, qualifiedClassName , inheritanceDetails );
    }

    private String parseModifier(int modifier) {

        StringBuilder sb = new StringBuilder();

        if (Modifier.isPrivate(modifier)) {
            sb.append("private ");
        }
        else if (Modifier.isPublic(modifier)) {
            sb.append("public ");
        }
        else if (Modifier.isProtected(modifier)) {
            sb.append("protected ");
        }

        if (Modifier.isFinal(modifier))        {  sb.append("final ");        }
        if (Modifier.isStatic(modifier))       {  sb.append("static ");       }
        if (Modifier.isAbstract(modifier))     {  sb.append("abstract ");     }
        if (Modifier.isSynchronized(modifier)) {  sb.append("synchronized "); }
        if (Modifier.isTransient(modifier))    {  sb.append("transient ");    }

        return sb.toString();
    }

    private String getInheritanceDetails(Class clazz) {

        StringBuilder sb = new StringBuilder();

        // if available, add implemented interfaces
        Class[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) { sb.append("implements "); }
        for (Class intf : interfaces) {
            sb.append(intf.getName()).append(", ");
         }
        sb.replace(sb.length() - 2, sb.length(), "");

        // if available, add extended Class
        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            if (interfaces.length > 0) { sb.append(" "); }
            sb.append("extends ").append(superClass.getName());
        }

        return sb.toString();
    }


    private void printFieldDetails(Field[] fields) {

        for (Field field : fields) {
            int i = field.getModifiers();
            String s1 = parseModifier(i);
            String s2 = field.getGenericType().getTypeName();
            String s3 = field.getName();

            log("%s %s %s",s1,s2,s3);
        }

    }

    private void printMethodDetails(Method[] methods) {

        for (Method method : methods) {
            int modifier = method.getModifiers();
            String s1 = parseModifier(modifier);
            String s2 = method.getReturnType().getTypeName();
            String s3 = method.getName();
            String s4 = getParametrs(method);

            log("%s%s %s(%s)",s1,s2,s3,s4);
        }

    }

    private void printConstructorDetails(Constructor[] constructors) {

        StringBuilder sb = new StringBuilder();
        for (Constructor constructor : constructors) {


            int modifier = constructor.getModifiers();
            String s1 = parseModifier(modifier);
            String s2 = constructor.getName();
            String s3 = getParametrs(constructor);

            log("%s%s(%s)",s1,s2,(s3));
        }
    }

    private <T> String getParametrs(T input) {

        StringBuilder sb = new StringBuilder();
        Class[] parameters = ((Executable) input).getParameterTypes();

        if (parameters.length > 0){
            for(Class parameter : parameters) {
                    sb.append(parameter.getSimpleName()).append(", " );
            }
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        return sb.toString();
    }

   /* private String getParametrs(Constructor constructor) {

        StringBuilder sb = new StringBuilder();
        Class[] parameters = constructor.getParameterTypes();

        if (parameters.length > 0){
            for(Class parameter : parameters) {
                    sb.append(parameter.getSimpleName()).append(", " );
            }
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        return sb.toString();
    }
*/

    public static void main(String[] args) {
        try {
            MyJavap obj = new MyJavap();
            obj.run(args);
        } catch (Throwable t) {
            log(t);
        }
    }

    private static void log(Throwable t) {
        t.printStackTrace(System.err);
    }

    private static void log(String format, String... vals) {
        System.out.format(format, vals);
        System.out.println();
    }
}
