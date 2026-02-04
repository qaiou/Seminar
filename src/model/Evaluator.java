package model;
import java.util.*;

public class Evaluator extends User{
    private ArrayList<Evaluation> evaluation = new ArrayList<Evaluation>();

    public Evaluator(String id, String role, String name, String password) {
        super(id, role, name, password);
    }
}
