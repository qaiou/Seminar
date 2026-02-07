package controller;

import java.util.List;

import dao.StudentDAO;
import dao.SubmissionDAO;
import model.Student;
import model.Submission;

public class StudentContr {

    private StudentDAO studentDAO = new StudentDAO();
    private SubmissionDAO submissionDAO = new SubmissionDAO();

    // Submit research
    public boolean submitResearch(Submission submission) {
        return submissionDAO.saveOrUpdate(submission);
    }

    public List<Submission> getAllStudents() {
        return submissionDAO.getAll();
    }

    public List<Student> getAllS() {
        return studentDAO.getAll();
    }


}
