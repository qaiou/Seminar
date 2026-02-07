package ui;

import controller.EvaluationContr;
import model.Submission;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorPanel extends JPanel {

    private JTextField txtAssignmentID;
    private JTextArea txtSubmissionDetails;
    
    private JTextField txtProblemClarity;
    private JTextField txtMethodology;
    private JTextField txtResults;
    private JTextField txtPresentation;
    private JTextArea txtComments;
    
    private JButton btnLoad;
    private JButton btnSubmit;
    
    private EvaluationContr controller;

    public EvaluatorPanel() {
        controller = new EvaluationContr();
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Assignment ID:"));
        txtAssignmentID = new JTextField(10);
        topPanel.add(txtAssignmentID);
        btnLoad = new JButton("Load Submission");
        topPanel.add(btnLoad);
        add(topPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Submission Details"));
        txtSubmissionDetails = new JTextArea();
        txtSubmissionDetails.setEditable(false);
        detailsPanel.add(new JScrollPane(txtSubmissionDetails), BorderLayout.CENTER);
        centerPanel.add(detailsPanel);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Evaluation Form"));
        
        formPanel.add(new JLabel("Problem Clarity (0-10):"));
        txtProblemClarity = new JTextField();
        formPanel.add(txtProblemClarity);
        
        formPanel.add(new JLabel("Methodology (0-10):"));
        txtMethodology = new JTextField();
        formPanel.add(txtMethodology);
        
        formPanel.add(new JLabel("Results (0-10):"));
        txtResults = new JTextField();
        formPanel.add(txtResults);
        
        formPanel.add(new JLabel("Presentation (0-10):"));
        txtPresentation = new JTextField();
        formPanel.add(txtPresentation);
        
        formPanel.add(new JLabel("Comments:"));
        txtComments = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(txtComments));
        
        btnSubmit = new JButton("Submit Evaluation");
        formPanel.add(new JLabel("")); 
        formPanel.add(btnSubmit);
        
        centerPanel.add(formPanel);
        add(centerPanel, BorderLayout.CENTER);
        
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSubmission();
            }
        });
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitEvaluation();
            }
        });
    }

    private void loadSubmission() {
        try {
            int assignmentID = Integer.parseInt(txtAssignmentID.getText());
            Submission sub = controller.getSubmissionDetails(assignmentID);
            
            if (sub != null) {
                txtSubmissionDetails.setText(sub.getDetails());
            } else {
                JOptionPane.showMessageDialog(this, "Assignment not found.");
                txtSubmissionDetails.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Assignment ID.");
        }
    }

    private void submitEvaluation() {
        try {
            int assignmentID = Integer.parseInt(txtAssignmentID.getText());
            int p1 = Integer.parseInt(txtProblemClarity.getText());
            int p2 = Integer.parseInt(txtMethodology.getText());
            int p3 = Integer.parseInt(txtResults.getText());
            int p4 = Integer.parseInt(txtPresentation.getText());
            String comments = txtComments.getText();
            
            boolean success = controller.submitEvaluation(assignmentID, p1, p2, p3, p4, comments);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Evaluation Saved Successfully!");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving evaluation.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please check your inputs.");
        }
    }
    
    private void clearForm() {
        txtAssignmentID.setText("");
        txtSubmissionDetails.setText("");
        txtProblemClarity.setText("");
        txtMethodology.setText("");
        txtResults.setText("");
        txtPresentation.setText("");
        txtComments.setText("");
    }
}