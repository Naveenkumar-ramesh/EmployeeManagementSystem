package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Employee;

import java.util.List;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public interface EmployeeService {

	/**
	 * Saves the employee details in above database and return true if the process
	 * is successful.
	 * 
	 * @param employee from controller
	 * @return Return the boolean value.
	 */
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto);

	/**
	 * Returns EmployeeDetail to be displayed.
	 *
	 * @param employee
	 * @return Returns employee
	 */
	public List<EmployeeDTO> getEmployees();

	/**
	 * Receives relevent employee details from database.
	 *
	 * @param Employee name
	 * @return returns relevent employee details
	 */
	public List<EmployeeDTO> searchEmployee(String name);

	/**
	 * Updates the employee detail and returns true if successful.
	 *
	 * @param employee
	 * @return true if employee is updated
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto);

	/**
	 * Deletes the employee details and returns true if successful.
	 *
	 * @param employee name
	 * @return true if employee details are deleted.
	 */
	public void deleteEmployee(int employeeId);

	/**
	 * Assigns the designated project to the employee.
	 * 
	 * @param employeeDTO
	 * @return employeeDTO
	 */
	public EmployeeDTO assignProjectToEmployee(int employeeId, int projectId);

	/**
	 * Check the validity of the email id.
	 *
	 * @param email id
	 * @return true if email id is valid.
	 */
	public boolean validateEmail(String email);

	/**
	 * Check the validity of the Phone number.
	 *
	 * @param Phone number
	 * @return true if Phone number is valid.
	 */
	public boolean validatePhoneNumber(long phoneNumber);

	/**
	 * Get the employee details of the id .
	 *
	 * @param employee id
	 * @return employee details of id.
	 */
	public Employee getEmployeeById(int employeeId);

}