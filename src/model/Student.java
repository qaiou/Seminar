package model;

public class Student extends User {
    private Submission submission;
    private String role = "Student";

    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    public String getRole() { return role; }

}
