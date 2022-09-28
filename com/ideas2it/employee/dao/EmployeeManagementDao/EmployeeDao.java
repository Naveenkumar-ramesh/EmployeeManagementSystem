package com.ideas2it.employee.dao.EmployeeManagementDao;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.EmployeeManagementDao.Factory;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    Connection connection = Factory.getConnection();    
    /**
     * {@inheritDoc}
     * 
     */
    public boolean addEmployee(Employee employee) {
        int added = 0;
        PreparedStatement preparedStatement = null;
        long employeeid = 0;

        try {
            preparedStatement = connection.preparedStatement("insert into employee(first_name," +
                                " last_name, date_of_birth," +
                                " email, phone_number, salary, date_of_joining, date_of_birth," +
                                " gender, role) values(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getPhoneNumber());
            preparedStatement.setDouble(6,employee.getSalary());
            preparedStatement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(9,employee.getGender());
            preparedStatement.setString(10,employee.getRole());
            added = preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.executeQuery(" Select " +
                               "employee_id from employee where email = " + "'" + employee.getEmail() + "'");
            while (result.next()){
                employeeid = result.getLong("employee_id");
            }

            preparedStatement = connection.prepareStatement("insert into address(employee_id, doorNumber,"
                                + "street, city, state, pincode, type)"
                                + "values(?,?,?,?,?,?,?)");
            Address address = new Address();
            preparedStatement.setLong(2,employeeid);
            preparedStatement.setString(3,address.getDoorNumber());
            preparedStatement.setString(4,address.getStreet());
            preparedStatement.setString(5,address.getCity());
            preparedStatement.setString(6,address.getState());
            preparedStatement.setInt(7,address.getPinCode());
            preparedStatement.setString(8,address.getType());
            added = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        Factory.closeConnection();
        return (added != 0);
    }

    /**
     * {@inheritDoc}
     * 
     */
    public List<Employee> displayEmployee() {
        List<Employee> employees = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("Select employee_id, first_name, last_name,email,")
             .append("phone_number, salary, date_of_joining, date_of_birth, gender, role,")
             .append("doornumber,street,city,state,pincode,type")
             .append("join employee on address ")
             .append("employee.employee_id = address.employee_id");

        try {
            PreparedStatement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {

                long employeeId = result.getLong(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String email = result.getString(4);
                long phoneNumber = result.getLong(5);
                double salary = result.getDouble(6);
                LocalDate DateOfJoining = result.getDateOfJoining(7);
                LocalDate DateOfBirth = result.getDateOfBirth(8);
                String gender = result.getString(9);
                String role = result.getString(10);
                String doorNumber = result.getString(11);
                String street = result.getString(12);
                String city = result.getString(13);
                String state = result.getString(14);
                int PinCode = result.getInt(15);
                String Type = result.getString(16);

                Address address = new Address(doorNumber, street, city,
                                                state, pincode, type);
                Employee employee = new Employee(employeeId, firstName,
                                     lastName, email, phoneNumber, salary,
                                     dateOfJoining, dateOfBirth, gender, role);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        Factory.closeConnection();
        return employees;  
    }


    /**
     * {@inheritDoc}
     * 
     */
    public boolean updateEmployee(Employee employee) {
        int updated = 0;
        PreparedStatement preparedStatement = null;
        long employeeid = 0;
        StringBuilder query = new StringBuilder();
        query.append("update employee set employee_id, first_name, last_name,")
             .append(" date_of_birth, email, phone_number, date_of_joining,")
             .append(" date_of_birth, gender, role, doornumber, street,")
             .append("city, state, pincode, type");

        try {
            ResultSet result = prepareStatement.executeQuery("Select "
                               + "employee_id from employee where email = "
                               + employee.getEmail);
            employeeid = result.getLong("employee_id");
            preparedStatement = connection.prepareStatement("update employee."
                               + "set(?,?,?,?,?,?,?,?,?,?)where employee_id = "
                               + "" + "" + employeeid + "");
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getPhoneNumber());
            preparedStatement.setDouble(6,employee.getSalary());
            preparedStatement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(9,employee.getGender());
            preparedStatement.setString(10,employee.getRole());
            updated = preparedStatement.executeUpdate();

            preparedStatement = prepareStatement("update address."
                               + "set(?,?,?,?,?,?,?) where employee_Id = " 
                               + "" + employeeid + "");
            Address address = new Address();
            preparedStatement.setString(2,address.getDoorNumber());
            preparedStatement.setString(3,address.getStreet());
            preparedStatement.setString(4,address.getCity());
            preparedStatement.setString(5,address.getState());
            preparedStatement.setInt(6,address.getPinCode());
            preparedStatement.setString(7,address.getType());
            updated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        Factory.closeConnection();
        return (updated != 0);
    }

    /**
     * {@inheritDoc}
     * 
     */
    public boolean deleteEmployee(Employee employee) {
        
        int deleted = 0;
        PreparedStatement preparedStatement = null;
        try {

            ResultSet result = connection.prepareStatement.executeQuery("Select" +
                              "employee_id from employee where email = "
                              + employee.getEmail());
            long employeeid = result.getLong("employee_id");
            preparedStatement = connection.prepareStatement("delete from address where "
                                + "employee_id = " + "" + employeeid + "");
            deleted = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from employee where "
                               + "employee_id = " + employeeid );
            deleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        Factory.closeConnection();
        return (deleted != 0);
    }

}