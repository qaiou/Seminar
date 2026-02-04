package dao;

import java.sql.*;

public class DBConnect {
    static String url = "jdbc:mysql://localhost:3306/seminar";
    static String username = "root";
    static String password = "";

    public static Connection getConnect(){
        
        try{
            return DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
}
