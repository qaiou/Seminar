package dao;

import model.Award;
import java.sql.*;
import java.util.*;

public class AwardDAO {

    public void insert(Award a) {
        String sql = "INSERT INTO award(submission_id, award_type, score) VALUES (?, ?, ?)";

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getSubmissionId());
            ps.setString(2, a.getAwardType());
            ps.setInt(3, a.getScore());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Award> getAll() {
        List<Award> list = new ArrayList<>();
        String sql = "SELECT * FROM award";

        try (Connection con = DBConnect.getConnect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Award(
                        rs.getInt("award_id"),
                        rs.getInt("submission_id"),
                        rs.getString("award_type"),
                        rs.getInt("score")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
