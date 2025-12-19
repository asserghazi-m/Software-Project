package com.mycompany.studentgrading;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class studentmethods {
    // Change to relative path
    private static final String studentfile = "student.txt";
    
    public boolean addstudents(Studentdata student) {
        // Ensure data directory exists
        new File("data").mkdirs();
        
        if(isIdUnique(student.getId())) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(studentfile, true))) {
                pw.println(student.toString());
                return true;
            } catch(IOException e) {
                System.err.println("Error adding student: " + e.getMessage());
                return false;
            }
        }
        return false; 
    }
    
    public boolean isIdUnique(String id) {
        File file = new File(studentfile);
        if(!file.exists()) return true;
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(id)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error checking ID: " + e.getMessage());
        }
        return true; 
    }
    
    public ArrayList<Studentdata> getallstudent() {
        ArrayList<Studentdata> students = new ArrayList<>(); 
        File file = new File(studentfile);
        if(!file.exists()) return students;
        
        try (Scanner data = new Scanner(file)) {
            while(data.hasNextLine()) {
                String line = data.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if(parts.length >= 2) {
                    students.add(new Studentdata(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return students;
    }
    
    public int getStudentCount() {
        return getallstudent().size();
    }
}