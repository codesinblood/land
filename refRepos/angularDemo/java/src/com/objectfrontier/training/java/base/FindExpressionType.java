package com.objectfrontier.training.java.base;
public class FindExpressionType {

    // Object expression = getExpression();
    private void displayType(Object... expressions) {

        // class className = expr.getClassName();
        // String type = className.getType();
        // console.print(type);
        for (Object expression : expressions) {
            // Class cn = expression.getClass();
            // String s = cn.getSimpleName();
            // System.out.println(s);
            System.out.println(expression.getClass().getSimpleName());
        }
    }

    // void execute(){
    public static void main(String[] args) {

        FindExpressionType exp = new FindExpressionType();
        exp.displayType(100 / 24,
                        100.10 / 10,
                        'Z' / 2,
                        10.5 / 0.5,
                        12.4 % 5.5,
                        100 % 56);
    }
}
