package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import java.util.List;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public interface Dao {

    
    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    boolean addEmployee(Employee employee);

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    List<Employee> displayEmployee();

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    boolean updateEmployee(Employee employee);

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    boolean deleteEmployee(String name);

}