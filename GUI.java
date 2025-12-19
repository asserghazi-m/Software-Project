package com.mycompany.studentgrading;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    
    private JTabbedPane tabbedPane;
    
    // Custom colors
    // Replace ALL color definitions at the top with these:
    private final Color PRIMARY_COLOR = new Color(0, 100, 200);      // Blue for buttons
    private final Color SUCCESS_COLOR = new Color(0, 150, 0);        // Green for buttons
    private final Color WARNING_COLOR = new Color(200, 100, 0);      // Orange for buttons
    private final Color DANGER_COLOR = new Color(200, 0, 0);         // Red for buttons
    private final Color INFO_COLOR = new Color(100, 0, 150);         // Purple for buttons

    // CHANGE THESE for black text and white background:
    private final Color DARK_BG = Color.WHITE;                      // Changed to WHITE background
    private final Color LIGHT_BG = Color.WHITE;                     // Changed to WHITE background  
    private final Color CARD_BG = Color.WHITE;                      // Keep WHITE

    // Add text colors:
    private final Color TEXT_COLOR = Color.BLACK;                   // BLACK text
    private final Color TEXT_SECONDARY = new Color(80, 80, 80);     // Dark gray for secondary text                    // White
    
    public GUI() {
        setTitle("Student Grading System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Ensure data directory exists
        ensureDataDirectory();
        
        // Set modern look and feel
        setModernLookAndFeel();
        
        initUI();
        setVisible(true);
    }
    
    private void setModernLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Customize UI defaults
            UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.BOLD, 14));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void ensureDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("Created data directory: " + dataDir.getAbsolutePath());
        }
    }
    
    private void initUI() {
        // Create modern toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        toolbar.setBackground(Color.WHITE);
        toolbar.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Create toolbar buttons
        JButton dashboardBtn = createModernButton("📊 Dashboard", PRIMARY_COLOR);
        JButton studentsBtn = createModernButton("👥 Students", SUCCESS_COLOR);
        JButton coursesBtn = createModernButton("📚 Courses", INFO_COLOR);
        JButton gradesBtn = createModernButton("📝 Grades", WARNING_COLOR);
        JButton predictorBtn = createModernButton("🔮 Predictor", new Color(142, 68, 173));
        JButton reportsBtn = createModernButton("📄 Reports", DANGER_COLOR);
        
        toolbar.add(dashboardBtn);
        toolbar.add(studentsBtn);
        toolbar.add(coursesBtn);
        toolbar.add(gradesBtn);
        toolbar.add(predictorBtn);
        toolbar.add(reportsBtn);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(Color.BLUE);
        tabbedPane.setForeground(Color.BLACK);
        
        // Add all tabs with full implementations
        tabbedPane.addTab("📊 Dashboard", createDashboardPanel());
        tabbedPane.addTab("👥 Students", createStudentPanel());
        tabbedPane.addTab("📚 Courses", createCoursePanel());
        tabbedPane.addTab("📝 Grades", createGradePanel());
        tabbedPane.addTab("🔮 Predictor", createPredictorPanel());
        tabbedPane.addTab("📄 Reports", createReportPanel());
        
        // Set tab colors
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setBackgroundAt(i, LIGHT_BG);
        }
        
        // Add components to frame
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        
        // Add button listeners
        dashboardBtn.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        studentsBtn.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        coursesBtn.addActionListener(e -> tabbedPane.setSelectedIndex(2));
        gradesBtn.addActionListener(e -> tabbedPane.setSelectedIndex(3));
        predictorBtn.addActionListener(e -> tabbedPane.setSelectedIndex(4));
        reportsBtn.addActionListener(e -> tabbedPane.setSelectedIndex(5));
    }
    
    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLUE);
        button.setBackground(bgColor);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    // ==================== DASHBOARD PANEL ====================
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK);
        
        // Header
        JPanel header = createCardPanel();
        JLabel title = new JLabel("📊 Student Grading System Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.BLACK);
        header.add(title);
        
        // Load data from backend
        studentmethods studentManager = new studentmethods();
        coursesmethod courseManager = new coursesmethod();
        GradeManager gradeManager = new GradeManager();
        
        // Get actual counts
        int studentCount = studentManager.getallstudent().size();
        int courseCount = courseManager.getAllCourses().size();
        int totalGrades = getGradeCount();
        
        // Stats panel with real data
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        statsPanel.setBackground(LIGHT_BG);
        statsPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        statsPanel.add(createStatCard("Total Students", String.valueOf(studentCount), PRIMARY_COLOR));
        statsPanel.add(createStatCard("Total Courses", String.valueOf(courseCount), SUCCESS_COLOR));
        statsPanel.add(createStatCard("Total Grades", String.valueOf(totalGrades), INFO_COLOR));
        statsPanel.add(createStatCard("Average GPA", "0.00", new Color(255, 159, 67)));
        statsPanel.add(createStatCard("Pending Grades", "0", WARNING_COLOR));
        statsPanel.add(createStatCard("Completion", 
            studentCount > 0 && courseCount > 0 ? 
            String.format("%.0f%%", ((double)totalGrades / (studentCount * courseCount)) * 100) : "0%", 
            new Color(52, 152, 219)));
        
        // Quick actions panel
        JPanel actionsPanel = createCardPanel();
        actionsPanel.setLayout(new GridLayout(2, 3, 15, 15));
        
        JButton addStudentBtn = createActionButton("➕ Add New Student", PRIMARY_COLOR);
        JButton assignGradeBtn = createActionButton("📝 Assign Grades", SUCCESS_COLOR);
        JButton generateReportBtn = createActionButton("📊 Generate Reports", INFO_COLOR);
        JButton predictorBtn = createActionButton("🔮 Grade Predictor", WARNING_COLOR);
        JButton exportBtn = createActionButton("📤 Export Data", new Color(0, 0, 255));
        JButton refreshBtn = createActionButton("🔄 Refresh Dashboard", new Color(0, 0, 255));
        
        // Add action listeners
        addStudentBtn.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        assignGradeBtn.addActionListener(e -> tabbedPane.setSelectedIndex(3));
        generateReportBtn.addActionListener(e -> tabbedPane.setSelectedIndex(5));
        predictorBtn.addActionListener(e -> tabbedPane.setSelectedIndex(4));
        exportBtn.addActionListener(e -> exportData());
        refreshBtn.addActionListener(e -> refreshDashboard(panel));
        
        actionsPanel.add(addStudentBtn);
        actionsPanel.add(assignGradeBtn);
        actionsPanel.add(generateReportBtn);
        actionsPanel.add(predictorBtn);
        actionsPanel.add(exportBtn);
        actionsPanel.add(refreshBtn);
        
        // Recent activity panel
        JPanel activityPanel = createCardPanel();
        activityPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel activityTitle = new JLabel("📈 Recent Activity");
        activityTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        String[] columns = {"Time", "Activity", "Details"};
        Object[][] activityData = getRecentActivities();
        
        JTable activityTable = new JTable(activityData, columns);
        customizeTable(activityTable);
        
        JScrollPane tableScroll = new JScrollPane(activityTable);
        tableScroll.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        
        activityPanel.add(activityTitle, BorderLayout.NORTH);
        activityPanel.add(tableScroll, BorderLayout.CENTER);
        
        // System status panel
        JPanel statusPanel = createCardPanel();
        statusPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel statusTitle = new JLabel("⚙️ System Status");
        statusTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JPanel statusDetails = new JPanel(new GridLayout(3, 1, 5, 5));
        statusDetails.setBackground(CARD_BG);
        
        boolean studentsFile = new File("student.txt").exists();
        boolean coursesFile = new File("courses.txt").exists();
        boolean gradesFile = new File("grades.txt").exists();
        
        statusDetails.add(createStatusItem("📁 Students Database", studentsFile ? "✅ Connected" : "❌ Missing"));
        statusDetails.add(createStatusItem("📁 Courses Database", coursesFile ? "✅ Connected" : "❌ Missing"));
        statusDetails.add(createStatusItem("📁 Grades Database", gradesFile ? "✅ Connected" : "❌ Missing"));
        
        statusPanel.add(statusTitle, BorderLayout.NORTH);
        statusPanel.add(statusDetails, BorderLayout.CENTER);
        
        // Layout
        panel.add(header, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);
        
        JPanel bottomLeft = new JPanel(new BorderLayout());
        bottomLeft.add(actionsPanel, BorderLayout.CENTER);
        bottomLeft.add(statusPanel, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(LIGHT_BG);
        bottomPanel.add(bottomLeft);
        bottomPanel.add(activityPanel);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ==================== STUDENT PANEL ====================
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);
        
        // Header
        JLabel header = new JLabel("👥 Student Management");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(5);
        
        // Left panel - Add Student Form
        JPanel leftPanel = createCardPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel formTitle = new JLabel("Add New Student");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        formTitle.setForeground(Color.BLACK);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:"), gbc);
        JTextField idField = createTextField();
        gbc.gridx = 1;
        formPanel.add(idField, gbc);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Full Name:"), gbc);
        JTextField nameField = createTextField();
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        
        // Add button
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JButton addButton = createStyledButton("➕ Add Student", SUCCESS_COLOR);
        formPanel.add(addButton, gbc);
        
        // Status label
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        
        leftPanel.add(formTitle, BorderLayout.NORTH);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(statusLabel, BorderLayout.SOUTH);
        
        // Right panel - Student List
        JPanel rightPanel = createCardPanel();
        rightPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel listTitle = new JLabel("Student Records");
        listTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        listTitle.setForeground(Color.BLACK);
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(CARD_BG);
        
        JTextField searchField = createTextField();
        searchField.setPreferredSize(new Dimension(200, 35));
        searchField.setBorder(BorderFactory.createTitledBorder("Search"));
        
        JButton searchButton = createStyledButton("🔍 Search", PRIMARY_COLOR);
        JButton clearButton = createStyledButton("Clear", new Color(149, 165, 166));
        
        searchPanel.add(new JLabel("Search by ID or Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        
        // Student table with real data
        List<Studentdata> students = new studentmethods().getallstudent();
        String[] columns = {"Student ID", "Full Name"};
        Object[][] data = new Object[students.size()][2];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
        }
        
        JTable studentTable = createTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(studentTable);
        
        rightPanel.add(listTitle, BorderLayout.NORTH);
        rightPanel.add(searchPanel, BorderLayout.CENTER);
        rightPanel.add(tableScroll, BorderLayout.SOUTH);
        
        // Add action listeners
        studentmethods studentManager = new studentmethods();
        
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            
            if (id.isEmpty() || name.isEmpty()) {
                statusLabel.setText("Please fill all fields");
                statusLabel.setForeground(DANGER_COLOR);
                return;
            }
            
            Studentdata student = new Studentdata(id, name);
            if (studentManager.addstudents(student)) {
                statusLabel.setText("Student added successfully!");
                statusLabel.setForeground(SUCCESS_COLOR);
                idField.setText("");
                nameField.setText("");
                refreshStudentTable(studentTable);
            } else {
                statusLabel.setText("Error: Student ID already exists!");
                statusLabel.setForeground(DANGER_COLOR);
            }
        });
        
        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim().toLowerCase();
            List<Studentdata> filtered = new ArrayList<>();
            for (Studentdata s : students) {
                if (s.getId().toLowerCase().contains(searchTerm) || 
                    s.getName().toLowerCase().contains(searchTerm)) {
                    filtered.add(s);
                }
            }
            
            Object[][] filteredData = new Object[filtered.size()][2];
            for (int i = 0; i < filtered.size(); i++) {
                filteredData[i][0] = filtered.get(i).getId();
                filteredData[i][1] = filtered.get(i).getName();
            }
            studentTable.setModel(new javax.swing.table.DefaultTableModel(
                filteredData, columns));
        });
        
        clearButton.addActionListener(e -> {
            searchField.setText("");
            refreshStudentTable(studentTable);
        });
        
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== COURSE PANEL ====================
    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);
        
        // Header
        JLabel header = new JLabel("📚 Course Management");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        
        // Left panel - Add Course Form
        JPanel leftPanel = createCardPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel formTitle = new JLabel("Add New Course");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Course ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Course ID:"), gbc);
        JTextField courseIdField = createTextField();
        gbc.gridx = 1;
        formPanel.add(courseIdField, gbc);
        
        // Course Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Course Name:"), gbc);
        JTextField courseNameField = createTextField();
        gbc.gridx = 1;
        formPanel.add(courseNameField, gbc);
        
        // Credit Hours
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Credit Hours:"), gbc);
        JTextField creditField = createTextField();
        gbc.gridx = 1;
        formPanel.add(creditField, gbc);
        
        // Course Type
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Course Type:"), gbc);
        
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton coreRadio = new JRadioButton("Core", true);
        JRadioButton electiveRadio = new JRadioButton("Elective");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(coreRadio);
        typeGroup.add(electiveRadio);
        typePanel.add(coreRadio);
        typePanel.add(electiveRadio);
        gbc.gridx = 1;
        formPanel.add(typePanel, gbc);
        
        // Add button
        gbc.gridx = 1; gbc.gridy = 4;
        JButton addButton = createStyledButton("➕ Add Course", INFO_COLOR);
        formPanel.add(addButton, gbc);
        
        // Status label
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        
        leftPanel.add(formTitle, BorderLayout.NORTH);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(statusLabel, BorderLayout.SOUTH);
        
        // Right panel - Course List
        JPanel rightPanel = createCardPanel();
        rightPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel listTitle = new JLabel("Course Catalog");
        listTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        // Load course data
        List<Courses> courses = new coursesmethod().getAllCourses();
        String[] columns = {"Course ID", "Course Name", "Credits", "Type"};
        Object[][] data = new Object[courses.size()][4];
        for (int i = 0; i < courses.size(); i++) {
            Courses c = courses.get(i);
            data[i][0] = c.getCourseID();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getCreditHours();
            data[i][3] = "Core"; // Default type
        }
        
        JTable courseTable = createTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(courseTable);
        
        rightPanel.add(listTitle, BorderLayout.NORTH);
        rightPanel.add(tableScroll, BorderLayout.CENTER);
        
        // Add action listener
        coursesmethod courseManager = new coursesmethod();
        
        addButton.addActionListener(e -> {
            String courseId = courseIdField.getText().trim().toUpperCase();
            String courseName = courseNameField.getText().trim();
            String creditText = creditField.getText().trim();
            
            if (courseId.isEmpty() || courseName.isEmpty() || creditText.isEmpty()) {
                statusLabel.setText("Please fill all fields");
                statusLabel.setForeground(DANGER_COLOR);
                return;
            }
            
            try {
                int credits = Integer.parseInt(creditText);
                Courses course = new Courses(courseName, courseId, credits);
                
                if (courseManager.addcourse(course)) {
                    statusLabel.setText("Course added successfully!");
                    statusLabel.setForeground(SUCCESS_COLOR);
                    courseIdField.setText("");
                    courseNameField.setText("");
                    creditField.setText("");
                    refreshCourseTable(courseTable);
                } else {
                    statusLabel.setText("Error: Course ID already exists!");
                    statusLabel.setForeground(DANGER_COLOR);
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("Credit hours must be a number!");
                statusLabel.setForeground(DANGER_COLOR);
            }
        });
        
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== GRADE PANEL ====================
    private JPanel createGradePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);
        
        // Header
        JLabel header = new JLabel("📝 Grade Management");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Main split
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setDividerLocation(400);
        
        // Left panel - Grade Assignment
        JPanel leftPanel = createCardPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel assignTitle = new JLabel("Assign Grade");
        assignTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Student selection
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student:"), gbc);
        JComboBox<String> studentCombo = new JComboBox<>();
        studentCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loadStudentCombo(studentCombo);
        gbc.gridx = 1;
        formPanel.add(studentCombo, gbc);
        
        // Course selection
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Course:"), gbc);
        JComboBox<String> courseCombo = new JComboBox<>();
        courseCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loadCourseCombo(courseCombo);
        gbc.gridx = 1;
        formPanel.add(courseCombo, gbc);
        
        // Grade selection
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Grade:"), gbc);
        JComboBox<String> gradeCombo = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        gradeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        formPanel.add(gradeCombo, gbc);
        
        // Assign button
        gbc.gridx = 1; gbc.gridy = 3;
        JButton assignButton = createStyledButton("✅ Assign Grade", SUCCESS_COLOR);
        formPanel.add(assignButton, gbc);
        
        // Status label
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        
        // GPA Calculator
        JPanel gpaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        gpaPanel.setBorder(new TitledBorder("GPA Calculator"));
        gpaPanel.setBackground(CARD_BG);
        
        JTextField gpaStudentField = createTextField();
        gpaStudentField.setPreferredSize(new Dimension(150, 30));
        JButton calculateButton = createStyledButton("Calculate GPA", PRIMARY_COLOR);
        JLabel gpaResult = new JLabel("GPA: ");
        gpaResult.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        gpaPanel.add(new JLabel("Student ID:"));
        gpaPanel.add(gpaStudentField);
        gpaPanel.add(calculateButton);
        gpaPanel.add(gpaResult);
        
        leftPanel.add(assignTitle, BorderLayout.NORTH);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(statusLabel, BorderLayout.SOUTH);
        leftPanel.add(gpaPanel, BorderLayout.NORTH);
        
        // Right panel - Recent Grades & Reports
        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightSplit.setDividerLocation(300);
        
        // Top right - Recent grades
        JPanel topRight = createCardPanel();
        topRight.setLayout(new BorderLayout(10, 10));
        
        JLabel recentTitle = new JLabel("Recent Grade Assignments");
        recentTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        String[] columns = {"Student", "Course", "Grade"};
        Object[][] gradeData = getGradeData();
        JTable gradeTable = createTable(gradeData, columns);
        JScrollPane gradeScroll = new JScrollPane(gradeTable);
        
        topRight.add(recentTitle, BorderLayout.NORTH);
        topRight.add(gradeScroll, BorderLayout.CENTER);
        
        // Bottom right - Report generator
        JPanel bottomRight = createCardPanel();
        bottomRight.setLayout(new BorderLayout(10, 10));
        
        JLabel reportTitle = new JLabel("Report Card Generator");
        reportTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JPanel reportInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        reportInput.setBackground(CARD_BG);
        
        JTextField reportStudentField = createTextField();
        reportStudentField.setPreferredSize(new Dimension(150, 30));
        JButton generateButton = createStyledButton("Generate Report", INFO_COLOR);
        
        reportInput.add(new JLabel("Student ID:"));
        reportInput.add(reportStudentField);
        reportInput.add(generateButton);
        
        JTextArea reportArea = new JTextArea(10, 30);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setEditable(false);
        JScrollPane reportScroll = new JScrollPane(reportArea);
        
        bottomRight.add(reportTitle, BorderLayout.NORTH);
        bottomRight.add(reportInput, BorderLayout.CENTER);
        bottomRight.add(reportScroll, BorderLayout.SOUTH);
        
        rightSplit.setTopComponent(topRight);
        rightSplit.setBottomComponent(bottomRight);
        
        mainSplit.setLeftComponent(leftPanel);
        mainSplit.setRightComponent(rightSplit);
        
        // Action listeners
        GradeManager gradeManager = new GradeManager();
        
        assignButton.addActionListener(e -> {
            String student = (String) studentCombo.getSelectedItem();
            String course = (String) courseCombo.getSelectedItem();
            String grade = (String) gradeCombo.getSelectedItem();
            
            if (student == null || course == null) {
                statusLabel.setText("Please select student and course");
                statusLabel.setForeground(DANGER_COLOR);
                return;
            }
            
            String studentId = student.split(" - ")[0];
            String courseId = course.split(" - ")[0];
            
            gradeManager.assignGrade(studentId, courseId, grade);
            statusLabel.setText("Grade assigned successfully!");
            statusLabel.setForeground(SUCCESS_COLOR);
            refreshGradeTable(gradeTable);
        });
        
        calculateButton.addActionListener(e -> {
            String studentId = gpaStudentField.getText().trim();
            if (!studentId.isEmpty()) {
                double gpa = gradeManager.calculateGPA(studentId);
                gpaResult.setText(String.format("GPA: %.2f", gpa));
            }
        });
        
        generateButton.addActionListener(e -> {
            String studentId = reportStudentField.getText().trim();
            if (!studentId.isEmpty()) {
                String report = gradeManager.generateReportCard(studentId);
                reportArea.setText(report);
            }
        });
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(mainSplit, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== PREDICTOR PANEL ====================
    private JPanel createPredictorPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);
        
        // Header
        JLabel header = new JLabel("🔮 Grade Predictor");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Main content
        JPanel mainPanel = createCardPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Course Type
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Course Type:"), gbc);
        
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton coreRadio = new JRadioButton("Core Course", true);
        JRadioButton electiveRadio = new JRadioButton("Elective Course");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(coreRadio);
        typeGroup.add(electiveRadio);
        typePanel.add(coreRadio);
        typePanel.add(electiveRadio);
        gbc.gridx = 1;
        mainPanel.add(typePanel, gbc);
        
        // Coursework Slider
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Coursework Score:"), gbc);
        
        JSlider courseworkSlider = new JSlider(0, 60, 10);
        customizeSlider(courseworkSlider);
        JLabel courseworkValue = new JLabel("50");
        courseworkValue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JPanel sliderPanel1 = new JPanel(new BorderLayout(10, 5));
        sliderPanel1.add(courseworkSlider, BorderLayout.CENTER);
        sliderPanel1.add(courseworkValue, BorderLayout.EAST);
        gbc.gridx = 1;
        mainPanel.add(sliderPanel1, gbc);
        
        // Exam Slider
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Expected Exam Score:"), gbc);
        
        JSlider examSlider = new JSlider(0, 40, 10);
        customizeSlider(examSlider);
        JLabel examValue = new JLabel("50");
        examValue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JPanel sliderPanel2 = new JPanel(new BorderLayout(10, 5));
        sliderPanel2.add(examSlider, BorderLayout.CENTER);
        sliderPanel2.add(examValue, BorderLayout.EAST);
        gbc.gridx = 1;
        mainPanel.add(sliderPanel2, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton predictButton = createStyledButton("🔮 Predict Grade", INFO_COLOR);
        predictButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        predictButton.setPreferredSize(new Dimension(180, 50));
        
        JButton resetButton = createStyledButton("🔄 Reset", new Color(149, 165, 166));
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resetButton.setPreferredSize(new Dimension(120, 50));
        
        buttonPanel.add(predictButton);
        buttonPanel.add(resetButton);
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(buttonPanel, gbc);
        
        // Result display
        JLabel resultLabel = new JLabel("Predicted Grade: ");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(resultLabel, gbc);
        
        // Slider listeners
        courseworkSlider.addChangeListener(e -> {
            courseworkValue.setText(String.valueOf(courseworkSlider.getValue()));
        });
        
        examSlider.addChangeListener(e -> {
            examValue.setText(String.valueOf(examSlider.getValue()));
        });
        
        // Action listeners
        predictButton.addActionListener(e -> {
            String courseType = coreRadio.isSelected() ? "C" : "E";
            double coursework = courseworkSlider.getValue();
            double exam = examSlider.getValue();
            
            String result = GradePredictor.predictGrade(courseType, coursework, exam);
            resultLabel.setText("Predicted Grade: " + result);
            
            if (result.contains("Error")) {
                resultLabel.setForeground(DANGER_COLOR);
            } else {
                resultLabel.setForeground(SUCCESS_COLOR);
            }
        });
        
        resetButton.addActionListener(e -> {
            courseworkSlider.setValue(50);
            examSlider.setValue(50);
            coreRadio.setSelected(true);
            resultLabel.setText("Predicted Grade: ");
            resultLabel.setForeground(Color.BLACK);
        });
        
        // Grade scale panel
        JPanel scalePanel = createCardPanel();
        scalePanel.setLayout(new GridLayout(2, 2, 10, 10));
        scalePanel.setBorder(new TitledBorder("Grade Scale Reference"));
        
        scalePanel.add(createScalePanel("Core Courses", 
            "A: ≥ 90\nB: ≥ 75\nC: ≥ 60\nD: ≥ 50\nF: < 50"));
        scalePanel.add(createScalePanel("Elective Courses", 
            "A: ≥ 93\nB: ≥ 83\nC: ≥ 73\nD: ≥ 63\nF: < 63"));
        scalePanel.add(createScalePanel("GPA Points", 
            "A = 4.0\nB = 3.5\nC = 2.0\nD = 1.0\nF = 0.0"));
        scalePanel.add(createScalePanel("Weighting", 
            "Core: 40% CW, 60% Exam\nElective: 50% CW, 50% Exam"));
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        panel.add(scalePanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ==================== REPORT PANEL ====================
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);
        
        // Header
        JLabel header = new JLabel("📄 Report Cards");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Control panel
        JPanel controlPanel = createCardPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        
        JComboBox<String> studentCombo = new JComboBox<>();
        studentCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        studentCombo.setPreferredSize(new Dimension(200, 35));
        loadStudentCombo(studentCombo);
        
        JComboBox<String> termCombo = new JComboBox<>(new String[]{
            "Fall 2024", "Spring 2024", "Summer 2024", "Full Year 2024"
        });
        termCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JComboBox<String> formatCombo = new JComboBox<>(new String[]{
            "Detailed", "Summary", "Transcript"
        });
        formatCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JButton generateButton = createStyledButton("📄 Generate Report", PRIMARY_COLOR);
        JButton printButton = createStyledButton("🖨️ Print", new Color(149, 165, 166));
        JButton exportButton = createStyledButton("📥 Export PDF", SUCCESS_COLOR);
        
        controlPanel.add(new JLabel("Student:"));
        controlPanel.add(studentCombo);
        controlPanel.add(new JLabel("Term:"));
        controlPanel.add(termCombo);
        controlPanel.add(new JLabel("Format:"));
        controlPanel.add(formatCombo);
        controlPanel.add(generateButton);
        controlPanel.add(printButton);
        controlPanel.add(exportButton);
        
        // Report display
        JTextArea reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setEditable(false);
        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);
        
        JScrollPane reportScroll = new JScrollPane(reportArea);
        reportScroll.setBorder(new TitledBorder("Report Preview"));
        
        // Add action listener
        GradeManager gradeManager = new GradeManager();
        
        generateButton.addActionListener(e -> {
            String selected = (String) studentCombo.getSelectedItem();
            if (selected != null) {
                String studentId = selected.split(" - ")[0];
                String report = gradeManager.generateReportCard(studentId);
                reportArea.setText(report);
            }
        });
        
        printButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Print functionality would be implemented here", 
                "Print Report", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        exportButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "PDF export would be implemented here", 
                "Export Report", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(controlPanel, BorderLayout.CENTER);
        panel.add(reportScroll, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ==================== HELPER METHODS ====================
    
    private JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        return card;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = createCardPanel();
        card.setLayout(new BorderLayout(10, 10));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(Color.BLACK);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(100, 100, 100));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setBorder(new EmptyBorder(15, 10, 15, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private JTable createTable(Object[][] data, String[] columns) {
        JTable table = new JTable(data, columns);
        customizeTable(table);
        return table;
    }
    
    private void customizeTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(DARK_BG);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 0));
    }
    
    private void customizeSlider(JSlider slider) {
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    }
    
    private JPanel createScalePanel(String title, String content) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(240, 242, 245));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(DARK_BG);
        
        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        contentArea.setBackground(new Color(240, 242, 245));
        contentArea.setEditable(false);
        contentArea.setBorder(null);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentArea, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatusItem(String label, String status) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BG);
        
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel statusComp = new JLabel(status);
        statusComp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        if (status.contains("✅")) {
            statusComp.setForeground(SUCCESS_COLOR);
        } else if (status.contains("❌")) {
            statusComp.setForeground(DANGER_COLOR);
        } else {
            statusComp.setForeground(new Color(100, 100, 100));
        }
        
        panel.add(labelComp, BorderLayout.WEST);
        panel.add(statusComp, BorderLayout.EAST);
        
        return panel;
    }
    
    // ==================== DATA METHODS ====================
    
    private int getGradeCount() {
        int count = 0;
        File file = new File("data/grades.txt");
        if (!file.exists()) return 0;
        
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            while (scanner.hasNextLine()) {
                if (!scanner.nextLine().trim().isEmpty()) {
                    count++;
                }
            }
        } catch (IOException e) {
            return 0;
        }
        return count;
    }
    
    private Object[][] getRecentActivities() {
        List<Object[]> activities = new ArrayList<>();
        
        try {
            File gradesFile = new File("data/grades.txt");
            if (gradesFile.exists()) {
                List<String> lines = java.nio.file.Files.readAllLines(gradesFile.toPath());
                int count = 0;
                for (int i = Math.max(0, lines.size() - 5); i < lines.size(); i++) {
                    String line = lines.get(i).trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            activities.add(new Object[]{
                                "Recent",
                                "Grade Assigned",
                                "Student: " + parts[0] + ", Course: " + parts[1] + ", Grade: " + parts[2]
                            });
                            count++;
                            if (count >= 5) break;
                        }
                    }
                }
            }
            
            if (activities.isEmpty()) {
                activities.add(new Object[]{"Today", "System Started", "Welcome to Student Grading System"});
                activities.add(new Object[]{"-", "No recent activities", "Add students, courses, or grades"});
            }
            
        } catch (IOException e) {
            activities.add(new Object[]{"Error", "Cannot read activities", e.getMessage()});
        }
        
        return activities.toArray(new Object[0][]);
    }
    
    private Object[][] getGradeData() {
        List<Object[]> grades = new ArrayList<>();
        File file = new File("data/grades.txt");
        
        if (file.exists()) {
            try (java.util.Scanner scanner = new java.util.Scanner(file)) {
                int count = 0;
                while (scanner.hasNextLine() && count < 10) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            grades.add(new Object[]{parts[0], parts[1], parts[2]});
                            count++;
                        }
                    }
                }
            } catch (IOException e) {
                // Ignore
            }
        }
        
        if (grades.isEmpty()) {
            grades.add(new Object[]{"No grades", "found", "yet"});
        }
        
        return grades.toArray(new Object[0][]);
    }
    
    private void loadStudentCombo(JComboBox<String> combo) {
        combo.removeAllItems();
        studentmethods manager = new studentmethods();
        for (Studentdata student : manager.getallstudent()) {
            combo.addItem(student.getId() + " - " + student.getName());
        }
    }
    
    private void loadCourseCombo(JComboBox<String> combo) {
        combo.removeAllItems();
        coursesmethod manager = new coursesmethod();
        for (Courses course : manager.getAllCourses()) {
            combo.addItem(course.getCourseID() + " - " + course.getCourseName());
        }
    }
    
    private void refreshStudentTable(JTable table) {
        List<Studentdata> students = new studentmethods().getallstudent();
        Object[][] data = new Object[students.size()][2];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
        }
        table.setModel(new javax.swing.table.DefaultTableModel(
            data, new String[]{"Student ID", "Full Name"}));
    }
    
    private void refreshCourseTable(JTable table) {
        List<Courses> courses = new coursesmethod().getAllCourses();
        Object[][] data = new Object[courses.size()][4];
        for (int i = 0; i < courses.size(); i++) {
            Courses c = courses.get(i);
            data[i][0] = c.getCourseID();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getCreditHours();
            data[i][3] = "Core";
        }
        table.setModel(new javax.swing.table.DefaultTableModel(
            data, new String[]{"Course ID", "Course Name", "Credits", "Type"}));
    }
    
    private void refreshGradeTable(JTable table) {
        Object[][] newData = getGradeData();
        table.setModel(new javax.swing.table.DefaultTableModel(
            newData, new String[]{"Student", "Course", "Grade"}));
    }
    
    private void refreshDashboard(JPanel dashboardPanel) {
        int index = tabbedPane.indexOfComponent(dashboardPanel);
        tabbedPane.remove(index);
        tabbedPane.insertTab("📊 Dashboard", null, createDashboardPanel(), null, index);
        tabbedPane.setSelectedIndex(index);
        
        JOptionPane.showMessageDialog(this, 
            "Dashboard refreshed with latest data!", 
            "Refresh Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exportData() {
        JOptionPane.showMessageDialog(this, 
            "Export functionality would be implemented here", 
            "Export Data", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
