package ui;

import javax.swing.*;
import java.awt.*;

public class CoordinatorPanel extends JPanel
{
    public CoordinatorPanel(){
        setLayout(new BorderLayout(10,40));
        
        String[] columns = {"Session", "Student", "Evaluator", "Status"};
        Object[][] data = {
            {"S1", "Ali", "Dr. Lim", "Under Review"},
            {"S2", "Aisyah", "Dr. Tan", "Completed"}
        };
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(new JLabel("Coordinator Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}