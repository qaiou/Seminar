package dao;

import model.User;
import java.sql.*;

public class UserDAO {

    public User login(String userId, String password, String role) {
        String sql = "SELECT * FROM users WHERE id=? AND password=? AND role=?";

        try (Connection conn = DBConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("id"),
                    rs.getString("role"),
                    rs.getString("name"),
                    rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login failed
    }
}
