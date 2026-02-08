package dao;

import model.Session;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    // CREATE
    public boolean insert(Session s) {
        String sql = """
            INSERT INTO session (session_date, venue, session_type, status)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // store date as ISO string (SQLite stores dates as text)
            ps.setString(1, s.getSessionDate());
            ps.setString(2, s.getVenue());
            ps.setString(3, s.getSessionType());
            ps.setString(4, s.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // READ ALL
    public List<Session> getAll() {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM session ORDER BY session_date";

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Session s = new Session(
                    rs.getInt("sessionID"),
                    rs.getString("session_date"),
                    rs.getString("venue"),
                    rs.getString("session_type"),
                    rs.getString("status")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ BY ID
    public Session getById(int id) {
        String sql = "SELECT * FROM session WHERE sessionID = ?";
        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Session(
                    rs.getInt("sessionID"),
                    rs.getString("session_date"),
                    rs.getString("venue"),
                    rs.getString("session_type"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public boolean update(Session s) {
        String sql = """
            UPDATE session 
            SET session_date=?, venue=?, session_type=?, status=?
            WHERE sessionID=?
        """;

        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // store date as ISO string
            ps.setString(1, s.getSessionDate());
            ps.setString(2, s.getVenue());
            ps.setString(3, s.getSessionType());
            ps.setString(4, s.getStatus());
            ps.setInt(5, s.getSessionId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM session WHERE sessionID=?";
        try (Connection con = DBConnect.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM session";
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
