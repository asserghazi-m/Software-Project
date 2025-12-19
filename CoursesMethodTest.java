package com.mycompany.studentproject;

import com.mycompany.studentgrading.Courses;

public class CoursesMethodTest {

    public static void main(String[] args) {

        coursesmethod cm = new coursesmethod();

        Courses c = new Courses("Math", "CS101", 3);

        if (!cm.addcourse(c)) {
            throw new AssertionError("Test failed");
        }

        System.out.println("Test passed");
    }
}
