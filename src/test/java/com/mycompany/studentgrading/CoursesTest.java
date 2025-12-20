package com.mycompany.studentgrading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesTest {

    private Courses course;

    @BeforeEach
    public void setUp() {
        // IMPORTANT: constructor order is (courseName, courseID, creditHours)
        course = new Courses("Data Structures", "C1", 3);
    }

    @Test
    public void testGetCourseName() {
        assertEquals("Data Structures", course.getCourseName());
    }

    @Test
    public void testGetCourseID() {
        assertEquals("C1", course.getCourseID());
    }

    @Test
    public void testGetCreditHours() {
        assertEquals(3, course.getCreditHours());
    }

    @Test
    public void testToString() {
        String result = course.toString();
        assertNotNull(result);
        assertTrue(result.contains("Data Structures"));
        assertTrue(result.contains("C1"));
        assertTrue(result.contains("3"));
    }
}
