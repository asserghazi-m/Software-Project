package com.mycompany.studentgrading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentdataTest {

    private Studentdata student;

    @BeforeEach
    public void setUp() {
        student = new Studentdata("S1", "Ahmed");
    }

    @Test
    public void testGetId() {
        assertEquals("S1", student.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Ahmed", student.getName());
    }

    @Test
    public void testToString() {
        String result = student.toString();
        assertNotNull(result);
        assertTrue(result.contains("S1"));
        assertTrue(result.contains("Ahmed"));
    }
}
