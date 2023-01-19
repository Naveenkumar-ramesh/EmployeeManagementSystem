package com.ideas2it.employee.service.EmployeeManagement;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.service.EmployeeService;

/**
 * Get's the employee details from the database and provides the user and from
 * user to the database.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */

@Service
public class EmployeeManagementService implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ProjectManagementService service ;

	private Logger logger = LogManager.getLogger(EmployeeManagementService.class);

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public EmployeeDTO addEmployee(EmployeeDTO employeeDto) throws EMSException {
		if (validateEmail(employeeDto.getEmail()) && validatePhoneNumber(employeeDto.getPhoneNumber())) {
			employeeDao.save(EmployeeMapper.toEmployee(employeeDto));
		} else {
			logger.error(EmployeeManagementConstant.DUPLICATE, EmployeeManagementConstant.ERROR_CODE200);
			throw new EMSException(EmployeeManagementConstant.DUPLICATE, EmployeeManagementConstant.ERROR_CODE200);
		}
		logger.info("Employee created for EmployeeID =" + employeeDto.getEmployeeId());
		return employeeDto;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public List<EmployeeDTO> getEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			employeesDto.add(EmployeeMapper.toEmployeeDTO(employee));
		}

		return employeesDto;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public List<EmployeeDTO> searchEmployee(String name) {
		List<Employee> employees = employeeDao.findByName(name);
		List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			employeesDto.add(EmployeeMapper.toEmployeeDTO(employee));
		}
		return employeesDto;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto) {
		Employee employee = EmployeeMapper.toEmployee(employeeDto);
		EmployeeDTO employeeDTO = null;
		try {
			employeeDTO = EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage());
			throw new EMSException(EmployeeManagementConstant.UPDATION_EXCEPTION,
					EmployeeManagementConstant.ERROR_CODE200);
		}
		return employeeDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public void deleteEmployee(int id) {
		employeeDao.deleteById(id);
		logger.info("Employee deleted successfully for EmploeeID =" + id);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public EmployeeDTO assignProjectToEmployee(int employeeId, int projectId) {
		Employee employee = getEmployeeById(employeeId);
		Project project = service.getProjectById(projectId);

		employee.getProjects().add(project);
		return EmployeeMapper.toEmployeeDTO(employeeDao.save(employee));

	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public Employee getEmployeeById(int employeeId) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new EMSException(EmployeeManagementConstant.DETALILS_NOT_EXIST,
						EmployeeManagementConstant.ERROR_CODE103));

		return employee;
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 */
	public boolean validateEmail(String email) {
		return (!(getEmployees().stream().anyMatch(employeeDTO -> employeeDTO.getEmail().equals(email))));
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 */
	public boolean validatePhoneNumber(long phoneNumber) {
		return (!(getEmployees().stream().anyMatch(
				employeeDTO -> String.valueOf(employeeDTO.getPhoneNumber()).equals(Long.toString(phoneNumber)))));
	}

}