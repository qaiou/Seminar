package controller;

import dao.DBConnect;
import dao.EvaluatorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Evaluation;
import model.Submission;

public class EvaluationContr {

    private EvaluatorDAO evaluatorDAO;

    public EvaluationContr() {
        this.evaluatorDAO = new EvaluatorDAO();
    }

    public Submission getSubmissionDetails(int assignmentID) {
        Submission submission = null;
        String query = "SELECT s.id, s.title, s.abstract, s.supervisor, s.type, s.file_path, a.status " +
                       "FROM submission s " +
                       "JOIN assignment a ON s.id = a.submissionID " +
                       "WHERE a.assignID = ?";

        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, assignmentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                submission = new Submission(
                    0,
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("abstract"),
                    rs.getString("supervisor"),
                    rs.getString("type"),
                    rs.getString("file_path"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submission;
    }

    public boolean submitEvaluation(int assignmentID, int p1, int p2, int p3, int p4, String comments) {
        Evaluation eval = new Evaluation(assignmentID, p1, p2, p3, p4, comments);
        boolean isSaved = evaluatorDAO.saveEvaluation(eval);
        if (isSaved) {
            updateAssignmentStatus(assignmentID);
            return true;
        }
        return false;
    }

    private void updateAssignmentStatus(int assignmentID) {
        String query = "UPDATE assignment SET status = 'Completed' WHERE assignID = ?";
        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignmentID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}