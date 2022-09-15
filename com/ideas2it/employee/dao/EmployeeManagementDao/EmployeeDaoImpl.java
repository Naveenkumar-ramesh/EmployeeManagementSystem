package com.ideas2it.employee.dao.EmployeeManagementDao;

import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.mapper.ModelMapper;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements transfer of employee details from database and
 * provides the data to be displayed.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeDaoImpl implements EmployeeDao {

    ModelMapper modelMapper = new ModelMapper();
    List<Employee> employees = new ArrayList<Employee>();

    /**
     * {@inheritDoc}
     * 
     */
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    /**
     * {@inheritDoc}
     * 
     */
    public List<Employee> displayEmployee() {
        return employees;
    }


    /**
     * {@inheritDoc}
     * 
     */
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
     * {@inheritDoc}
     * 
     */
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