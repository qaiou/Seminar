package ui;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EvaluatorPanel extends JPanel {

    private EvaluatorContr evaluatorContr = new EvaluatorContr();

    private JTable assignmentTable;
    private JTextField problemField;
    private JTextField methodField;
    private JTextField resultField;
    private JTextField presentationField;
    private JTextArea commentArea;

    private String currentEvaluatorId;

    public EvaluatorPanel(String evaluatorId) {
        this.currentEvaluatorId = evaluatorId;
        setLayout(new BorderLayout(10, 10));

        add(new JLabel("Evaluator Dashboard", JLabel.CENTER), BorderLayout.NORTH);

        assignmentTable = new JTable();
        refreshAssignments();

        add(new JScrollPane(assignmentTable), BorderLayout.CENTER);
        add(buildEvaluationForm(), BorderLayout.SOUTH);
    }

    // ---------------- FORM ----------------
    private JPanel buildEvaluationForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        problemField = new JTextField();
        methodField = new JTextField();
        resultField = new JTextField();
        presentationField = new JTextField();
        commentArea = new JTextArea(3, 20);

        JButton submitBtn = new JButton("Submit Evaluation");

        panel.add(new JLabel("Problem Clarity:"));
        panel.add(problemField);
        panel.add(new JLabel("Methodology:"));
        panel.add(methodField);
        panel.add(new JLabel("Results:"));
        panel.add(resultField);
        panel.add(new JLabel("Presentation:"));
        panel.add(presentationField);
        panel.add(new JLabel("Comments:"));
        panel.add(new JScrollPane(commentArea));
        panel.add(new JLabel());
        panel.add(submitBtn);

        submitBtn.addActionListener(e -> submitEvaluation());

        return panel;
    }

    // ---------------- LOAD ASSIGNMENTS ----------------
    private void refreshAssignments() {
        List<Assignment> assignments =
                evaluatorContr.getAssignedPresentations(currentEvaluatorId);

        assignmentTable.setModel(TableUtils.buildTableModel(
                assignments,
                new String[]{"assignmentId", "submissionId", "sessionId", "status"},
                new String[]{"Assignment ID", "Submission ID", "Session ID", "Status"}
        ));
    }

    // ---------------- SUBMIT ----------------
    private void submitEvaluation() {
        int row = assignmentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a submission to evaluate.");
            return;
        }

        int assignmentId = (int) assignmentTable.getValueAt(row, 0);

        Evaluation e = new Evaluation();
        e.setAssignmentId(assignmentId);
        e.setProblemClarity(Integer.parseInt(problemField.getText()));
        e.setMethodology(Integer.parseInt(methodField.getText()));
        e.setResults(Integer.parseInt(resultField.getText()));
        e.setPresentation(Integer.parseInt(presentationField.getText()));
        e.setComments(commentArea.getText());

        evaluatorContr.submitEvaluation(e);
        JOptionPane.showMessageDialog(this, "Evaluation submitted.");
        clearForm();
    }

    private void clearForm() {
        problemField.setText("");
        methodField.setText("");
        resultField.setText("");
        presentationField.setText("");
        commentArea.setText("");
    }
}

