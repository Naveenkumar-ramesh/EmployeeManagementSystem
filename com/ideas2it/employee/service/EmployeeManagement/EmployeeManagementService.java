package com.ideas2it.employee.service.EmployeeManagement;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.EmployeeManagementDao.EmployeeDao;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.dto.EmployeeDTO;
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
public class EmployeeManagementService implements EmployeeService {
    EmployeeMapper employeeMapper = new EmployeeMapper();
    EmployeeDao employeeDao = new EmployeeDao();

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.addEmployee(employeeMapper.toEmployee(employeeDTO));

    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public List<EmployeeDTO> displayEmployee() {
        List<Employee> employees = employeeDao.displayEmployee();
        List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeesDto.add(employeeMapper.toEmployeeDTO(employee));
        }
        return employeesDto;
    }

    /**
     * 
     * {@inheritDoc}
     * 
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
     * 
     * {@inheritDoc}
     * 
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.updateEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public boolean deleteEmployee(String name) {
        return employeeDao.deleteEmployee(name);
    }

}