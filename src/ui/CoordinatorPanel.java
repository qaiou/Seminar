package ui;

import controller.*;
import model.*;

import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class CoordinatorPanel extends JPanel {

    private SessionController sessionController = new SessionController();
    private AssignmentContr assignmentController = new AssignmentContr();
    private ReportController reportController = new ReportController();

    private JTable sessionTable;
    private JTable submissionTable;
    private JTable evaluatorTable;

    private JTextArea outputArea;

    public CoordinatorPanel() {
        setLayout(new BorderLayout(10, 10));

        add(new JLabel("Coordinator Dashboard", JLabel.CENTER), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Sessions", buildSessionPanel());
        tabs.add("Assignments", buildAssignmentPanel());
        tabs.add("Reports & Awards", buildReportPanel());

        add(tabs, BorderLayout.CENTER);
    }

    // ---------------- SESSION TAB ----------------
    private JPanel buildSessionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField dateField = new JTextField("2026-02-08");
        JTextField venueField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Oral", "Poster"});
        JButton createBtn = new JButton("Create Session");

        form.add(new JLabel("Date:"));
        form.add(dateField);
        form.add(new JLabel("Venue:"));
        form.add(venueField);
        form.add(new JLabel("Type:"));
        form.add(typeCombo);
        form.add(new JLabel());
        form.add(createBtn);

        sessionTable = new JTable();
        refreshSessions();

        createBtn.addActionListener(e -> {
            sessionController.createSession(
                dateField.getText(),
                venueField.getText(),
                (String) typeCombo.getSelectedItem()
            );
            refreshSessions();
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(sessionTable), BorderLayout.CENTER);
        return panel;
    }

    // ---------------- ASSIGNMENT TAB ----------------
    private JPanel buildAssignmentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        StudentContr sc = new StudentContr();
        List<Submission> submissions = sc.getAllStudents();

        submissionTable = new JTable();
        submissionTable.setModel(TableUtils.buildTableModel(
            submissions,
            new String[]{"submissionId", "title"},
            new String[]{"ID", "Title"}
        ));

        evaluatorTable = new JTable();
        sessionTable = new JTable();

        refreshStudents();
        refreshEvaluators();
        refreshSessions();

        JButton assignBtn = new JButton("Assign Selected");

        assignBtn.addActionListener(e -> {
            int sRow = submissionTable.getSelectedRow();
            int eRow = evaluatorTable.getSelectedRow();
            int sessRow = sessionTable.getSelectedRow();

            if (sRow == -1 || eRow == -1 || sessRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a student, evaluator, and session.");
                return;
            }

            String studentId = submissionTable.getValueAt(sRow, 0).toString();
            String evaluatorId = evaluatorTable.getValueAt(eRow, 0).toString();
            int sessionId = (int) sessionTable.getValueAt(sessRow, 0);

            assignmentController.assign(sessionId, studentId, evaluatorId);
            JOptionPane.showMessageDialog(this, "Assignment created.");
        });

        JPanel center = new JPanel(new GridLayout(1, 3));
        center.add(new JScrollPane(submissionTable), BorderLayout.CENTER);
        center.add(new JScrollPane(evaluatorTable));
        center.add(new JScrollPane(sessionTable));

        panel.add(center, BorderLayout.CENTER);
        panel.add(assignBtn, BorderLayout.SOUTH);
        return panel;
    }

    // ---------------- REPORT TAB ----------------
    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JButton reportBtn = new JButton("Generate Report");
        JButton awardBtn = new JButton("Compute Awards");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        reportBtn.addActionListener(e -> outputArea.setText(reportController.generateReport()));
        awardBtn.addActionListener(e -> outputArea.setText(reportController.computeAwards()));

        JPanel top = new JPanel();
        top.add(reportBtn);
        top.add(awardBtn);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }

    // ---------------- REFRESH METHODS ----------------
    private void refreshStudents() {
        submissionTable.setModel(TableUtils.buildTableModel(new StudentContr().getAllStudents()));
    }

    private void refreshEvaluators() {
        evaluatorTable.setModel(TableUtils.buildTableModel(new EvaluatorContr().getAllEvaluators()));
    }

    private void refreshSessions() {
        sessionTable.setModel(TableUtils.buildTableModel(sessionController.getAllSessions()));
    }
}
