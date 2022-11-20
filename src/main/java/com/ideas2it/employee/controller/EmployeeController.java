package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeManagement.EmployeeManagementService;
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
 * Get's the Employee details and saves them to database Display's and
 * manipulates these project details.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */

@RestController
@RequestMapping("/api/ems/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeManagementService employeeService;

	/**
	 * Employee details are added to the database.
	 *
	 * @return returns id of employee added
	 */
	@PostMapping("/create")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDto) {
		return new ResponseEntity<EmployeeDTO>(employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
	}

	/**
	 * Employee details to be displayed.
	 *
	 * @return the employee details from the service class.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.getEmployees(), HttpStatus.OK);
	}

	/**
	 * Receives the required employee details from database.
	 *
	 * @param Employee name
	 * @return returns required employee details
	 */
	@GetMapping("/search/{name}")
	public ResponseEntity<List<EmployeeDTO>> searchEmployee(@PathVariable String name) {
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.searchEmployee(name), HttpStatus.OK);
	}

	/**
	 * Employee details to be updated are added to the database.
	 * 
	 * @param employee
	 */
	@PatchMapping("/update")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDto) {
		return new ResponseEntity<EmployeeDTO>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
	}

	/**
	 * The required employee data is deleted.
	 * 
	 * @param employee id
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Employee details deleted sucessfully", HttpStatus.NO_CONTENT);
	}

	/**
	 * Assigns the project to the designated employee.
	 * 
	 * @param employeeId
	 * @param projectId
	 * @return employee
	 */
	@PatchMapping("/assign/employee/{employeeId}/project/{projectId}")
	public ResponseEntity<EmployeeDTO> assignProjectToEmployee(@PathVariable int employeeId,
			@PathVariable int projectId) {
		EmployeeDTO employeeDTO = employeeService.assignProjectToEmployee(employeeId, projectId);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

}