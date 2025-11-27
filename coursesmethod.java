/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

/**
 *
 * @author jana
 */
import java.io.*;
import java.util.*;


public class coursesmethod {
    private static final String courseFile = "courses.txt";
    
     public boolean addcourse(Courses course){
        if(isIdUnique(course.getCourseID())){
        PrintWriter pw=null;
        try{
            pw=new PrintWriter(new FileWriter(courseFile,true));
            pw.println(course.toString());
            return true;
        
        
        
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if(pw!=null){
                pw.close();
            }
        
        }
        }
       
        return false; 
    
    }
   
    public boolean isIdUnique(String idToCheck) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File(courseFile));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 1 && parts[1].equals(idToCheck.toUpperCase())) {
                return false; 
            }
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } finally {
        if (scanner != null) {
            scanner.close();
        }
    }
    return true; 
}

    public ArrayList<Courses> getAllCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(courseFile))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String id = parts[1];
                    int ch = Integer.parseInt(parts[2]);
                    courses.add(new Courses(name, id, ch));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading courses: " + e.getMessage());
        }
        return courses;
    }

    

    public String filterbyname(String name){
       studentmethods s1=new studentmethods() ;
       ArrayList<Courses> courses = getAllCourses();
       for(Courses sa : courses){
           String studentname=null;
           if( sa.getCourseName().equals(name)){
                  String result="courseName: "+sa.getCourseName() +"\n";
                  Scanner read = null;
               try{
                   read=new Scanner(new File("grades.txt"));
                   while(read.hasNextLine()){
                       String line=read.nextLine();
                       String[] parts=line.split(",");
                       
                      if(parts[1].equals(sa.getCourseID())){
                        String id=parts[0];
                         String grade=parts[2];
                             
                        
                    
                   for(Studentdata s:s1.getallstudent()){
                           if(s.getId().equals(id)){
                               studentname=s.getName();} }
                   
                   
                             result+= "student: " + studentname + " , grade: " + grade+ "\n";
                      }
                       
                   }
                   
                  
                   
               } 
               catch(IOException e){
                   System.out.println(e.getMessage());}
               finally{
                  read.close(); 
               }
               return result;
             
           }
           
           
          
           
           
       
       }
      
      return "course is not found"; 
   }

}
