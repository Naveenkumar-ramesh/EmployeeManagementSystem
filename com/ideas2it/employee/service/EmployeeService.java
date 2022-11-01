package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public interface EmployeeService {

    /**
     * Compares if the detail matches the expectation
     * and return true if it matches.
     * 
     * @param employee details
     * @return Return the boolean value.
     */
    public boolean validateField(String regexPattern, String fieldValue) ;  

    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException;

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<EmployeeDTO> getEmployees() throws EMSException;

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException;

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public void updateEmployee(EmployeeDTO employeeDTO) throws EMSException;

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public void deleteEmployee(int employeeId) throws EMSException;

    /**
     * Checks the validity of the joining date with birth date.
     *
     * @param dateOfBirth and dateOfJoining
     * @return true if joining date is valid
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth,LocalDate dateOfJoining);

    /**
     * Checks the validity of the birth date .
     *
     * @param dateOfBirth 
     * @return true if birth date is valid
     */
    public boolean validateBirthDate(LocalDate dateOfBirth);

    /**
     * Check the validity of the email id.
     *
     * @param email id
     * @return true if email id is valid.
     */
    public boolean validateEmail(String email) throws EMSException;

    /**
     * Check the validity of the Phone number.
     *
     * @param Phone number
     * @return true if Phone number is valid.
     */
    public boolean validatePhoneNumber(long phoneNumber) throws EMSException;

    /**
     * Check if employee id already exists.
     *
     * @param employee id
     * @return true if id exists.
     */
    public boolean isEmployeePresent(int employeeId) throws EMSException;

    /**
     * Get the employee details of the id .
     *
     * @param employee id
     * @return employee details of id.
     */
    public EmployeeDTO getEmployeeById(int employeeId) throws EMSException;

}