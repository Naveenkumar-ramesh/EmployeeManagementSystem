package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.model.Project;

import java.util.List;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
public interface ProjectService {

	/**
	 * Saves the project details in above database and return true if the process is
	 * successful.
	 * 
	 * @param project from controller
	 * @return Return the boolean value.
	 */
	public ProjectDTO addProject(ProjectDTO projectDTO);

	/**
	 * Returns ProjectDetail to be displayed.
	 *
	 * @param project
	 * @return Returns project details
	 */
	public List<ProjectDTO> getProjects();

	/**
	 * Receives relevent employee details from database.
	 *
	 * @param Employee name
	 * @return returns relevent employee details
	 */
	public List<ProjectDTO> searchProject(String name);

	/**
	 * Updates the employee detail and returns true if successful.
	 *
	 * @param employee
	 * @return true if employee is updated
	 */
	public ProjectDTO updateProject(ProjectDTO projectDTO);

	/**
	 * Deletes the employee details and returns true if successful.
	 *
	 * @param employee name
	 * @return true if employee details are deleted.
	 */
	public void deleteProject(int projectId);

	/**
	 * Assigns the designated employee to the project.
	 * 
	 * @param projectDTO
	 * @return projectDTO
	 */
	public ProjectDTO assignEmployeeToProject(int projectId, int employeeId);

	/**
	 * Get's the respective project details of the Project id.
	 *
	 * @param project id
	 * @return project details of the id
	 */
	public Project getProjectById(int projectId);

}