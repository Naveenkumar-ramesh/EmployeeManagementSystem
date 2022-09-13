package com.ideas2it.employee.main;

import com.ideas2it.employee.view.EmployeeView;

/**
 * Here we initialize the programm for creating employee database
 * display and make changes for these employee details.
 * @version 1.8 13-09-2022.
 * @author  Naveenkumar R
 */
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        EmployeeView viewEmployee = new EmployeeView();
        System.out.println("   Welcome To Employee Management System   ");
        viewEmployee.chooseOperation();
    }
}       