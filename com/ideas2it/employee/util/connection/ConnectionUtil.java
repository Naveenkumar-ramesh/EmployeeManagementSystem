package com.ideas2it.employee.util.connection;

import com.ideas2it.employee.exception.EMSException;

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
public class ConnectionUtil {

    private static Connection connection = null;
    private static ConnectionUtil dbConnection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/employee_management_application";
    private static final String user = "root";
    private static final String password = "Naveen@1998";

    private ConnectionUtil() {}

    /**
     * Creates connection between database and application.
     * @return dbconnection
     */
    public static ConnectionUtil getDBConnection() {
        if (dbConnection == null) {
            dbConnection = new ConnectionUtil();
        }
        return dbConnection;
    }

    /**
     * Creates connection between database and application.
     */
    public Connection getConnection() throws EMSException {

        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, user, password);
            }
        } catch (ClassNotFoundException e) {
            throw new EMSException
            ("Cannot connect database", "ErrorCode 107");
        } catch (SQLException e) { 
            throw new EMSException
            ("Cannot connect database", "ErrorCode 107");
        }
        return connection;
    }

    /**
     * Closes connection between database and application.
     */
    public void closeConnection() throws EMSException {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) { 
            throw new EMSException
            ("Error occured in inserting data, Try again", "ErrorCode 101");
       }
    }
}