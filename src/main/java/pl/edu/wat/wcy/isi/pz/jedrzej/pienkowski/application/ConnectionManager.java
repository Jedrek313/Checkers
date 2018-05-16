package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Jedrzej Pienkowski on 16.03.2018.
 */
public class ConnectionManager {

    private static String url;
    private static String username;
    private static String password;
    private static Connection con;

    public static Connection getConnection() {

        ResourceBundle rb = ResourceBundle.getBundle("configuration");
        url = rb.getString("Database.url");
        //private static String driverName = "com.mysql.jdbc.Driver";
        username = rb.getString("Database.username");
        password = rb.getString("Database.password");

        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            // log an exception. fro example:
            System.out.println("Failed to create the database connection.");
        }
        return con;
    }
}
