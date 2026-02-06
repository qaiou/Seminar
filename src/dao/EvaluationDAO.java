package dao;

import model.Evaluation;
import model.Evaluator;
import model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluationDAO {

    public void saveEvaluation(Evaluation e) {
        String sql = """
            INSERT INTO evaluations
            (student_id, evaluator_id, session_id,
             clarity, methodology, results, presentation, comments)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getStudentId());
            stmt.setString(2, e.getEvaluatorId());
            stmt.setInt(3, e.getSessionId());
            stmt.setInt(4, e.getProblemClarity());
            stmt.setInt(5, e.getMethodology());
            stmt.setInt(6, e.getResults());
            stmt.setInt(7, e.getPresentation());
            stmt.setString(8, e.getComments());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean insertEvaluation(Evaluation e) {
        String sql = """
            INSERT INTO evaluation 
            (assignment_id, problem_clarity, methodology, results, presentation, comments, total_score)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getAssignmentId());
            ps.setInt(2, e.getProblemClarity());
            ps.setInt(3, e.getMethodology());
            ps.setInt(4, e.getResults());
            ps.setInt(5, e.getPresentation());
            ps.setString(6, e.getComments());
            ps.setInt(7, e.getTotalScore());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Evaluation> getEvaluationsByEvaluator(int evaluatorId) {
        List<Evaluation> list = new ArrayList<>();
        String sql = "SELECT * FROM evaluations WHERE evaluator_id=?";

        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, evaluatorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Evaluation(
                    rs.getString("student_id"),
                    rs.getString("evaluator_id"),
                    rs.getInt("session_id"),
                    rs.getInt("clarity"),
                    rs.getInt("methodology"),
                    rs.getInt("results"),
                    rs.getInt("presentation"),
                    rs.getString("comments")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    
}
