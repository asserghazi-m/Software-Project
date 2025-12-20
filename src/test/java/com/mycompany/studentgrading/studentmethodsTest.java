package com.mycompany.studentgrading;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class studentmethodsTest {

    private static final String TEST_FILE = "student.txt";

    @BeforeEach
    void cleanFile() throws Exception {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            new PrintWriter(file).close(); // clear contents
        }
    }

    @Test
    void testAddStudent_validStudent() {
        studentmethods sm = new studentmethods();
        Studentdata student = new Studentdata("S001", "Ahmed");

        boolean result = sm.addstudents(student);

        assertTrue(result);
        assertEquals(1, sm.getStudentCount());
    }

    @Test
    void testIsIdUnique_whenFileEmpty() {
        studentmethods sm = new studentmethods();

        assertTrue(sm.isIdUnique("S100"));
    }

    @Test
    void testGetAllStudent_whenEmpty() {
        studentmethods sm = new studentmethods();

        ArrayList<Studentdata> result = sm.getallstudent();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetStudentCount_whenEmpty() {
        studentmethods sm = new studentmethods();

        assertEquals(0, sm.getStudentCount());
    }
}
