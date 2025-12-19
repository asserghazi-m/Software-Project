package com.mycompany.studentgrading;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class GradeManager {
    private static final String GRADE_FILE = "grades.txt";
    private studentmethods studentManager = new studentmethods();
    private coursesmethod courseManager = new coursesmethod();

    public void assignGrade(String studentId, String courseId, String gradeValue) {
        // Ensure data directory exists
        new File("data").mkdirs();
        
        if (!studentExists(studentId)) {
            System.err.println("Student not found: " + studentId);
            return;
        }
        if (!courseExists(courseId)) {
            System.err.println("Course not found: " + courseId);
            return;
        }
        if (isGradeAlreadyAssigned(studentId, courseId)) {
            System.err.println("Grade already assigned for student " + studentId + " in course " + courseId);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(GRADE_FILE, true))) {
            writer.println(studentId + "," + courseId.toUpperCase() + "," + gradeValue.toUpperCase());
            System.out.println("Grade assigned successfully.");
        } catch (IOException e) {
            System.err.println("Error assigning grade: " + e.getMessage());
        }
    }

    public double calculateGPA(String studentId) {
        double totalPoints = 0.0;
        double totalCreditHours = 0.0;
        
        File file = new File(GRADE_FILE);
        if(!file.exists()) return 0.0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if(line.isEmpty()) continue;
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
            System.err.println("Error calculating GPA: " + e.getMessage());
        }

        if (totalCreditHours == 0) return 0.0;
        return totalPoints / totalCreditHours;
    }
    
    public String generateReportCard(String studentId) {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(50)).append("\n");
        report.append(" ".repeat(18)).append("REPORT CARD").append("\n");
        report.append("=".repeat(50)).append("\n");
        report.append("Student ID: ").append(studentId).append("\n");
        
        String studentName = findstudentById(studentId);
        if(studentName != null) {
            report.append("Student Name: ").append(studentName).append("\n");
        }
        
        report.append("-".repeat(50)).append("\n");
                // FIXED: Use custom padding instead of padRight()
        String courseHeader = "COURSE";
        String gradeHeader = "GRADE";
        report.append(courseHeader);
        report.append(" ".repeat(30 - courseHeader.length()));
        report.append(gradeHeader).append("\n");
        
        report.append("-".repeat(50)).append("\n");
        
        File file = new File(GRADE_FILE);
        if(file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if(line.isEmpty()) continue;
                    String[] parts = line.split(",");
                    if (parts.length == 3 && parts[0].equals(studentId)) {
                        String courseId = parts[1];
                        String grade = parts[2];
                        Courses course = findCourseById(courseId);
                        if (course != null) {
                            String courseName = course.getCourseName();
                            String displayText = courseName + " (" + courseId + ")";
                            report.append(displayText);
                            
                            // Calculate padding manually
                            int padding = 30 - displayText.length();
                            if (padding > 0) {
                                report.append(" ".repeat(padding));
                            }
                            
                            report.append(grade).append("\n");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                return "Error generating report card.";
            }
        }
        
        double gpa = calculateGPA(studentId);
        report.append("-".repeat(50)).append("\n");
        report.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        
        // Academic standing
        if (gpa >= 3.5) {
            report.append("Academic Standing: Honors\n");
        } else if (gpa >= 2.0) {
            report.append("Academic Standing: Good\n");
        } else {
            report.append("Academic Standing: Probation\n");
        }
        
        report.append("=".repeat(50));
        
        return report.toString();
    }

  
    public boolean isGradeAlreadyAssigned(String studentId, String courseId) {
        File file = new File(GRADE_FILE);
        if(!file.exists()) return false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(studentId) && 
                    parts[1].equalsIgnoreCase(courseId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error checking grade assignment: " + e.getMessage());
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
            if (c.getCourseID().equalsIgnoreCase(courseId)) {
                return true;
            }
        }
        return false;
    }

    public Courses findCourseById(String courseId) {
        for (Courses c : courseManager.getAllCourses()) {
            if (c.getCourseID().equalsIgnoreCase(courseId)) {
                return c;
            }
        }
        return null;
    }
    
    public String findstudentById(String studentId) {
        for (Studentdata s : studentManager.getallstudent()) {
            if (s.getId().equals(studentId)) {
                return s.getName();
            }
        }
        return null;
    }
    
    public double getGradePoint(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.5;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
    // Add these methods at the END of GradeManager class, before the final }

public double getAverageGPA() {
    ArrayList<Studentdata> students = new studentmethods().getallstudent();
    if (students.isEmpty()) return 0.0;
    
    double totalGPA = 0.0;
    int count = 0;
    
    for (Studentdata student : students) {
        double gpa = calculateGPA(student.getId());
        if (gpa > 0) { // Only count students with grades
            totalGPA += gpa;
            count++;
        }
    }
    
    return count > 0 ? totalGPA / count : 0.0;
}

public int getPendingGradesCount() {
    
    ArrayList<Studentdata> students = new studentmethods().getallstudent();
    ArrayList<Courses> courses = new coursesmethod().getAllCourses();
    
    if (students.isEmpty() || courses.isEmpty()) return 0;
    
    int pending = 0;
    
    for (Studentdata student : students) {
        int gradedCourses = 0;
        // Count how many courses this student has grades for
        try (Scanner scanner = new Scanner(new File("data/grades.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(student.getId())) {
                    gradedCourses++;
                }
            }
        } catch (IOException e) {
            // File doesn't exist or error reading
        }
        
        // If student has fewer grades than total courses, they have pending grades
        if (gradedCourses < courses.size()) {
            pending++;
        }
    }
    
    return pending;
}

public int getTotalGradeCount() {
    int count = 0;
    File file = new File("data/grades.txt");
    if (!file.exists()) return 0;
    
    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                count++;
            }
        }
    } catch (IOException e) {
        // File doesn't exist or error reading
    }
    return count;
}
}