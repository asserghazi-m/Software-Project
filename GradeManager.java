/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

/**
 *
 * @author sara
 */

import java.io.*;
import java.util.Scanner;

public class GradeManager {
    private static final String GRADE_FILE = "grades.txt";

    studentmethods studentManager = new studentmethods();
    coursesmethod courseManager = new coursesmethod();

    public void assignGrade(String studentId, String courseId, String gradeValue) {
        if (!studentExists(studentId)) {
            System.out.println("Student not found.");
            return;
        }
        if (!courseExists(courseId)) {
            System.out.println("Course not found.");
            return;
        }
        if (isGradeAlreadyAssigned(studentId, courseId)) {
            System.out.println("Grade already assigned for this course.");
            return;
        }

        Grade grade = new Grade(studentId, courseId, gradeValue);

        try (PrintWriter writer = new PrintWriter(new FileWriter(GRADE_FILE, true))) {
            writer.println(grade.toString());
            System.out.println("Grade assigned successfully.");
        } catch (IOException e) {
            System.out.println("Error writing grade: " + e.getMessage());
        }
    }

    public double calculateGPA(String studentId) {
        double totalPoints = 0.0;
        double totalCreditHours = 0.0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(GRADE_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String sid = parts[0];
                    String cid = parts[1];
                    String gradeValue = parts[2];

                    if (sid.equals(studentId)) {
                        Courses course = findCourseById(cid);
                        if (course != null) {
                            int creditHours = course.getCreditHours();
                            totalCreditHours += creditHours;
                            totalPoints += getGradePoint(gradeValue) * creditHours;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading grades: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();

   }
        }

        if (totalCreditHours == 0) {
            return 0.0;
        }
        return totalPoints / totalCreditHours;
    }
    
        public String generateReportCard(String studentId) {
       

        String report = "==============================\n";
        report += "       REPORT CARD\n";
        report += "==============================\n";
        report += "Student ID: " + studentId + "\n";
        report+= "student NAME:  "+ findstudentById(studentId) + "\n";
        report += "------------------------------\n";
        String coursesInfo = "";

        try {
            Scanner scanner = new Scanner(new File(GRADE_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(studentId)) {
                    String courseId = parts[1];
                    String grade = parts[2];
                    Courses course = findCourseById(courseId);
                    if (course != null) {
                        coursesInfo += "Course: " + course.getCourseName() + "\n";
                        coursesInfo += "Course ID: " + courseId + "\n";
                        coursesInfo += "Grade: " + grade + "\n";
                        coursesInfo += "------------------------------\n";
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return "Error generating report card: " + e.getMessage();
        }

        double gpa = calculateGPA(studentId);
        report += coursesInfo;
        report += "GPA: " + String.format("%.2f", gpa) + "\n";
        report += "==============================";

        return report;
    }
  

      public boolean isGradeAlreadyAssigned(String studentId, String courseId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(studentId) && parts[1].equals(courseId.toUpperCase())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading grades: " + e.getMessage());
        }
        return false;


      }
    
     public boolean studentExists(String studentId) {
        for (Studentdata s : studentManager.getallstudent()) {
            if (s.getId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    public boolean courseExists(String courseId) {
        for (Courses c : courseManager.getAllCourses()) {
            if (c.getCourseID().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public Courses findCourseById(String courseId) {
        for (Courses c : courseManager.getAllCourses()) {
            if (c.getCourseID().equals(courseId)) {
                return c;
            }
        }
        return null;
    }
    public String findstudentById(String courseId) {
        for (Studentdata c : studentManager.getallstudent()) {
            if (c.getId().equals(courseId)) {
                return c.getName();
            }
        }
        return null;
    }
    
     public double getGradePoint(String grade) {
        switch (grade) {
            case "A": return 4.0;
            case "B":return 3.5;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
     
     
}