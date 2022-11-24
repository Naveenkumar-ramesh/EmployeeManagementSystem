package com.ideas2it.employee.mapper;

import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dto.AddressDTO;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Converts DTO Project details to model Project details and 
 * model Project details to DTO Project details.
 * 
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
@Component
public class ProjectMapper {

    /**
     * Convert model Project details to DTO Project details
     *
     * @param projectDTO 
     * @return project
     */
    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        if (null != project) {
            projectDTO.setProjectId(project.getProjectId());
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setTechnology(project.getTechnology());
            projectDTO.setClientName(project.getClientName());
            projectDTO.setClientEmail(project.getClientEmail());
            projectDTO.setStartDate(project.getStartDate());
            projectDTO.setDueDate(project.getDueDate());
            projectDTO.setEndDate(project.getEndDate());
            projectDTO.setEmployeesDTO(toEmployeeDTO(project.getEmployees()));
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
        if (null != projectDTO) {
            project.setProjectId(projectDTO.getProjectId());
            project.setProjectName(projectDTO.getProjectName());
            project.setTechnology(projectDTO.getTechnology());
            project.setClientName(projectDTO.getClientName());
            project.setClientEmail(projectDTO.getClientEmail());
            project.setStartDate(projectDTO.getStartDate());
            project.setDueDate(projectDTO.getDueDate());
            project.setEndDate(projectDTO.getEndDate());
            project.setEmployees(toEmployee(projectDTO.getEmployeesDTO()));
        }
        return project;
    }

    /**
     * Convert model Employee details to DTO Employee details
     *
     * @param employeeDTO 
     * @return employee
     */
    public static List<EmployeeDTO> toEmployeeDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
        List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();

        if (null != employees) {
            for (Employee employee : employees) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmployeeId(employee.getEmployeeId());
                employeeDTO.setFirstName(employee.getFirstName());
                employeeDTO.setLastName(employee.getLastName());
                employeeDTO.setEmail(employee.getEmail());
                employeeDTO.setPhoneNumber(employee.getPhoneNumber());
                employeeDTO.setSalary(employee.getSalary());
                employeeDTO.setDateOfJoining(employee.getDateOfJoining());
                employeeDTO.setDateOfBirth(employee.getDateOfBirth());
                employeeDTO.setGender(employee.getGender());
                employeeDTO.setRole(employee.getRole());
    
                if (null != employee.getAddresses()) {
                    for (Address address : employee.getAddresses()) {
                        addressDTOs.add(toAddressDTO(address));
                    }
                    employeeDTO.setAddresses(addressDTOs);
                }
                employeeDTOs.add(employeeDTO);
            }
        }
        return employeeDTOs;
    }


    /**
     * Convert DTO Employee details to model Employee details
     *
     * @param employee 
     * @return employeeDTO
     */
    public static List<Employee> toEmployee(List<EmployeeDTO> employeeDTOs) {
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<Employee>();
        List<Address> addresses = new ArrayList<Address>();

        if (null != employeeDTOs) {
            for (EmployeeDTO employeeDTO : employeeDTOs) {
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
                employees.add(employee);
            }
        }
        return employees;
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

}