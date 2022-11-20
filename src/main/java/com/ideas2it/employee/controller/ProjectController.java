package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.service.EmployeeManagement.ProjectManagementService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get's the Project details and saves them to database and Display's and
 * manipulates these project details.
 *
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
@RestController
@RequestMapping("/api/ems/v1/project")
public class ProjectController {

	@Autowired
	private ProjectManagementService projectService;

	/**
	 * Get's the project details and adds them to the database.
	 *
	 * @return returns id of project added
	 */
	@PostMapping("/create")
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDto) {
		return new ResponseEntity<ProjectDTO>(projectService.addProject(projectDto), HttpStatus.CREATED);
	}

	/**
	 * Get's project details from the database to be displayed.
	 *
	 * @return the project details.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<ProjectDTO>> getProjects() {
		return new ResponseEntity<List<ProjectDTO>>(projectService.getProjects(), HttpStatus.OK);
	}

	/**
	 * Receives relevant project details from database.
	 *
	 * @param project name
	 * @return returns relevant project details
	 */
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProjectDTO>> searchProject(String name) {
		return new ResponseEntity<List<ProjectDTO>>(projectService.searchProject(name), HttpStatus.OK);
	}

	/**
	 * Updates the project details in the database.
	 * 
	 * @param project
	 */
	@PatchMapping("/update")
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDto) {
		return new ResponseEntity<ProjectDTO>(projectService.updateProject(projectDto), HttpStatus.OK);
	}

	/**
	 * Deletes the project details by project id.
	 *
	 * @param project id
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable int id) {
		projectService.deleteProject(id);
		return new ResponseEntity<>("Employee details deleted sucessfully", HttpStatus.NO_CONTENT);
	}

	/**
	 * Assigns the employee to the designated project by project id and employee id.
	 * 
	 * @param employeeId
	 * @param projectId
	 * @return project
	 */
	@PatchMapping("/assign/project/{projectId}/employee/{employeeId}")
	public ResponseEntity<ProjectDTO> assignEmployeeToProject(@PathVariable int projectId,
			@PathVariable int employeeId) {
		ProjectDTO projectDTO = null;
		projectDTO = projectService.assignEmployeeToProject(projectId, employeeId);
		return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);
	}

}