package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import java.util.List;

/**
 * Interface is used to declare the abstract method to service class
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public interface EmployeeManagementService {

    boolean addEmployee(Employee employee);

    List<Employee> displayEmployee();

    Employee searchEmployee(String name);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String name);

}