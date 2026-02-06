package ui;
import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentPanel extends JPanel
{
    private JTextField titleField;
    private JTextArea abstractArea;
    
    public StudentPanel(Main frame){
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(3,3,10,10));
        
        titleField = new JTextField();
        abstractArea = new JTextArea(4,20); //range of text. 4 lines, 20 row of words
        
        JButton uploadBtn = new JButton("Upload Materials");
        JButton submitBtn = new JButton("Submit");
        JButton backBtn = new JButton("Back");
        
        uploadBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFileChooser fileChooser = new JFileChooser();
                    
                    fileChooser.showOpenDialog(StudentPanel.this);
                    File file = fileChooser.getSelectedFile();
                    System.out.println("Submitted "+ file.getName());
                }
            }
        ); //trigger dialog box to select file
        
        backBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.switchPanel("LOGIN");
                }
            }
        );
        
        formPanel.add(new JLabel("ResearchTitle:", JLabel.CENTER));
        formPanel.add(titleField);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel("Abstract:", JLabel.CENTER));
        formPanel.add(new JScrollPane(abstractArea));
        formPanel.add(new JLabel());
        formPanel.add(backBtn);
        formPanel.add(uploadBtn);
        formPanel.add(submitBtn);
        
        add(new JLabel("Student Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
    
}
