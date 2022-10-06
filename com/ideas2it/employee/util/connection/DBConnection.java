package com.ideas2it.employee.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;

/**
 * Provides connection between database and application.
 * With this connection we can manipulate the data in database.
 * 
 * @version 2.0 28-09-2022.
 * @author  Naveenkumar R.
 */
public class DBConnection {

    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/employee_management_application";
    private static final String user = "root";
    private static final String password = "Naveen@1998";

    private DBConnection() {}

    public static Connection getConnection() {
        
        try {
            if (connection == null || connection.isClosed())
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver class");
        } catch ( SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void closeConnection() {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           System.out.println(e);
       }
    }
}