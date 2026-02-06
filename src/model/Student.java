package model;

public class Student extends User {
    private String role = "Student";

    public Student(String id, String name, String password) {
        super(id, name, password);
    }

     public Student(String id, String name) {
        super(id, name);
    }


    public String getRole() { return role; }

}
