package com.mycompany.studentgrading;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradePredictorTest {

    @Test
    public void testPredictGrade_Core_A() {
        String courseType = "C";
        double courseworkGrade = 50;
        double finalExamPrediction = 50;

        String expected = "A";     // 50 + 50 = 100 → A (core >=90)
        String result = GradePredictor.predictGrade(courseType, courseworkGrade, finalExamPrediction);

        assertEquals(expected, result);
    }

    @Test
    public void testPredictGrade_Core_F() {
        String courseType = "C";
        double courseworkGrade = 10;
        double finalExamPrediction = 20;

        String expected = "F";     // 10 + 20 = 30 → F
        String result = GradePredictor.predictGrade(courseType, courseworkGrade, finalExamPrediction);

        assertEquals(expected, result);
    }

    @Test
    public void testPredictGrade_InvalidType() {
        String courseType = "X";
        double courseworkGrade = 50;
        double finalExamPrediction = 50;

        String expected = "Error: Course type must be 'C' for Core or 'E' for Elective.";
        String result = GradePredictor.predictGrade(courseType, courseworkGrade, finalExamPrediction);

        assertEquals(expected, result);
    }
}
