package model;

public class Evaluator extends User{
    private String role = "Evaluator";

    public Evaluator(String id, String name, String password) {
        super(id, name, password);
    }

    public Evaluator(String id, String name) {
        super(id, name);
    }


    public String getRole() { return role; }

}
