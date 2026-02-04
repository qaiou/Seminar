package model;
import java.util.ArrayList;

public class Coordinator extends User {
    private ArrayList<Session> session = new ArrayList<Session>();
    private String role = "Student";

    public Coordinator(String id, String name, String password) {
        super(id, name, password);
    }

    public String getRole() { return role; }
}
