package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
public interface ProjectService {


    /**
     * Project details to be validated .
     * Transfer's back true if field satisfies it expectations.
     *
     * @param regex pattern and field value
     * @return true if employee is deleted
     */
    public boolean validateField(String regexPattern, String fieldValue);

    /**
     * Saves the project details in above database
     * and return true if the process is successful.
     * 
     * @param project from controller
     * @return Return the boolean value.
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException;

    /**
     * Returns ProjectDetail to be displayed.
     *
     * @param project
     * @return Returns project details
     */
    public List<ProjectDTO> getProjects() throws EMSException;

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException;

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException;

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public void deleteProject(int projectId) throws EMSException;

    /**
     * Transfers project starting date to be validated.
     *
     * @param starting date
     * @return true if starting date is valid.
     */
    public boolean isValidStartDate(String startDate) ;

    /**
     * Transfers project starting date and due date to be validated.
     *
     * @param starting date and end date
     * @return true if end date is valid.
     */
    public boolean isValidDate(LocalDate startDate, String date);

    /**
     * Project id to check if already present in database.
     *
     * @param project id
     * @return true if id exists.
     */
    public boolean isProjectPresent(int projectId) throws EMSException;

    /**
     * Get's the respective project details of the Project id.
     *
     * @param project id
     * @return project details of the id
     */
    public ProjectDTO getProjectById(int projectId) throws EMSException;

}