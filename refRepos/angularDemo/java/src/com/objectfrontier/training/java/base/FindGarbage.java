package com.objectfrontier.training.java.base;
public class FindGarbage {

    public static void main(String[] args) {

        int i = 0;
        String[] students = new String[10];
        String studentName = "Peter Parker";
        students[0] = studentName;
        studentName = null;
        for(String temp : students) {
            System.out.println("students[" + i + "] = " + temp);
            i++;
        }
        System.out.println("studentName = " + studentName);
    }
}

/*
Output :

    students[0] = Peter Parker //  a[0] reference only exist after the execution of this program.
    students[1] = null
    students[2] = null
    students[3] = null
    students[4] = null
    students[5] = null
    students[6] = null
    students[7] = null
    students[8] = null
    students[9] = null
    studentName = null
*/
