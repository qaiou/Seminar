package controller;

import dao.*;
import model.*;
import java.util.*;

public class ReportController {

    private SubmissionDAO submissionDAO = new SubmissionDAO();
    private SessionDAO sessionDAO = new SessionDAO();
    private EvaluationDAO evaluationDAO = new EvaluationDAO();

    public String generateSummaryReport() {
        int totalSubmissions = submissionDAO.count();
        int totalSessions = sessionDAO.count();
        double avgScore = evaluationDAO.getOverallAverageScore();

        return """
            SEMINAR SUMMARY REPORT
            -----------------------
            Total Submissions: %d
            Total Sessions: %d
            Average Evaluation Score: %.2f
            """.formatted(totalSubmissions, totalSessions, avgScore);
    }

    public String exportCSV() {
        List<Submission> list = submissionDAO.getAll();
        StringBuilder sb = new StringBuilder("SubmissionID,Title,StudentID,Type,Status\n");

        for (Submission s : list) {
            sb.append(s.getSubmissionId()).append(",")
              .append(s.getTitle()).append(",")
              .append(s.getStudentId()).append(",")
              .append(s.getPresentationType()).append(",")
              .append(s.getStatus()).append("\n");
        }
        return sb.toString();
    }
}
