package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Evaluation;

public class EvaluatorDAO {

    public boolean saveEvaluation(Evaluation evaluation) {
        String query = "INSERT INTO evaluation (assignmentID, problem_clarity, methodology, results, presentation, comments, total_score) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, evaluation.getAssignmentID());
            stmt.setInt(2, evaluation.getProblemClarity());
            stmt.setInt(3, evaluation.getMethodology());
            stmt.setInt(4, evaluation.getResults());
            stmt.setInt(5, evaluation.getPresentation());
            stmt.setString(6, evaluation.getComments());
            stmt.setInt(7, evaluation.getTotalScore());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}