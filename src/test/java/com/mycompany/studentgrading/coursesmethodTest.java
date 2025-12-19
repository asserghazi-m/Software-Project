/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.studentgrading;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.PrintWriter;

public class coursesmethodTest {

    @Test
    void testAddcourse_validCourse() throws Exception {
        // Clean file before test
        File file = new File("courses.txt");
        if (file.exists()) {
            new PrintWriter(file).close();
        }

        coursesmethod cm = new coursesmethod();
        Courses course = new Courses("Math", "C001", 3);

        boolean result = cm.addcourse(course);

        assertTrue(result);
        assertEquals(1, cm.getCourseCount());
    }
}
