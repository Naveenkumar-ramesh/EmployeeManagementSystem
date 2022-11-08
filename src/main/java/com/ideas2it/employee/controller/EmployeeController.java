package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.EmployeeManagement.EmployeeManagementService;
import com.ideas2it.employee.service.EmployeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.view.EmployeeView;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

/**
 * Get's the Employee details and saves them to database .
 * Display's and manipulates these project details.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeController {
    EmployeeService employeeService = new EmployeeManagementService();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns id of employee added
     */
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException {
        return employeeService.addEmployee(employeeDTO);
    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<EmployeeDTO> getEmployees() throws EMSException {
        return employeeService.getEmployees();
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException {
        return employeeService.searchEmployee(firstName);
    }

    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public void updateEmployee(EmployeeDTO employeeDTO) throws EMSException{
        employeeService.updateEmployee(employeeDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     */
    public void deleteEmployee(int employeeId) throws EMSException {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Employee details to be validated are transfered
     * Transfer's back true if field satisfies it expectations.
     *
     * @param regex pattern and field value
     * @return true if employee is deleted
     */
    public boolean validateField(String regexPattern, String fieldValue) {
        return employeeService.validateField(regexPattern, fieldValue);
    }

    /**
     * Transfers birth date and joining date to be valedated.
     *
     * @param birth date and joining date
     * @return true if joining date is valid.
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth,LocalDate dateOfJoining) {
        return employeeService.validateJoiningDate(dateOfBirth,dateOfJoining);
    }

    /**
     * Transfers birth date to be valedated.
     *
     * @param birth date
     * @return true if birth date is valid.
     */
    public boolean validateBirthDate(LocalDate dateOfBirth) {
        return employeeService.validateBirthDate(dateOfBirth);
    }

    /**
     * Transfers email id to be valedated.
     *
     * @param email id
     * @return true if email id is valid.
     */
    public boolean validateEmail(String email) throws EMSException {
        return employeeService.validateEmail(email);
    }

    /**
     * Transfers phoneNumber to be valedated.
     *
     * @param phoneNumber
     * @return true if phoneNumber is valid.
     */
    public boolean validatePhoneNumber(long phoneNumber) throws EMSException {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * Transfers employee id to check if already present in database.
     *
     * @param employee id
     * @return true if id exists.
     */
    public boolean isEmployeePresent(int employeeId) throws EMSException {
        return employeeService.isEmployeePresent(employeeId);
    }

    /**
     * Transfers employee id to get the respective employee details.
     *
     * @param employee id
     * @return employee details of the id
     */
    public EmployeeDTO getEmployeeById(int employeeId) throws EMSException {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public ProjectDTO getProject(int projectId) throws EMSException {
        ProjectService projectService = new ProjectManagementService();
        ProjectDTO projectDto = projectService.getProjectById(projectId);
        return projectDto;
    }

}