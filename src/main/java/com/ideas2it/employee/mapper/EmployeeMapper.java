package com.ideas2it.employee.mapper;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.dto.ProjectDTO;

import java.util.List;
import java.util.ArrayList;

/**
 * Converts DTO Employee details to model Employee details and 
 * model Employee details to DTO Employee details.
 * 
 * @version 1.8 15-09-2022
 * @author Naveenkumar R
 */
public class EmployeeMapper {

    /**
     * Convert model Employee details to DTO Employee details
     *
     * @param employeeDTO 
     * @return employee
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();;
        ProjectDTO projectDTO = new ProjectDTO();
        List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
        List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();

        if (null != employee) {
            employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhoneNumber(employee.getPhoneNumber());
            employeeDTO.setSalary(employee.getSalary());
            employeeDTO.setDateOfJoining(employee.getDateOfJoining());

            if (null != employee.getAddresses()) {
                for (Address address : employee.getAddresses()) {
                    addressDTOs.add(toAddressDTO(address));
                }
                employeeDTO.setAddresses(addressDTOs);
            }

            if (null != employee.getProjects()) {
                for (Project project : employee.getProjects()) {
                    projectDTOs.add(toProjectDTO(project));
                }
                employeeDTO.setProjects(projectDTOs);
            }

            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setRole(employee.getRole());
        }
        return employeeDTO;
    }


    /**
     * Convert DTO Employee details to model Employee details
     *
     * @param employee 
     * @return employeeDTO
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        Project project = new Project();
        List<Address> addresses = new ArrayList<Address>();
        List<Project> projects = new ArrayList<Project>();

        if (null != employeeDTO) {
            employee.setEmployeeId(employeeDTO.getEmployeeId());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDateOfJoining(employeeDTO.getDateOfJoining());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setGender(employeeDTO.getGender());
            employee.setRole(employeeDTO.getRole());

            if (null != employeeDTO.getAddresses()) {
                for (AddressDTO address : employeeDTO.getAddresses()) {
                    addresses.add(toAddress(address));
                }
                employee.setAddresses(addresses);
            }
            if (null != employeeDTO.getProjects()) {
                for (ProjectDTO projectDTO : employeeDTO.getProjects()) {
                    projects.add(toProject(projectDTO));
                }
                employee.setProjects(projects);
            }
        }
        return employee;
    }

    /**
     * Convert model address to DTO address
     *
     * @param addressDTO 
     * @return address
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setType(address.getType());

        return addressDTO;

    }

    /**
     * Convert DTO address to model address
     *
     * @param address
     * @return addressDTO
     */
    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        address.setType(addressDTO.getType());

        return address;

    }

    /**
     * Convert model Project details to DTO Project details
     *
     * @param projectDTO 
     * @return project
     */
    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();

        if (null != project) {
            projectDTO.setProjectId(project.getProjectId());
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setTechnology(project.getTechnology());
            projectDTO.setClientName(project.getClientName());
            projectDTO.setClientEmail(project.getClientEmail());
            projectDTO.setStartDate(project.getStartDate());
            projectDTO.setDueDate(project.getDueDate());
            projectDTO.setEndDate(project.getEndDate());

        }
        return projectDTO;
    }

    /**
     * Convert DTO Project details to model Project details
     *
     * @param project 
     * @return projectDTO
     */
    public static Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        List<Employee> employees = new ArrayList<Employee>();

        if (null != projectDTO) {
            project.setProjectId(projectDTO.getProjectId());
            project.setProjectName(projectDTO.getProjectName());
            project.setTechnology(projectDTO.getTechnology());
            project.setClientName(projectDTO.getClientName());
            project.setClientEmail(projectDTO.getClientEmail());
            project.setStartDate(projectDTO.getStartDate());
            project.setDueDate(projectDTO.getDueDate());
            project.setEndDate(projectDTO.getEndDate());

        }
        return project;
    }

}