package dao;

import model.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    public boolean insertAssignment(Assignment a) {
        String sql = """
            INSERT INTO assignment (sessionID, studentID, evaluatorID, status)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getSessionId());
            ps.setString(2, a.getStudentId());
            ps.setString(3, a.getEvaluatorId());
            ps.setString(4, a.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Assignment> getByEvaluator(String evaluatorId) {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT * FROM assignment WHERE evaluatorID = ?";

        try (Connection con = DBConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, evaluatorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Assignment a = new Assignment(
                    rs.getInt("assignmentID"),
                    rs.getInt("sessionID"),
                    rs.getString("submissionID"),
                    rs.getString("evaluatorID"),
                    rs.getString("status")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
