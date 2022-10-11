package com.ideas2it.employee.dao.EmployeeManagementDao;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.util.connection.ConnectionUtil;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.exception.EMSException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements transfer of employee details from database and
 * provides the data to be displayed.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeDao implements Dao {
    ConnectionUtil dbConnection = ConnectionUtil.getDBConnection();
    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public boolean addEmployee(Employee employee) throws EMSException {

        boolean isAdded = false;
        PreparedStatement preparedStatement = null;
        Connection connection = dbConnection.getConnection();
        int count = 0;

        try {
            String query =("insert into employee(first_name, last_name, "
                              + "email, phone_number, salary,date_of_joining, "
                              + " date_of_birth, gender, role) "
                              + " values(?,?,?,?,?,?,?,?,?)");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setLong(4,employee.getPhoneNumber());
            preparedStatement.setDouble(5,employee.getSalary());
            preparedStatement.setDate(6, Date.valueOf(employee.
                                                      getDateOfJoining()));
            preparedStatement.setDate(7, Date.valueOf(employee.
                                                      getDateOfBirth()));
            preparedStatement.setString(8,employee.getGender());
            preparedStatement.setString(9,employee.getRole());
            count = preparedStatement.executeUpdate();
            query = ("select employee_id from employee where email = ?");
            PreparedStatement statementId = connection.prepareStatement(query);
            statementId.setString(1, employee.getEmail());
            ResultSet result = statementId.executeQuery();       
            int employeeid = 0;

            while (result.next()) {
                employeeid = result.getInt(1);
            }
            isAdded = addAddress(employee.getAddresses(), employeeid); 
        } catch (SQLException e) { 
            throw new EMSException
            ("Error occured in inserting data, Try again", "ErrorCode 101");
        } finally {
            dbConnection.closeConnection();
        }  
        if (count > 0 && isAdded) {
            isAdded = true;
        }
        return isAdded;  
    }

    /**
     * Saves the address details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public boolean addAddress(List<Address> addresses, int employeeid) throws EMSException {
        boolean isAdded= false;
        PreparedStatement preparedStatement = null;
        Connection connection = dbConnection.getConnection();
        int count = 0;

        try {
            for (Address address : addresses) {
                String query = ("insert into address(employee_id, door_number,"
                                + "street, city, state, pin_code, type)"
                                + "values(?,?,?,?,?,?,?)");
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, employeeid);
                preparedStatement.setString(2,address.getDoorNumber());
                preparedStatement.setString(3,address.getStreet());
                preparedStatement.setString(4,address.getCity());
                preparedStatement.setString(5,address.getState());
                preparedStatement.setInt(6,address.getPinCode());
                preparedStatement.setString(7,address.getType());
                count = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured in inserting data, Try again", "ErrorCode 101");
        } finally {
           dbConnection.closeConnection(); 
        }  
        if (count > 0) {
            isAdded = true;
        }
        return isAdded;    
    }

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<Employee> displayEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        Connection connection = dbConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select employee_id, first_name, last_name, email,")
             .append("phone_number, salary, date_of_joining,")
             .append(" date_of_birth, gender, role ")
             .append(" from employee ");
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());

            while (result.next()) {
                int employeeId = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String email = result.getString(4);
                long phoneNumber = result.getLong(5);
                double salary = result.getDouble(6);
                LocalDate dateOfJoining = result.getDate(7).toLocalDate();
                LocalDate dateOfBirth = result.getDate(8).toLocalDate();
                String gender = result.getString(9);
                String role = result.getString(10);

                Statement statement1 = connection.createStatement();
                String querys = "select door_number, street, city, state," +
                                " pin_code, type from address where " +
                                "employee_id = " + employeeId ;
                ResultSet resultAddress = statement1.executeQuery(querys);
                List<Address> addresses = new ArrayList();
                while (resultAddress.next()) {
                    Address address = new Address();
                    String doorNumber = resultAddress.getString(1);
                    String street = resultAddress.getString(2);
                    String city = resultAddress.getString(3);
                    String state = resultAddress.getString(4);
                    int pinCode = resultAddress.getInt(5);
                    String type = resultAddress.getString(6);

                    address = new Address(doorNumber, street, city,
                                                  state, pinCode, type);
                    addresses.add(address);
                }

                Employee employee = new Employee(employeeId, firstName,
                                     lastName, email, phoneNumber, salary,
                                     dateOfJoining, addresses, dateOfBirth,
                                     gender, role);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured while retreving data, Try again", "ErrorCode 102");
        } finally {
            dbConnection.closeConnection();
        }
        return employees;  
    }


    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public boolean updateEmployee(Employee employee) throws EMSException {
        boolean isUpdated = false;
        PreparedStatement preparedStatement = null;
        Connection connection = dbConnection.getConnection();
        int count = 0;
        int employeeid = employee.getEmployeeId();
        StringBuilder query = new StringBuilder();
        query.append("update employee set first_name = ?, last_name = ?,")
             .append(" email = ?, phone_number = ?,salary = ?, ")
             .append(" date_of_joining = ?,date_of_birth = ?, gender = ?,")
             .append("  role = ? where employee_id = ")
             .append(employeeid);

        try {
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setLong(4,employee.getPhoneNumber());
            preparedStatement.setDouble(5,employee.getSalary());
            preparedStatement.setDate(6, Date.valueOf(employee.
                                         getDateOfJoining()));
            preparedStatement.setDate(7, Date.valueOf(employee.
                                         getDateOfBirth()));
            preparedStatement.setString(8,employee.getGender());
            preparedStatement.setString(9,employee.getRole());
            count = preparedStatement.executeUpdate();

            isUpdated = updateAddress(employee.getAddresses(), employeeid);
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured while updating data, Try again", "ErrorCode 103");
        } finally{
            dbConnection.closeConnection();  
        }     
        if (count > 0 && isUpdated) {
            isUpdated = true;
        }  
        return isUpdated;
    }

    /**
     * Updates the address detail and returns true if successful.
     *
     * @param employeeid
     * @return true if address is updated
     */
    public boolean updateAddress(List<Address> addresses, int employeeid) throws EMSException {
        boolean isUpdate= false;
        int count = 0;
        Connection connection = dbConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("update address set door_number = ?, street = ?,") 
             .append("city = ?, state = ?, pin_code = ?, type = ?")
             .append(" where employee_id = ")
             .append(employeeid);

        try {
            for (Address address : addresses) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                                                      query.toString());
                preparedStatement.setString(1, address.getDoorNumber());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getState());
                preparedStatement.setInt(5, address.getPinCode());
                preparedStatement.setString(6, address.getType());
                count = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured while updating data, Try again", "ErrorCode 103");
        } finally {
           dbConnection.closeConnection(); 
        }
        if (count > 0) {
            isUpdate = true;
        }
        return isUpdate;   
    }

    /**
     * Search the employee detail and returns the employee details.
     *
     * @param firstName
     * @return employee details wiith address.
     */
    public List<Employee> searchEmployee(String firstName) throws EMSException {

        List<Employee> employees = new ArrayList();
        Connection connection = dbConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select employee_id, first_name, last_name, email,")
             .append(" phone_number, salary, date_of_joining,")
             .append(" date_of_birth, gender, role from employee")
             .append(" where first_name like '")
             .append(firstName).append("%'");

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());
            while (result.next()) {
                int employeeId = result.getInt(1);
                firstName = result.getString(2);
                String lastName = result.getString(3);
                String email = result.getString(4);
                long phoneNumber = result.getLong(5);
                double salary = result.getDouble(6);
                LocalDate dateOfJoining = result.getDate(7).toLocalDate();
                LocalDate dateOfBirth = result.getDate(8).toLocalDate();
                String gender = result.getString(9);
                String role = result.getString(10);
                Statement statement1 = connection.createStatement();
                String querys = "select door_number, street, city, state," + 
                               " pin_code, type from address where " +
                               "employee_id = " + employeeId;
                ResultSet resultAddress = statement1.executeQuery(querys);
                List<Address> addresses = new ArrayList();
                while (resultAddress.next()) {
                    Address address = new Address();
                    String doorNumber = resultAddress.getString(1);
                    String street = resultAddress.getString(2);
                    String city = resultAddress.getString(3);
                    String state = resultAddress.getString(4);
                    int pinCode = resultAddress.getInt(5);
                    String type = resultAddress.getString(6);

                    address = new Address(doorNumber, street, city,
                                                  state, pinCode, type);
                    addresses.add(address);
                }
                Employee employee = new Employee(employeeId, firstName,
                                     lastName, email, phoneNumber, salary,
                                     dateOfJoining, addresses, dateOfBirth,
                                     gender, role);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured while retreving data, Try again", "ErrorCode 102");
        } finally {
            dbConnection.closeConnection();
        }
        return employees;  
    }

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public boolean deleteEmployee(int employeeId) throws EMSException {

        int count = 0;        
        boolean isDeleted= false;
        Connection connection = dbConnection.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("delete from employee ")
             .append(" where employee_id = ")
             .append(employeeId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                                                  query.toString());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new EMSException
            ("Error occured while deleting data, Try again", "ErrorCode 105");
        } finally {
            dbConnection.closeConnection();
        }
        if (count > 0) {
             isDeleted = true;
        }
        return isDeleted;
    }                
}