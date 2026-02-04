package model;

public class Student extends User {
    private Submission submission;

    public Student(String id, String role, String name, String password) {
        super(id, role, name, password);
    }

}
