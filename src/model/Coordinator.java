package model;

public class Coordinator extends User {
    private String role = "Student";

    public Coordinator(String id, String name, String password) {
        super(id, name, password);
    }

    public String getRole() { return role; }
}
