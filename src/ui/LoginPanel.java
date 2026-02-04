package ui;
import app.Main;
import controller.AuthController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel
{
    private AuthController authController = new AuthController();

    public LoginPanel(Main frame){
        setLayout(new GridLayout(4,2));

        String roleList[] = {"Student", "Evaluator", "Coordinator"};
        JComboBox<String> roleSelect = new JComboBox<>(roleList);
        JTextField userID = new JTextField();
        JPasswordField passw = new JPasswordField();
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
                    String role = roleSelect.getSelectedItem().toString();
                    String uid = userID.getText();
                    String pwd = new String(passw.getPassword());

                    User user = authController.authenticate(uid, pwd, role);

                    if (user != null) {
                        if (role.equals("Student")) frame.switchPanel("STUDENT");
                        else if (role.equals("Evaluator")) frame.switchPanel("EVALUATOR");
                        else frame.switchPanel("COORDINATOR");
                    } else {
                        JOptionPane.showMessageDialog(loginBtn, "Invalid login!");;
                    }
                }
            }
        );
    }
}

/*
if ("Student".equals(roleSelect.getSelectedItem())){

                        //if() check student id dan

                        frame.switchPanel("STUDENT");
                    }
                    else if ("Evaluator".equals(roleSelect.getSelectedItem())){
                        frame.switchPanel("EVALUATOR");
                    }
                    else if ("Coordinator".equals(roleSelect.getSelectedItem())){
                        frame.switchPanel("COORDINATOR");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid login!");
                    }
*/