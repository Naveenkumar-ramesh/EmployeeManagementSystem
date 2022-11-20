package com.ideas2it.employee.service.EmployeeManagement;

import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.service.EmployeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.ProjectDAO;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Get's the project details from the database and provides the user and from
 * user to the database.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */

@Service
public class ProjectManagementService implements ProjectService {

	@Autowired
	private ProjectDAO projectDao;

	@Lazy
	@Autowired
	private EmployeeManagementService service;

	private Logger logger = LogManager.getLogger(ProjectManagementService.class);

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public ProjectDTO addProject(ProjectDTO projectDto) {
		Project project = ProjectMapper.toProject(projectDto);
		project = projectDao.save(project);
		logger.info("Project created succuessfully ProjectID =" + projectDto.getProjectId());
		return ProjectMapper.toProjectDTO(project);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public List<ProjectDTO> getProjects() {
		List<Project> projects = projectDao.findAll();
		List<ProjectDTO> projectsDto = new ArrayList<ProjectDTO>();
		for (Project project : projects) {
			projectsDto.add(EmployeeMapper.toProjectDTO(project));
		}

		return projectsDto;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public List<ProjectDTO> searchProject(String name) {
		List<Project> projects = projectDao.findByName(name);
		List<ProjectDTO> projectsDto = new ArrayList<ProjectDTO>();
		for (Project project : projects) {
			projectsDto.add(ProjectMapper.toProjectDTO(project));
		}

		return projectsDto;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public ProjectDTO updateProject(ProjectDTO projectDto) {
		Project project = ProjectMapper.toProject(projectDto);
		ProjectDTO projectDTO = ProjectMapper.toProjectDTO(projectDao.save(project));
		return projectDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	public void deleteProject(int id) {
		projectDao.deleteById(id);
		logger.info("Project deleted successfully ProjectID =" + id);
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 */
	public ProjectDTO assignEmployeeToProject(int projectId, int employeeId) {
		Project project = getProjectById(projectId);
		Employee employee = service.getEmployeeById(employeeId);
		project.getEmployees().add(employee);
		return ProjectMapper.toProjectDTO(projectDao.save(project));
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 */
	public Project getProjectById(int projectId) {
		return projectDao.findById(projectId)
				.orElseThrow(() -> new EMSException(EmployeeManagementConstant.DETALILS_NOT_EXIST,
						EmployeeManagementConstant.ERROR_CODE103));

	}
}