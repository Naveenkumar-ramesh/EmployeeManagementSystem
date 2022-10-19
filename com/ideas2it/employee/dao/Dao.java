package com.ideas2it.employee.dao;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;
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
    public int addEmployee(Employee employee) throws EMSException ;

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<Employee> displayEmployee() throws EMSException ;

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public void updateEmployee(Employee employee) throws EMSException ;

    /**
     * Search the employee detail and returns the employee details.
     *
     * @param firstName
     * @return employee details wiith address.
     */
    public List<Employee> searchEmployee(String firstName) throws EMSException ;

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public void deleteEmployee(int employeeId) throws EMSException ;

}