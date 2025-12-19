/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

/**
 *
 * @author sara
 */
  
public class Grade {
    private String studentId;
    private String courseId;
    private String gradeValue;

    public Grade(String studentId, String courseId, String gradeValue) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.gradeValue = gradeValue;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    @Override
    public String toString() {
        return studentId + "," + courseId .toUpperCase()+ "," + gradeValue.toUpperCase();
    }
}
