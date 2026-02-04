package model;
import java.util.*;

public class Evaluator extends User{
    private ArrayList<Evaluation> evaluation = new ArrayList<Evaluation>();
    private String role = "Student";

    public Evaluator(String id, String name, String password) {
        super(id, name, password);
    }

    public String getRole() { return role; }

}
