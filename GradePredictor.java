/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

/**
 *
 * @author asser
 */


public class GradePredictor {

    
    public static String predictGrade( String courseType, double courseworkGrade, double finalExamPrediction) {
        courseType = courseType.toUpperCase();

       
        if (!courseType.equals("C") && !courseType.equals("E")) {
            return "Error: Course type must be 'C' for Core or 'E' for Elective.";
        }

        
        if (courseworkGrade < 0 || courseworkGrade > 100 || finalExamPrediction < 0 || finalExamPrediction > 100) {
            return "Error: Grades must be between 0 and 100.";
        }

        
        double finalScore;
        if (courseType.equals("C")) {
            finalScore = courseworkGrade  + finalExamPrediction ;
        } else {
            finalScore = courseworkGrade  + finalExamPrediction ;
        }

        
        String letterGrade = getLetterGrade(finalScore, courseType);

        
        return letterGrade;
    }

    
    private static String getLetterGrade(double score, String courseType) {
        if (courseType.equals("E")) {
            if (score >= 93) return "A";
            else if (score >= 83) return "B";
            else if (score >= 73) return "C";
            else if (score >= 63) return "D";
           
            else return "F";
        } else { 
            
            if (score >= 90) return "A";
            else if (score >= 75) return "B";
            else if (score >= 60) return "C";
            else if (score >= 50) return "D";
            else return "F";
        }
    }
}
