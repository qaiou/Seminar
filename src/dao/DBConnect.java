package dao;

import java.sql.*;

public class DBConnect {

    public DBConnect(){
        String url = "db:mysql://localhost3306/dbconnect";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.sql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
