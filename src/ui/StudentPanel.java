package ui;

import controller.SubmissionController;
import model.Submission;
import app.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StudentPanel extends JPanel {

    private JTextField titleField;
    private JTextArea abstractArea;
    private JTextField supervisorField;
    private JComboBox<String> typeCombo;
    private JTextField fileField;

    private SubmissionController controller = new SubmissionController();
    private String studentId;

    public StudentPanel(Main frame, String studentId) {
        this.studentId = studentId;
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(6,2,10,10));
        titleField = new JTextField();
        abstractArea = new JTextArea(3,20);
        supervisorField = new JTextField();
        typeCombo = new JComboBox<>(new String[]{"Oral","Poster"});
        fileField = new JTextField();
        fileField.setEditable(false);

        JButton browseBtn = new JButton("Browse");
        JButton saveBtn = new JButton("Save / Update");

        browseBtn.addActionListener(e -> chooseFile());
        saveBtn.addActionListener(e -> save());

        form.add(new JLabel("Title:")); form.add(titleField);
        form.add(new JLabel("Abstract:")); form.add(new JScrollPane(abstractArea));
        form.add(new JLabel("Supervisor:")); form.add(supervisorField);
        form.add(new JLabel("Presentation Type:")); form.add(typeCombo);
        form.add(new JLabel("File:"));
        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(fileField, BorderLayout.CENTER);
        filePanel.add(browseBtn, BorderLayout.EAST);
        form.add(filePanel);
        form.add(new JLabel()); form.add(saveBtn);

        add(new JLabel("Student Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);

        loadExisting();
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            fileField.setText(f.getAbsolutePath());
        }
    }

    private void save() {
        controller.saveSubmission(
                studentId,
                titleField.getText(),
                abstractArea.getText(),
                supervisorField.getText(),
                (String) typeCombo.getSelectedItem(),
                fileField.getText()
        );
        JOptionPane.showMessageDialog(this, "Submission saved.");
    }

    private void loadExisting() {
        Submission s = controller.getMySubmission(studentId);
        if (s != null) {
            titleField.setText(s.getTitle());
            abstractArea.setText(s.getAbstractText());
            supervisorField.setText(s.getSupervisorName());
            typeCombo.setSelectedItem(s.getPresentationType());
            fileField.setText(s.getFilePath());
        }
    }
}
