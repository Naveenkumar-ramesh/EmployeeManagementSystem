package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.EmployeeManagement.EmployeeManagementService;
import com.ideas2it.employee.view.EmployeeView;
import java.util.List;
import java.util.ArrayList;

/**
 * Transfers the values between view and controller.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeController {
    EmployeeService employeeService = new EmployeeManagementService();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns true if employee added
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);

    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<EmployeeDTO> displayEmployee() {
        return employeeService.displayEmployee();
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public EmployeeDTO searchEmployee(String name) {
        return employeeService.searchEmployee(name);
    }


    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     */
    public boolean deleteEmployee(String name) {
        return employeeService.deleteEmployee(name);
    }

}