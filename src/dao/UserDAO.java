package dao;

import model.Student;
import model.*;
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
                if (role == "Student"){
                    return new Student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password")
                    ); 
                } else if (role == "Evaluator"){
                    return new Evaluator(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password")
                    );
                } else if (role == "Coordinator"){
                    return new Coordinator(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password")
                    );
                }
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login failed
    }
}
