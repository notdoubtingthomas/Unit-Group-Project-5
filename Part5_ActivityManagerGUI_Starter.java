/*
 Part 5 (Starter) — Swing Activity & Event Manager GUI
 Modules 11–12: Swing Components I & II

 Integration notes:
 - This folder includes its own Starter model classes (BaseActivity_Starter, AcademicActivity_Starter)
   so students can run Part 5 independently.
 - Students can later refactor to reuse Part 4 classes if desired.

 Teaching Cues (Liang-aligned):
 - JFrame → JPanel → Components
 - Use layout managers (BorderLayout + GridLayout)
 - Validate inputs before object creation
 - Use StringBuilder for multi-line reports
 - Event-driven flow: code runs when buttons are clicked
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Part5_ActivityManagerGUI_Starter<BaseActivity_Starter> extends JFrame {

    // TODO 1: Declare Swing components
    // private JTextField nameField;
    // private JTextField categoryField;
    // private JTextField hoursField;
    // private JTextField priorityField;
    // private JButton addButton;
    // private JButton summaryButton;
    // private JTextArea outputArea;

    private JTextField nameField, categoryField, hoursField, priorityField;
    private JButton addButton, summaryButton;
    private JTextArea outputArea;

    // TODO 2: Store activities
    // private ArrayList<BaseActivity_Starter> activities = new ArrayList<>();

    private ArrayList<BaseActivity_Starter> activities = new ArrayList<>();

    public Part5_ActivityManagerGUI_Starter() {
        super("Student Activity & Event Manager (Starter)");

        // TODO 3: Initialize components
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));


        nameField = new JTextField();
        categoryField = new JTextField();
        hoursField = new JTextField();
        priorityField = new JTextField();


        gridPanel.add(new JLabel("Activity Name: "));
        gridPanel.add(nameField);
        gridPanel.add(new JLabel(" Category: "));
        gridPanel.add(categoryField);
        gridPanel.add(new JLabel(" Weekly Hours: "));
        gridPanel.add(hoursField);
        gridPanel.add(new JLabel(" Priority Level: "));
        gridPanel.add(priorityField);

        addButton = new JButton("Add");
        summaryButton = new JButton("Summary");
        gridPanel.add(addButton);
        gridPanel.add(summaryButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        gridPanel.add(outputArea);

        // TODO 4: Build input panel using GridLayout (labels + fields + buttons)

        setLayout(new BorderLayout(10,10));

        // TODO 5: Add ActionListeners:
        // - Add Activity: validate input, create activity, append confirmation text
        // - Generate Summary: buildReport() and display in text area

        addButton.addActionListener(e -> {
            outputArea.setText(buildReport());
        });

        summaryButton.addActionListener(e -> {
            outputArea.setText(buildReport());
        });

        // TODO 6: Add panels to JFrame and configure window settings
        // setSize(...), setDefaultCloseOperation(...), setVisible(true)
        add(gridPanel, BorderLayout.NORTH);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
     TODO 7: Create an activity from input.
     Minimum validation rules:
     - name/category not empty
     - weeklyHours >= 0
     - priority in [1..3]
     Return null if invalid (and show a JOptionPane).
    */
    private BaseActivity_Starter createActivityFromInput() {
        // TODO
        return null;
    }

    /*
     TODO 8: Build report using StringBuilder:
     - list each activity getDescription()
     - total hours
     - total priority score
    */
    private String buildReport() {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        new Part5_ActivityManagerGUI_Starter();
    }
}
