package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.service.EmployeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.EmployeeManagement.EmployeeManagementService;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.view.ProjectView;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

/**
 * Get's the Project details and saves them to database and
 * Display's and manipulates these project details.
 *
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
public class ProjectController {
    ProjectService projectService = new ProjectManagementService();

    /**
     * Get's the project details and adds them to the database.
     *
     * @return returns id of project added
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException {
        return projectService.addProject(projectDTO);
    }

    /**
     * Transfers project details to be displayed.
     *
     * @return the project details.
     */
    public List<ProjectDTO> getProjects() throws EMSException {
        return projectService.getProjects();
    }

    /**
     * Receives relevent project details from database.
     *
     * @param project name
     * @return returns relevent project details
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException {
        return projectService.searchProject(projectName);
    }

    /**
     * Transfer's project details to be updated.
     * @param project
     * @return the project details from the service class.
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException{
        projectService.updateProject(projectDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if project is deleted
     */
    public void deleteProject(int projectId) throws EMSException {
        projectService.deleteProject(projectId);
    }

    /**
     * Project details to be validated are transfered
     * Transfer's back true if field satisfies it expectations.
     *
     * @param regex pattern and field value
     * @return true if employee is deleted
     */
    public boolean validateField(String pattern, String field) {
        return projectService.validateField(pattern, field);
    }

    /**
     * Transfers project starting date  to be validated.
     *
     * @param starting date
     * @return true if starting date is valid.
     */
    public boolean isValidStartDate(String startDate) {
        return projectService.isValidStartDate(startDate);
    }

    /**
     * Transfers project starting date and dueend date to be valedated.
     *
     * @param starting date and end date
     * @return true if end date is valid .
     */
    public boolean isValidDate(LocalDate startDate, String date) {
        return projectService.isValidDate(startDate, date);
    }

    /**
     * Get's the project details for the requested employeeId.
     *
     * @param employee id
     * @return employee details of the id
     */
    public EmployeeDTO getEmployee(int employeeId) throws EMSException {
        EmployeeService employeeService = new EmployeeManagementService();
        EmployeeDTO employeeDto = employeeService.getEmployeeById(employeeId);
        return employeeDto;
    }

    /**
     * Transfers project id to check if already present in database.
     *
     * @param project id
     * @return true if id exists.
     */
    public boolean isProjectPresent(int projectId) throws EMSException {
        return projectService.isProjectPresent(projectId);
    }

    /**
     * Transfers project id to get the respective project details.
     *
     * @param project id
     * @return project details of the id
     */
    public ProjectDTO getProjectById(int projectId) throws EMSException {
        return projectService.getProjectById(projectId);
    }

}