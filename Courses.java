/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

/**
 *
 * @author jana
 */
public class Courses {
    private String courseName, courseID;
    private int creditHours;

    public Courses(String name, String ID, int CH) {
        this.courseName = name;
        this.courseID = ID;
        this.creditHours = CH;
        
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCreditHours() {
        return creditHours;
    }

 

    @Override
    public String toString() {
        return courseName + "," + courseID.toUpperCase() + "," + creditHours ;
    }

}
