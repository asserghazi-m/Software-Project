package com.mycompany.studentgrading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class GradeManagerTest {

    GradeManager gm;

    @BeforeEach
    public void setUp() {
        gm = new GradeManager();

        // Clean grades file before each test
        File file = new File("data/grades.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAssignGrade_NoStudentNoCourse() {
        gm.assignGrade("S1", "C1", "A");

        // No student & course → grade should NOT be assigned
        assertEquals(0, gm.getTotalGradeCount());
    }

    @Test
    public void testCalculateGPA_NoGrades() {
        double gpa = gm.calculateGPA("S1");
        assertEquals(0.0, gpa);
    }

    @Test
    public void testGenerateReportCard_NoGrades() {
        String report = gm.generateReportCard("S1");

        assertNotNull(report);
        assertTrue(report.contains("REPORT CARD"));
        assertTrue(report.contains("GPA"));
    }

    @Test
    public void testIsGradeAlreadyAssigned_NoFile() {
        assertFalse(gm.isGradeAlreadyAssigned("S1", "C1"));
    }

    @Test
    public void testStudentExists_EmptySystem() {
        assertFalse(gm.studentExists("S1"));
    }

    @Test
    public void testCourseExists_EmptySystem() {
        assertFalse(gm.courseExists("C1"));
    }

    @Test
    public void testFindCourseById_NotFound() {
        assertNull(gm.findCourseById("C1"));
    }

    @Test
    public void testFindStudentById_NotFound() {
        assertNull(gm.findstudentById("S1"));
    }

    @Test
    public void testGetGradePoint_AllGrades() {
        assertEquals(4.0, gm.getGradePoint("A"));
        assertEquals(3.5, gm.getGradePoint("B"));
        assertEquals(2.0, gm.getGradePoint("C"));
        assertEquals(1.0, gm.getGradePoint("D"));
        assertEquals(0.0, gm.getGradePoint("F"));
        assertEquals(0.0, gm.getGradePoint("X")); // invalid grade
    }

    @Test
    public void testGetAverageGPA_NoStudents() {
        assertEquals(0.0, gm.getAverageGPA());
    }

    @Test
    public void testGetPendingGradesCount_NoStudentsOrCourses() {
        assertEquals(0, gm.getPendingGradesCount());
    }

    @Test
    public void testGetTotalGradeCount_EmptyFile() {
        assertEquals(0, gm.getTotalGradeCount());
    }
}
