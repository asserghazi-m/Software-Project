/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentgrading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author asser
 */


public class GUI extends JFrame implements ActionListener {
   
    private JButton student,grade,exit,gpa,Rcard,filter1,filter2,what,course;
    private JPanel p1;
     public GUI(){
        
         
        this.setSize(800,800);
        this.setTitle("Main menu");
        
        p1=new JPanel();
        Border basedlevel=BorderFactory.createRaisedBevelBorder();
        
       
        
      BoxLayout boxlayout = new BoxLayout(p1, BoxLayout.Y_AXIS);
      p1.setLayout(boxlayout);
      p1.setBackground(Color.BLACK);
      
      
      
      
      
       JLabel j1=new JLabel("Student Grading System ");
       j1.setFont(new Font("Segoe UI", Font.BOLD, 20));
       j1.setAlignmentX(CENTER_ALIGNMENT);
       j1.setForeground(Color.RED);
      
      
        student=new JButton("Add student");
        student.addActionListener(this);
        student.setAlignmentX(CENTER_ALIGNMENT);
        
        student.setBorder(new LineBorder(Color.RED, 2));
        student.setPreferredSize(new Dimension(2000, 100));
        
        
        course =new JButton("Add course");
        course.addActionListener(this);
        course.setAlignmentX(CENTER_ALIGNMENT);
        
        course.setBorder(new LineBorder(Color.RED, 2));
       
        
        
        grade=new JButton("Student Grade");
        grade.addActionListener(this);
        grade.setAlignmentX(CENTER_ALIGNMENT);
       
        grade.setBorder(new LineBorder(Color.RED, 2));
         grade.setToolTipText("To assign grade in specific course");
        
        
        
        gpa=new JButton("Calculate GPA");
//        gpa.setBackground(Color.GREEN);
        gpa.addActionListener(this);
        gpa.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon i3=new ImageIcon("C:\\Users\\ibrah\\Downloads\\icons8-gpa-calculator-24.png");
        gpa.setIcon(i3);
        gpa.setBorder(new LineBorder(Color.RED, 2));
        
        
        Rcard=new JButton("Generate Report Card");
 //       Rcard.setBackground(Color.BLACK);
        Rcard.addActionListener(this);
        Rcard.setAlignmentX(CENTER_ALIGNMENT);
        
        Rcard.setBorder(new LineBorder(Color.RED, 2));
        
        
        
        filter1=new JButton("Filter by Course Name");
//        filter1.setBackground(Color.GREEN);
        filter1.addActionListener(this);
        filter1.setAlignmentX(CENTER_ALIGNMENT);
        
        filter1.setBorder(new LineBorder(Color.RED, 2));
        filter1.setToolTipText("To see by coursesnames studentnames and grades");
        
        
        filter2=new JButton("Filter by Student Name");
        filter2.addActionListener(this);
        filter2.setAlignmentX(CENTER_ALIGNMENT);
       
        filter2.setBorder(new LineBorder(Color.RED, 2));
        filter2.setToolTipText("To see by name courses and grades");
        
        what=new JButton("WHAT IF");
        what.addActionListener(this);
        what.setAlignmentX(CENTER_ALIGNMENT);
        
        what.setBorder(new LineBorder(Color.RED, 2));
        what.setToolTipText("To predict your final grade");
        
        
        
        
        
        
        exit=new JButton("Exit");
        exit.setBackground(Color.red);
        exit.addActionListener(this);
        exit.setAlignmentX(CENTER_ALIGNMENT);
        exit.setBorder(new LineBorder(Color.RED, 2));
        exit.setMnemonic(KeyEvent.VK_F);
        exit.setToolTipText("Terminate the project");
        
        
        student.setFont(new Font("Segoe UI", Font.BOLD, 15));
        course.setFont(new Font("Segoe UI", Font.BOLD, 15));
        grade.setFont(new Font("Segoe UI", Font.BOLD, 15));
        Rcard.setFont(new Font("Segoe UI", Font.BOLD, 15));
        filter1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        filter2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        gpa.setFont(new Font("Segoe UI", Font.BOLD, 15));
        exit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        
        p1.add(Box.createRigidArea(new Dimension(140, 30)));
        p1.add(j1);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(student);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(course);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(grade);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(gpa);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(Rcard);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(filter1);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(filter2);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(what);
        p1.add(Box.createRigidArea(new Dimension(0, 30)));
        p1.add(exit);
        
        
        
       
       
        
        add(p1);
        
         
         
         
        this.setVisible(true);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==student){
            studentfunction();
        }
        if(e.getSource()==course){
            coursefunction();
        }
        
