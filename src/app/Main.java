package app;
import ui.*;

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
        mainPanel.add(new StudentPanel(this), "STUDENT");
        mainPanel.add(new EvaluatorPanel(), "EVALUATOR");
        mainPanel.add(new CoordinatorPanel(), "COORDINATOR");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    public void switchPanel(String panelName){
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Main().setVisible(true));
    }
}
