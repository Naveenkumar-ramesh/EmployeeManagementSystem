package com.ideas2it.employee.service;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.dao.EmployeeManagementDao.EmployeeDaoImpl;
import com.ideas2it.employee.mapper.ModelMapper;
import com.ideas2it.employee.model.AddressDTO;
import com.ideas2it.employee.model.EmployeeDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

/**
 * Transfers the values between view and controller.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeManagementService {
    ModelMapper modelMapper = new ModelMapper();
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns true if employee added
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.addEmployee(modelMapper.toEmployee(employeeDTO));

    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<EmployeeDTO> displayEmployee() {
        List<Employee> employees = employeeDao.displayEmployee();
        List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeesDto.add(modelMapper.toEmployeeDTO(employee));
        }
        return employeesDto;
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public EmployeeDTO searchEmployee(String name) {
        List<EmployeeDTO> employeesDto = displayEmployee();
        EmployeeDTO searchEmployeeDto = null;
        for (int i = 0; i < employeesDto.size(); i++) {
            if (employeesDto.get(i).getName().equals(name)) {
                searchEmployeeDto = employeesDto.get(i);
            }
        }
        return searchEmployeeDto;
    }


    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.updateEmployee(modelMapper.toEmployee(employeeDTO));
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     */
    public boolean deleteEmployee(String name) {
        return employeeDao.deleteEmployee(name);
    }

}