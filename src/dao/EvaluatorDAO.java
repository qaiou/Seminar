package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Evaluator;

public class EvaluatorDAO {
    public List<Evaluator> getAll() {
        List<Evaluator> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'evaluator' ORDER BY userID ";

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evaluator s = new Evaluator(
                        rs.getString("userID"),
                        rs.getString("name")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
