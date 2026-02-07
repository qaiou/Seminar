package app;
import ui.LoginPanel;
import ui.StudentPanel;
import ui.EvaluatorPanel;
import ui.CoordinatorPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Main(){
        setTitle("Research Submission System");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //center no matter what device
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        //panels
        mainPanel.add(new LoginPanel(this), "LOGIN");
        //mainPanel.add(new StudentPanel(this), "STUDENT");
        //mainPanel.add(new EvaluatorPanel(), "EVALUATOR");
        mainPanel.add(new CoordinatorPanel(), "COORDINATOR");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    public void switchPanel(String panelName){
        cardLayout.show(mainPanel, panelName);
    }
    public void showStudentPanel(String studentId) {
        mainPanel.add(new StudentPanel(this, studentId), "STUDENT");
        cardLayout.show(mainPanel, "STUDENT");
    }

    public void showEvaluatorPanel(String evaluatorId) {
        mainPanel.add(new EvaluatorPanel(evaluatorId), "EVALUATOR");
        cardLayout.show(mainPanel, "EVALUATOR");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Main().setVisible(true));
    }
}
