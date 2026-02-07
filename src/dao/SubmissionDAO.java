package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class SubmissionDAO {
    public boolean saveOrUpdate(Submission s) {
        String sql = """
            INSERT INTO submission
            (studentID, title, abstract, supervisor, type, file_path)
            VALUES (?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
              title=VALUES(title),
              abstract=VALUES(abstract),
              supervisor=VALUES(supervisor),
              type=VALUES(type),
              file_path=VALUES(file_path)
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getStudentId());
            ps.setString(2, s.getTitle());
            ps.setString(3, s.getAbstractText());
            ps.setString(4, s.getSupervisorName());
            ps.setString(5, s.getPresentationType());
            ps.setString(6, s.getFilePath());
            ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Submission getByStudent(String studentId) {
        String sql = "SELECT * FROM submission WHERE student_id=?";
        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Submission(
                        rs.getInt("submissionID"),
                        rs.getString("studentID"),
                        rs.getString("title"),
                        rs.getString("abstract"),
                        rs.getString("supervisor"),
                        rs.getString("ype"),
                        rs.getString("file_path"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Submission> getAll() {
        List<Submission> list = new ArrayList<>();
        String sql = "SELECT * FROM submission";

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Submission s = new Submission(
                        rs.getInt("submissionID"),
                        rs.getString("studentID"),
                        rs.getString("title"),
                        rs.getString("abstract"),
                        rs.getString("supervisor"),
                        rs.getString("type"),
                        rs.getString("file_path"),
                        rs.getString("status")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
