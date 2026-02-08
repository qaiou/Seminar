package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    
    public static void initializeDatabase() {
        try {
            // Read the SQL script
            String sqlScript = new String(Files.readAllBytes(Paths.get("init_db.sql")));
            
            // Get connection
            Connection conn = DBConnect.getConnect();
            if (conn == null) {
                System.err.println("Failed to get database connection");
                return;
            }
            
            // Split by semicolon and execute each statement
            String[] statements = sqlScript.split(";");
            Statement stmt = conn.createStatement();
            
            for (String sql : statements) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    try {
                        stmt.execute(sql);
                        System.out.println("Executed: " + sql.substring(0, Math.min(50, sql.length())) + "...");
                    } catch (Exception e) {
                        System.err.println("Error executing: " + sql.substring(0, Math.min(50, sql.length())));
                        e.printStackTrace();
                    }
                }
            }
            
            stmt.close();
            conn.close();
            System.out.println("Database initialized successfully!");
            
        } catch (IOException e) {
            System.err.println("Error reading SQL script:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error initializing database:");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        initializeDatabase();
    }
}
