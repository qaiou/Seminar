package ui;

import controller.*;
import dao.SubmissionDAO;
import model.Submission;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CoordinatorPanel extends JPanel {

    private SessionController sessionController = new SessionController();
    private AssignmentContr assignmentController = new AssignmentContr();
    private SubmissionController submissionController = new SubmissionController();

    private JTable sessionTable;
    private JTable submissionTable;
    private JTable evaluatorTable;

    private StudentContr sc = new StudentContr();
    private EvaluatorContr ec = new EvaluatorContr();
    private SessionController ssc = new SessionController();

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
        
        submissionTable = new JTable();
        evaluatorTable = new JTable();
        sessionTable = new JTable();

        refreshSubmissions();
        refreshEvaluators();
        refreshSessions();

        JButton assignBtn = new JButton("Assign Selected");

        assignBtn.addActionListener(e -> {
            int sRow = submissionTable.getSelectedRow();
            int eRow = evaluatorTable.getSelectedRow();
            int sessRow = sessionTable.getSelectedRow();

            refreshSubmissions();
            refreshEvaluators();
            refreshSessions();
            
            if (sRow == -1 || eRow == -1 || sessRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a student, evaluator, and session.");
                return;
            }

            int submissionId = (int) submissionTable.getValueAt(sRow, 0);
            String evaluatorId = evaluatorTable.getValueAt(eRow, 0).toString();
            int sessionId = (int) sessionTable.getValueAt(sessRow, 0);

            assignmentController.assign(sessionId, submissionId, evaluatorId);
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
    JPanel panel = new JPanel(new BorderLayout(10,10));

    AwardController awardController = new AwardController();
    ReportController reportController = new ReportController();

    JTextArea outputArea = new JTextArea();
    outputArea.setEditable(false);

    JButton awardBtn = new JButton("Compute Awards");
    JButton reportBtn = new JButton("Generate Summary");
    JButton exportBtn = new JButton("Export CSV");

    awardBtn.addActionListener(e ->
        outputArea.setText(awardController.computeAwards())
    );

    reportBtn.addActionListener(e ->
        outputArea.setText(reportController.generateSummaryReport())
    );

    exportBtn.addActionListener(e ->
        outputArea.setText(reportController.exportCSV())
    );

    JPanel top = new JPanel();
    top.add(awardBtn);
    top.add(reportBtn);
    top.add(exportBtn);

    panel.add(top, BorderLayout.NORTH);
    panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
    return panel;
}


    // ---------------- REFRESH METHODS ----------------
    private void refreshSubmissions() {
        SubmissionDAO dao = new SubmissionDAO();
        List<Submission> submissions = dao.getAll();
        
        submissionTable.setModel(TableUtils.buildTableModel(
            submissions,
            new String[]{"submissionId", "studentId", "title", "presentationType", "status"},
            new String[]{"Submission ID", "Student ID", "Title", "Type", "Status"}
        ));
    }

    private void refreshEvaluators() {
        evaluatorTable.setModel(TableUtils.buildTableModel(
             ec.getAllEvaluators(),
            new String[]{"id", "name"},
            new String[]{"Evaluator ID", "Name"}
        ));
    }

    private void refreshSessions() {
        sessionTable.setModel(TableUtils.buildTableModel(
             ssc.getAllSessions(),
            new String[]{"sessionId", "sessionDate", "venue", "sessionType", "status"},
            new String[]{"Session ID", "Date", "Venue", "Type", "Status"}
        ));
    }
}