        if(e.getSource()==grade )
        {
            gradingfunc();
        }
        if(e.getSource()==gpa)
        {
            funct();
        }
        
        if(e.getSource()==Rcard )
        {
            function2();
        }
        if(e.getSource()==filter1 )
        {
            function3();
        }
        
        if(e.getSource()==filter2 )
        {
            function4();
        }
        if(e.getSource()==what){
            whatif();
        }
        
        if(e.getSource()==exit){
            System.exit(0);
        }
        
    }
    
    
    public void studentfunction(){
        
        JFrame studentframe=new JFrame();
        studentframe.setTitle("studentadder");
        studentframe.setSize(800, 800);
        JPanel s1=new JPanel();
       
        JLabel j1=new JLabel("Student id: ");
        s1.add(j1);
        JTextField t1=new JTextField(10);
        s1.add(t1);

         JLabel j2=new JLabel("Student name: ");
        s1.add(j2);
        JTextField t2=new JTextField(10);
        s1.add(t2);
        
        JButton b1=new JButton("Add");
        s1.add(b1);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Studentdata sd=null;
                studentmethods sm = null;
                
                    String id=t1.getText();
                    String name=t2.getText();
                   
                    GradeManager g1=new GradeManager();
                   
                    if(!name.isEmpty()&&!id.isEmpty()){
                        
                         sd=new Studentdata(id,name);
                         sm=new studentmethods();
                        boolean added = sm.addstudents(sd);
                        
                        if (added==true) {
                            JOptionPane.showMessageDialog(studentframe, "Student added!", "Success", JOptionPane.INFORMATION_MESSAGE);}
                      else {
                            JOptionPane.showMessageDialog(studentframe, "Student ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                    JOptionPane.showMessageDialog(studentframe, "please fill all things","report",JOptionPane.ERROR_MESSAGE);}
                    
                            
            }
    
    }); 
        studentframe.add(s1);
        studentframe.setVisible(true);
       
    }
    
     public void coursefunction(){
        
        JFrame courseframe=new JFrame();
        courseframe.setTitle("studentadder");
        courseframe.setSize(700, 700);
        JPanel s1=new JPanel();
       
        JLabel j1=new JLabel("Coursename: ");
        s1.add(j1);
        JTextField t1=new JTextField(10);
        s1.add(t1);

        JLabel j2=new JLabel("CourseId: ");
        s1.add(j2);
        JTextField t2=new JTextField(10);
        s1.add(t2);
        
         JLabel j3=new JLabel("Credithours: ");
        s1.add(j3);
        JTextField t3=new JTextField(10);
        s1.add(t3);
        
        JButton b1=new JButton("Add");
        s1.add(b1);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Courses sd=null;
                coursesmethod cm = null;
                
                    String id=t2.getText();
                    String name=t1.getText();
                    String credit=t3.getText();
                    
                   
                   
                   
                    if(!name.isEmpty()&&!id.isEmpty()&&!credit.isEmpty()){
                        int credit1=Integer.parseInt(credit);
                        if (credit1 <= 0 || credit1 > 4) {
                            JOptionPane.showMessageDialog(courseframe, "Credit hours must be more than 0 and at most 4.", "Invalid Credit", JOptionPane.ERROR_MESSAGE);
                             return;
                                 }
                        
                        
                         sd=new Courses(name,id,credit1);
                         cm=new coursesmethod();
                        boolean added = cm.addcourse(sd);
                        
                        if (added==true) {
                            JOptionPane.showMessageDialog(courseframe, "course added!", "Success", JOptionPane.INFORMATION_MESSAGE);}
                      else {
                            JOptionPane.showMessageDialog(courseframe, "course ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }else{
                    JOptionPane.showMessageDialog(courseframe, "please fill all things","report",JOptionPane.ERROR_MESSAGE);}
            
            }
    });
        
        
        courseframe.add(s1);
        courseframe.setVisible(true);
       
    }
    
    public void gradingfunc(){
         JFrame gradeframe=new JFrame();
        gradeframe.setTitle("studentgrade");
        gradeframe.setSize(700, 700);
        JPanel g1=new JPanel();
        
        
        
        JLabel a1=new JLabel("Student id: ");
        g1.add(a1);
        JTextField c1=new JTextField(10);
        g1.add(c1);
        JLabel d1=new JLabel("Course id: ");
        g1.add(d1);
        JTextField e1=new JTextField(10);
        g1.add(e1);
        JLabel m1=new JLabel("Student Grade: ");
        g1.add(m1);
        JTextField n1=new JTextField(10);
        g1.add(n1);
        
        
        JButton a2=new JButton("Add");
        g1.add(a2);
        
        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                GradeManager gg = null;
                
                    String id=c1.getText();
                    String courseid=e1.getText();
                    String grade=n1.getText();
                   
                     if (id.isEmpty() || courseid.isEmpty() || grade.isEmpty()) {
                        JOptionPane.showMessageDialog(gradeframe, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (grade.length() != 1 || !grade.matches("[ABCDFabcdf]")) {
                        JOptionPane.showMessageDialog(gradeframe, "Grade must be one character: A, B, C, D, or F.", "Invalid Grade", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                         gg = new GradeManager();
                        
                        boolean added = gg.isGradeAlreadyAssigned (id,courseid);
                        boolean courseexist=gg.courseExists(courseid.toUpperCase());
                        boolean studentexist=gg.studentExists(id);
                        if (  !studentexist ) {
                            
                            JOptionPane.showMessageDialog(gradeframe, "student is not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return; }
                        if (!courseexist ) {
                            
                            JOptionPane.showMessageDialog(gradeframe, "course is not exist", "ERROR", JOptionPane.ERROR_MESSAGE); return;}
                        
                               
                        if (!added) {
                             gg.assignGrade(id, courseid.toUpperCase(), grade);
                            JOptionPane.showMessageDialog(gradeframe, "Grade add!", "Success", JOptionPane.INFORMATION_MESSAGE);}
                      else {
                            JOptionPane.showMessageDialog(gradeframe, "GRADE already exists!", "SAME PERSON", JOptionPane.ERROR_MESSAGE);
                        }
                    
        
     }
        });
        
        gradeframe.add(g1);
        gradeframe.setVisible(true);
                }
    
    
     public void funct(){
          JFrame gpaframe=new JFrame();
        gpaframe.setTitle("studentgpa");
        gpaframe.setSize(400, 400);
        JPanel j1=new JPanel();
        
        
        JLabel i1=new JLabel("Student id: ");
        j1.add(i1);
        JTextField i2=new JTextField(10);
        j1.add(i2);
        
        

        JButton i3=new JButton("Generate GPA");
        j1.add(i3);
        
        
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                GradeManager gg1 =  new GradeManager();
                
                    String id=i2.getText();
                    boolean studentexist=gg1.studentExists(id);
                    if (  !studentexist ) {
                            
                            JOptionPane.showMessageDialog(gpaframe, "student is not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return; }
                    
                   
                    if(!id.isEmpty()){
                         
                       
                         JOptionPane.showMessageDialog(gpaframe, " GPA " +  gg1.calculateGPA(id), " GPA", JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{
                    JOptionPane.showMessageDialog(gpaframe, "something went wrong","report",JOptionPane.ERROR_MESSAGE);}
        
     }
     
        });
         gpaframe.add(j1);
        gpaframe.setVisible(true);              
                }
     
     
     
            public void function2() {
           JFrame Rcardframe = new JFrame();
           Rcardframe.setTitle("Report Card Generator");
           Rcardframe.setSize(400, 400);
           JPanel r1 = new JPanel();

           JLabel r2 = new JLabel("Student ID: ");
           r1.add(r2);
           JTextField r3 = new JTextField(10);
           r1.add(r3);

           JButton r4 = new JButton("Generate");
           r1.add(r4);

           r4.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   String id = r3.getText();
                   GradeManager gg = new GradeManager();
                   
                   if(!gg.studentExists(id)){
                         JOptionPane.showMessageDialog(Rcardframe, "Student ID is not exist", "Input Error", JOptionPane.ERROR_MESSAGE);
                         return;
                   }
                   if (!id.isEmpty()) {
                      
                       JOptionPane.showMessageDialog(Rcardframe, gg.generateReportCard(id), "Report card", JOptionPane.PLAIN_MESSAGE);

                       
                   } else {
                       JOptionPane.showMessageDialog(Rcardframe, "Student ID cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
           });

           Rcardframe.add(r1);
           Rcardframe.setVisible(true);
           
           
           
       }

    
        public void function3() {
        JFrame frame = new JFrame("Filter by Course Name ");
        frame.setSize(300, 300);
        JPanel panel = new JPanel();

        JTextField courseField = new JTextField(10);
        JButton filterButton = new JButton("Filter");

        panel.add(new JLabel("Course Name:"));
        panel.add(courseField);
        panel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coursename = courseField.getText();
                if (!coursename.isEmpty()) {
                    coursesmethod cm = new coursesmethod();
                    String result = cm.filterbyname(coursename.toLowerCase());

                    JOptionPane.showMessageDialog(frame, result,"sucess",JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a course name.","Input error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

        
            public void function4() {
            JFrame frame = new JFrame("Filter by Student Name");
            frame.setSize(300, 300);
            JPanel panel = new JPanel();

            JTextField studentField = new JTextField(10);
            JButton filterButton = new JButton("Filter");

            panel.add(new JLabel("Student Name:"));
            panel.add(studentField);
            panel.add(filterButton);

            filterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String studentname = studentField.getText();
                    if (!studentname.isEmpty()) {
                        studentmethods sm = new studentmethods();
                        String result = sm.filterbyname(studentname.toLowerCase());

                        JOptionPane.showMessageDialog(frame, result,"sucess",JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter a student name.","Input error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        }
        public void whatif(){
        JFrame predict=new JFrame();
        predict.setTitle("prediction");
        predict.setSize(700, 700);
        JPanel p1=new JPanel();
        
        JLabel j1=new JLabel("coursetype: ");
        p1.add(j1);
        JTextField t1=new JTextField(10);
        p1.add(t1);
       
        
        JLabel j2=new JLabel("coursework: ");
        p1.add(j2);
        JTextField t2=new JTextField(10);
        p1.add(t2);
        
        
        JLabel j3=new JLabel("finalexamprediction: ");
        p1.add(j3);
        JTextField t3=new JTextField(10);
        p1.add(t3);
        
       JButton guess= new JButton("Guess");
        p1.add(guess);
        
        guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String type=t1.getText();
               String course= t2.getText();
               String Final=t3.getText();
               
               
               
               if(!type.isEmpty()&&!course.isEmpty()&&!Final.isEmpty()){
                   double coursework=Double.parseDouble(course);
                   double finalgrade=Double.parseDouble(Final);
                  JOptionPane.showMessageDialog(predict,"GRADE IS: "+ GradePredictor.predictGrade(type, coursework, finalgrade),"prediction",JOptionPane.PLAIN_MESSAGE);
               
               
               
               }
               
            }
        
        
        
        
        
        
        
        
        
        
        
        });
        
        
        
        
        
        
        
         predict.add(p1);
        predict.setVisible(true);
        
        
        
        
        
        } 
     
}
