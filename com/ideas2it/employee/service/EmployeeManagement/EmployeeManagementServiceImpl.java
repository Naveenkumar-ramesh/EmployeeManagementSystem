package com.ideas2it.employee.service.EmployeeManagement;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements transfer of employee details from database and
 * provides the data to be displayed.
 *
 * @version 1.8 03-09-2022
 * @author Naveenkumar R
 */
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    List<Employee> employees = new ArrayList<Employee>();

    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    @Override
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    @Override
    public List<Employee> displayEmployee() {
        return employees;
    }

    /**
     * Searches for the employee by name and
     * returns the employee details.
     *
     * @param employee name.
     * @return Returns employee
     */
    @Override
    public Employee searchEmployee(String name) {
        Employee searchEmployee = null;
        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getName().equals(name)) {
                searchEmployee = employees.get(i);
            }
        }
        return searchEmployee;
    }

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdate = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employee.getName().equals(employees.get(i).getName())) {
                employees.set(i, employee);
                isUpdate = true;
            }
        }
        return isUpdate;
    }

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    @Override
    public boolean deleteEmployee(String name) {
        boolean isDelete = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                employees.remove(i);
                isDelete = true;
            }
        }
        return isDelete;
    }
}