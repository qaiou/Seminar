package ui;

import javax.swing.*;
import java.awt.*;

public class EvaluatorPanel extends JPanel
{
    public EvaluatorPanel(){
        setLayout(new BorderLayout());
        add(new JLabel("Evaluator Dashboard", JLabel.CENTER));
        
        JTextArea evaluationArea = new JTextArea("Evaluation form goes here...");
        add(new JScrollPane(evaluationArea), BorderLayout.CENTER);
    }
}

