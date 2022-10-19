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
import com.ideas2it.employee.util.ValidationUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
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
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException {
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
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException {
        List<Employee> employees = employeeDao.searchEmployee(firstName);
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
    public void updateEmployee(EmployeeDTO employeeDTO) throws EMSException {
        employeeDao.updateEmployee(employeeMapper.
                                          toEmployee(employeeDTO));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    public void deleteEmployee(int employeeId) throws EMSException {
        employeeDao.deleteEmployee(employeeId);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth,LocalDate dateOfJoining) {
        return ValidationUtil.validateJoiningDate(dateOfBirth,dateOfJoining);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean validateBirthDate(LocalDate dateOfBirth) {
        return ValidationUtil.validateBirthDate(dateOfBirth);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean validateEmail(String email) throws EMSException {
        return (!(displayEmployee().stream().
               anyMatch(employeeDTO -> employeeDTO.getEmail().equals(email))));
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean validatePhoneNumber(long phoneNumber) throws EMSException {
        return (!(displayEmployee().stream().
               anyMatch(employeeDTO -> String.valueOf(employeeDTO.
               getPhoneNumber()).equals(Long.toString(phoneNumber)))));
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean isIdPresent(int employeeId) throws EMSException {
        List<Employee> employees = employeeDao.displayEmployee();
        EmployeeDTO employeeDto = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employeeId) {
                employeeDto = (employeeMapper.toEmployeeDTO(employees.get(i)));
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
    public EmployeeDTO getEmployeeById(int employeeId) throws EMSException {
        List<Employee> employees = employeeDao.displayEmployee();
        EmployeeDTO employeeDto = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employeeId) {
                employeeDto = (employeeMapper.toEmployeeDTO(employees.get(i)));
                break;
            }
        }
        return employeeDto;
    }

}