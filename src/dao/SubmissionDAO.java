package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class SubmissionDAO {
    public boolean saveOrUpdate(Submission s) {
        String sql = """
            INSERT OR REPLACE INTO submission
            (studentID, title, abstract, supervisor, type, file_path, status)
            VALUES (?, ?, ?, ?, ?, ?, 'Submitted')
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getStudentId());
            ps.setString(2, s.getTitle());
            ps.setString(3, s.getAbstractText());
            ps.setString(4, s.getSupervisorName());
            ps.setString(5, s.getPresentationType());
            ps.setString(6, s.getFilePath());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Submission getByStudent(String studentId) {
        String sql = "SELECT * FROM submission WHERE studentID=?";
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
                        rs.getString("type"),
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

    public int count() {
        String sql = "SELECT COUNT(*) FROM submission";
        try (Connection con = DBConnect.getConnect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
