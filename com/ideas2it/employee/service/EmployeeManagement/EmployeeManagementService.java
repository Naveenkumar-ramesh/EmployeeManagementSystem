package com.ideas2it.employee.service.EmployeeManagement;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.EmployeeManagementDao.EmployeeDao;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;

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
    public boolean validateField(String regexPattern, String fieldValue) {
        return Pattern.matches(regexPattern, fieldValue);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) throws EMSException {
        return employeeDao.addEmployee(employeeMapper.toEmployee(employeeDTO));

    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public List<EmployeeDTO> displayEmployee() throws EMSException {
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
    public EmployeeDTO searchEmployee(String firstName) throws EMSException {
        return employeeMapper.toEmployeeDTO(employeeDao.
                                            searchEmployee(firstName));
    }


    /**
     * 
     * {@inheritDoc}
     * 
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws EMSException{
        return employeeDao.updateEmployee(employeeMapper.
                                          toEmployee(employeeDTO));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public boolean deleteEmployee(int employeeId) throws EMSException {
        return employeeDao.deleteEmployee(employeeId);
    }

}