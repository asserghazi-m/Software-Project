/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.studentgrading;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.PrintWriter;

public class studentmethodsTest {

    @Test
    void testAddStudent_validStudent() throws Exception {

        // Ensure clean environment (VERY IMPORTANT FOR CI)
        File file = new File("students.txt");
        if (file.exists()) {
            new PrintWriter(file).close();
        }

        studentmethods sm = new studentmethods();
        Studentdata student = new Studentdata( "S001", "Ahmed");

        boolean result = sm.addstudents(student);

        assertTrue(result);
        assertEquals(1, sm.getStudentCount());
    }
}


