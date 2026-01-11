import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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

class LoginPanel extends JPanel
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

class StudentPanel extends JPanel
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
        
        formPanel.add(new JLabel("ResearchTitle:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel("Abstract:"));
        formPanel.add(new JScrollPane(abstractArea));
        formPanel.add(new JLabel());
        formPanel.add(backBtn);
        formPanel.add(uploadBtn);
        formPanel.add(submitBtn);
        
        add(new JLabel("Student Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
    
}


class EvaluatorPanel extends JPanel
{
    public EvaluatorPanel(){
        setLayout(new BorderLayout());
        add(new JLabel("Evaluator Dashboard", JLabel.CENTER));
        
        JTextArea evaluationArea = new JTextArea("Evaluation form goes here...");
        add(new JScrollPane(evaluationArea), BorderLayout.CENTER);
    }
}

class CoordinatorPanel extends JPanel
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