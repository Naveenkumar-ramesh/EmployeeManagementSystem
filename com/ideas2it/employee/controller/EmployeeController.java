package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.service.EmployeeManagement.EmployeeManagementServiceImpl;
import java.util.List;
import java.util.ArrayList;

/**
 * Transfers the values between view and controller.
 *
 * @version 1.8
 * @author Naveenkumar R
 */
public class EmployeeController {
    EmployeeManagementServiceImpl employeeManagementService = new EmployeeManagementServiceImpl();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns true if employee added
     */
    public boolean addEmployee(Employee employee) {
        return employeeManagementService.addEmployee(employee);

    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<Employee> displayEmployee() {
        return employeeManagementService.displayEmployee();
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public Employee searchEmployee(String name) {
        return employeeManagementService.searchEmployee(name);
    }


    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public boolean updateEmployee(Employee employee) {
        return employeeManagementService.updateEmployee(employee);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     */
    public boolean deleteEmployee(String name) {
        return employeeManagementService.deleteEmployee(name);
    }

}