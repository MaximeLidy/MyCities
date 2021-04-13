package app;

import java.sql.*;

public class DBConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn =DriverManager.getConnection("jdbc:sqlite:BDD.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
            // TODO: handle exception
        }
    }
}