package model;

public class Student extends User {
    private Submission submission;

    public Student(String n, String pw, String e){
        super(n, pw, e);
    }

    public String getRole(){
        return "Student";
    }

}
