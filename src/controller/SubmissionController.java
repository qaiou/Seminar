package controller;

import dao.SubmissionDAO;
import model.Submission;

public class SubmissionController {

    private SubmissionDAO dao = new SubmissionDAO();

    public void saveSubmission(String studentId, String title, String abs,
                               String supervisor, String type, String filePath) {
        dao.saveOrUpdate(new Submission(studentId, title, abs, supervisor, type, filePath));
    }

    public Submission getMySubmission(String studentId) {
        return dao.getByStudent(studentId);
    }

    public java.util.List<Submission> getAllSubmissions() {
        return dao.getAll();
    }
}
