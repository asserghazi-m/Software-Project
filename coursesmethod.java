package com.mycompany.studentgrading;

import java.io.*;
import java.util.*;

public class coursesmethod {
    private static final String courseFile = "courses.txt";
    
    public boolean addcourse(Courses course) {
        // Ensure data directory exists
        new File("data").mkdirs();
        
        if(isIdUnique(course.getCourseID())) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(courseFile, true))) {
                pw.println(course.toString());
                return true;
            } catch(IOException e) {
                System.err.println("Error adding course: " + e.getMessage());
                return false;
            }
        }
        return false; 
    }
    
    public boolean isIdUnique(String idToCheck) {
        File file = new File(courseFile);
        if(!file.exists()) return true;
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equalsIgnoreCase(idToCheck)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error checking course ID: " + e.getMessage());
        }
        return true; 
    }

    public ArrayList<Courses> getAllCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        File file = new File(courseFile);
        if(!file.exists()) return courses;
        
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String id = parts[1];
                    int ch = Integer.parseInt(parts[2].trim());
                    courses.add(new Courses(name, id, ch));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading courses: " + e.getMessage());
        }
        return courses;
    }
   
    public int getCourseCount() {
        return getAllCourses().size();
    }
}