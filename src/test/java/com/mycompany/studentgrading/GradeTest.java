package com.mycompany.studentgrading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {

    private Grade grade;

    @BeforeEach
    public void setUp() {
        grade = new Grade("S1", "C1", "A");
    }

    @Test
    public void testGetStudentId() {
        assertEquals("S1", grade.getStudentId());
    }

    @Test
    public void testGetCourseId() {
        assertEquals("C1", grade.getCourseId());
    }

    @Test
    public void testGetGradeValue() {
        assertEquals("A", grade.getGradeValue());
    }

    @Test
    public void testToString() {
        String result = grade.toString();
        assertNotNull(result);
        assertTrue(result.contains("S1"));
        assertTrue(result.contains("C1"));
        assertTrue(result.contains("A"));
    }
}
