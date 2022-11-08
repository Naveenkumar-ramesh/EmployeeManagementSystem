package com.ideas2it.employee.service.EmployeeManagement;

import com.ideas2it.employee.dao.ProjectManagementDao;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.util.ValidationUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Get's the project details from the database and provides  
 * the user and from user to the database.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class ProjectManagementService implements ProjectService {
    ProjectMapper projectMapper = new ProjectMapper();
    ProjectManagementDao projectDao = new ProjectManagementDao();
    ValidationUtil validationUtil = new ValidationUtil();

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean validateField(String regexPattern, String fieldValue) {
        return validationUtil.isValid(regexPattern, fieldValue);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException {
        return projectDao.addProject(projectMapper.toProject(projectDTO));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public List<ProjectDTO> getProjects() throws EMSException {
        List<Project> projects = projectDao.getProjects();
        List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();
        for (Project project : projects) {
            projectDtos.add(projectMapper.toProjectDTO(project));
        }
        return projectDtos;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException {
        List<Project> projects = projectDao.searchProject(projectName);
        List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();
        for (Project project : projects) {
            projectDtos.add(projectMapper.toProjectDTO(project));
        }
        return projectDtos;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException {
        projectDao.updateProject(projectMapper.toProject(projectDTO));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public void deleteProject(int projectId) throws EMSException {
        projectDao.deleteProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValidStartDate(String startDate) {
        return validationUtil.isValidStartDate(startDate);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValidDate(LocalDate startDate, String date) {
        return validationUtil.isValidDate(startDate, date);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean isProjectPresent(int projectId) throws EMSException {
        List<Project> projects = projectDao.getProjects();
        ProjectDTO projectDto = null;
        for (Project project : projects) {
            if (project.getProjectId() == projectId) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public ProjectDTO getProjectById(int projectId) throws EMSException {
        List<Project> projects = projectDao.getProjects();
        ProjectDTO projectDto = null;
        for (Project project : projects) {
            if (project.getProjectId() == projectId) {
                projectDto = (projectMapper.toProjectDTO(project));
                break;
            }
        }
        return projectDto;
    }

}