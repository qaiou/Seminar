package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.Submission;

public class SubmissionDAO {
    public List<Submission> getByStudentId(String studentId) {
        List<Submission> list = new ArrayList<>();
        String sql = "SELECT * FROM submission WHERE studentID = ?";

        try (Connection con = DBConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Submission(
                    
                    rs.getString("studentID"),
                    rs.getString("title"),
                    rs.getString("abstract"),
                    rs.getString("supervisor"),
                    rs.getString("type"),
                    rs.getString("file_Path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertSubmission(Submission e) {
        String sql = """
            INSERT INTO submission 
            (submissionID, studentID, title, abstract, supervisor, type, file_path)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(2, e.getStudentId());
            ps.setString(3, e.getTitle());
            ps.setString(4, e.getAbstractText());
            ps.setString(5, e.getSupervisorName());
            ps.setString(6, e.getPresentationType());
            ps.setString(7, e.getFilePath());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
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
