package com.ideas2it.employee.dao.EmployeeManagementDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;

/**
 *
 *
 *
 */
public class Factory {

    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/employee_management_application";
    private static final String user = "root";
    private static final String password = "Naveen@1998";

    private Factory() {}

    public static Connection getConnection() {
        
        try {
            if (connection == null || connection.isClosed())
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Invalid user/password. Try again");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
}