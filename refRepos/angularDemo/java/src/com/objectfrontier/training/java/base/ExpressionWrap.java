package com.objectfrontier.training.java.base;
public class ExpressionWrap {

    void findType(Object ...num) {

        for(Object b : num) {
            if(b instanceof Integer) {
                int c = (Integer) b;
                System.out.println(c + " = is the type of Integer");
            } else {
                float f = (Float) b;
                System.out.println(f + " = is the type of Float");
            }
        }
    }

    public static void main(String[] args) {

        ExpressionWrap exp = new ExpressionWrap();
        exp.findType( 100 / 24, 100.10f / 10, 'Z' / 2, 10.5f / 0.5f, 12.4f % 5.5f, 100 % 56);
    }
}
