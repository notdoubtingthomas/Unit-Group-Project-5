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

public class Part5_ActivityManagerGUI_Starter extends JFrame {

    // TODO 1: Declare Swing components
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField hoursField;
    private JTextField priorityField;
    private JButton addButton;
    private JButton summaryButton;
    private JTextArea outputArea;

    // TODO 2: Store activities
    private ArrayList<BaseActivity_Starter> activities = new ArrayList<>();

    public Part5_ActivityManagerGUI_Starter() {
        super("Student Activity & Event Manager (Starter)");

        // TODO 3: Initialize components
        nameField = new JTextField(10);
        categoryField = new JTextField(10);
        hoursField = new JTextField(5);
        priorityField = new JTextField(5);

        addButton = new JButton("Add Activity");
        summaryButton = new JButton("Show Summary");

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // TODO 4: Build input panel using GridLayout (labels + fields + buttons)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 6, 6));
        inputPanel.setBorder(BorderFactory.createTitledBorder("New Activity"));

        inputPanel.add(new JLabel("Activity Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Category: "));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Weekly Hours:"));
        inputPanel.add(hoursField);
        inputPanel.add(new JLabel("Priority (1-3):"));
        inputPanel.add(priorityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        buttonPanel.add(addButton);
        buttonPanel.add(summaryButton);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputPanel.add(outputArea, BorderLayout.CENTER);

        // TODO 5: Add ActionListeners:
        // - Add Activity: validate input, create activity, append confirmation text
        // - Generate Summary: buildReport() and display in text area
        addButton.addActionListener(e -> handleAddActivity());
        summaryButton.addActionListener(e -> outputArea.setText(buildReport()));

        // TODO 6: Add panels to JFrame and configure window settings
        // setSize(...), setDefaultCloseOperation(...), setVisible(true)
        setLayout(new BorderLayout(8, 8));
        add(inputPanel,  BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleAddActivity() {
        BaseActivity_Starter activity = createActivityFromInput();
        if (activity != null) {
            activities.add(activity);
            outputArea.append("Added: " + activity.getDescription() + "\n");
            clearFields();
        }
    }

    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        hoursField.setText("");
        priorityField.setText("");
        nameField.requestFocus();
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
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String hoursStr = hoursField.getText().trim();
        String priStr = priorityField.getText().trim();

        if (name.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Activity name and category cannot be empty.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        double weeklyHours;
        try {
            weeklyHours = Double.parseDouble(hoursStr);
            if (weeklyHours < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Weekly hours must be a non-negative number (e.g. 3.5).",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int priority;
        try {
            priority = Integer.parseInt(priStr);
            if (priority < 1 || priority > 3) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Priority must be 1, 2, or 3.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new BaseActivity_Starter(name, category, weeklyHours, priority);
    }

    /*
     TODO 8: Build report using StringBuilder:
     - list each activity getDescription()
     - total hours
     - total priority score
    */
    private String buildReport() {
        if (activities.isEmpty()) {
            return "No activities recorded yet.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("       ACTIVITY SUMMARY\n");
        sb.append("================================\n");

        double totalHours = 0;
        int totalPriority = 0;

        for (BaseActivity_Starter a : activities) {
            sb.append("* ").append(a.getDescription()).append("\n");
            totalHours += a.getWeeklyHours();
            totalPriority += a.getPriority();
        }

        sb.append("================================\n");
        sb.append(String.format("Total activities : %d\n", activities.size()));
        sb.append(String.format("Total hours/week : %.1f\n", totalHours));
        sb.append(String.format("Total priority   : %d\n",  totalPriority));
        sb.append("================================\n");

        return sb.toString();
    }

    public static void main(String[] args) {
        new Part5_ActivityManagerGUI_Starter();
    }
}


public class BaseActivity_Starter {

    private String name;
    private String category;
    private double weeklyHours;
    private int priority;          // 1 = low, 2 = medium, 3 = high

    public BaseActivity_Starter(String name, String category,
                                double weeklyHours, int priority) {
        this.name = name;
        this.category = category;
        this.weeklyHours = weeklyHours;
        this.priority = priority;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getName()  {
        return name;
    }
    public String getCategory()  {
        return category;
    }
    public double getWeeklyHours() {
        return weeklyHours;
    }
    public int    getPriority()    {
        return priority;
    }

    /**
     * One-line summary used by the GUI report.
     * Example: "[Study] Calculus | 5.0 hrs/wk | Priority: 2"
     */
    public String getDescription() {
        return String.format("[%s] %s | %.1f hrs/wk | Priority: %d",
                category, name, weeklyHours, priority);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}