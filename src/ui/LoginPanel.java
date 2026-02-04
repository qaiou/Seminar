package ui;
import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel
{
    public LoginPanel(Main frame){
        setLayout(new GridLayout(4,2));

        String roleList[] = {"Student", "Evaluator", "Coordinator"};
        JComboBox roleSelect = new JComboBox<String>(roleList);
        JTextField userID = new JTextField();
        JTextField passw = new JTextField();
        JButton loginBtn = new JButton("Log in");

        add(new JLabel("Role", JLabel.CENTER));
        add(roleSelect);
        add(new JLabel("UserID ", JLabel.CENTER));
        add(userID);
        add(new JLabel("Password", JLabel.CENTER));
        add(passw);
        add(loginBtn);
        add(new JLabel());

        loginBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if ("Student".equals(roleSelect.getSelectedItem())){

                        //if() check student id dan

                        frame.switchPanel("STUDENT");
                    }
                    else if ("Evaluator".equals(roleSelect.getSelectedItem())){
                        frame.switchPanel("EVALUATOR");
                    }
                    else if ("Coordinator".equals(roleSelect.getSelectedItem())){
                        frame.switchPanel("COORDINATOR");
                    }
                    
                }
            }
        );
    }
}
