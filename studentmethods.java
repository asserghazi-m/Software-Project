package com.mycompany.studentgrading;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ahmed
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class studentmethods {
    private static final String studentfile="student.txt";
    public boolean addstudents(Studentdata student){
        if(isIdUnique(student.getId())){
        PrintWriter pw=null;
        try{
            pw=new PrintWriter(new FileWriter(studentfile,true));
            pw.println(student.toString());
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
    public boolean isIdUnique(String id) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File(studentfile));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 1 && parts[0].equals(id)) {
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
    
    public ArrayList<Studentdata> getallstudent(){
        ArrayList<Studentdata> student = new ArrayList<>(); 
        Scanner data=null;
        try {
            data=new Scanner (new File(studentfile));
            while(data.hasNext()){
                String line=data.nextLine();
                String[] parts=line.split(",");
                student.add(new Studentdata(parts[0],parts[1]));
                
                
            
            }
          
           
            
        } catch (IOException e){
                    System.out.println(e.getMessage());
                    }
        finally{
            if(data!=null){
                data.close();
            }
        }
        return student;
    }
    
   public String filterbyname(String name){
       coursesmethod c1=new coursesmethod();
       ArrayList<Studentdata> students = getallstudent();
       for(Studentdata sa : students){
           
          
           String coursename=null;
           if( sa.getName().equals(name)){
                  String result="Name: "+sa.getName() +"\n";
                  Scanner s1 = null;
               try{
                   s1=new Scanner(new File("grades.txt"));
                   while(s1.hasNextLine()){
                       String line=s1.nextLine();
                       String[] parts=line.split(",");
                       
                        if(parts[0].equals(sa.getId())){
                        String course=parts[1];
                         String grade=parts[2];
                             
                        
                    
                   for(Courses c:c1.getAllCourses()){
                           if(c.getCourseID().equals(course)){
                               coursename=c.getCourseName();} }
                   
                   
                             result+= "course: " + coursename + " , grade: " + grade+ "\n";
                       }
                   }
                   
                  
                   
               } 
               catch(IOException e){
                   System.out.println(e.getMessage());}
               finally{
                  s1.close(); 
               }
               return result;
             
           }
           
           
          
           
           
       
       }
      
      return "student is not found"; 
   }
    
    
}
