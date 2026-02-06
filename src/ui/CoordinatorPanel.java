package ui;

import controller.AssignmentContr;
import dao.SessionDAO;
//import model.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoordinatorPanel extends JPanel {
    private AssignmentContr controller;
    private JTable table;
    private JTextField sessionIdField, presenterField, evaluatorField;
    private JTextField newSessionIdField, dateField, venueField, typeField;
    private JButton createSessionBtn, assignPresenterBtn, assignEvaluatorBtn;

    public CoordinatorPanel() {
        setLayout(new BorderLayout(10, 40));

        // Initialize DAO + Controller
        SessionDAO dao = new SessionDAO();
        controller = new AssignmentContr(dao);

        // Example data (could be loaded from DAO later)
        String[] columns = {"Session", "Student", "Evaluator", "Status"};
        Object[][] data = {
            {"S1", "Ali", "Dr. Lim", "Under Review"},
            {"S2", "Aisyah", "Dr. Tan", "Completed"}
        };

        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        sessionIdField = new JTextField();
        presenterField = new JTextField();
        evaluatorField = new JTextField();

        inputPanel.add(new JLabel("Session ID:"));
        inputPanel.add(sessionIdField);
        inputPanel.add(new JLabel("Presenter ID:"));
        inputPanel.add(presenterField);
        inputPanel.add(new JLabel("Evaluator ID:"));
        inputPanel.add(evaluatorField);

        // Input fields for creating a new session
        JPanel createPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        newSessionIdField = new JTextField();
        dateField = new JTextField();
        venueField = new JTextField();
        typeField = new JTextField();
        createSessionBtn = new JButton("Create Session");

        createPanel.add(new JLabel("New Session ID:"));
        createPanel.add(newSessionIdField);
        createPanel.add(new JLabel("Date:"));
        createPanel.add(dateField);
        createPanel.add(new JLabel("Venue:"));
        createPanel.add(venueField);
        createPanel.add(new JLabel("Type (Oral/Poster):"));
        createPanel.add(typeField);
        createPanel.add(createSessionBtn);

        // Buttons
        assignPresenterBtn = new JButton("Assign Presenter");
        assignEvaluatorBtn = new JButton("Assign Evaluator");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(assignPresenterBtn);
        buttonPanel.add(assignEvaluatorBtn);

        // Add components to layout
        add(new JLabel("Coordinator Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.EAST);
        add(createPanel, BorderLayout.WEST);

        // Event handling
        assignPresenterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sessionId = Integer.parseInt(sessionIdField.getText());
                String presenterId = presenterField.getText();
                controller.assignPresenter(sessionId, presenterId);
                JOptionPane.showMessageDialog(null, "Presenter assigned!");
            }
        });

        assignEvaluatorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sessionId = Integer.parseInt(sessionIdField.getText());
                String evaluatorId = evaluatorField.getText();
                controller.assignEvaluator(sessionId, evaluatorId);
                JOptionPane.showMessageDialog(null, "Evaluator assigned!");
            }
        });

        createSessionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sessionId = Integer.parseInt(newSessionIdField.getText());
                String date = dateField.getText();
                String venue = venueField.getText();
                String type = typeField.getText();

                controller.createSession(sessionId, date, venue, type);
                JOptionPane.showMessageDialog(null, "Session created successfully!");
            }
        });
    }
}
