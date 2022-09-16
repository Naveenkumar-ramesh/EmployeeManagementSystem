package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dao.EmployeeManagementDao.EmployeeDao;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.util.EmployeeManagementUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Get's the Employee details , organizes and saves them to database .
 * Display's and manipulates these employee details.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeView {

    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    /**
     * Create, read, search, update, delete and exit operations takes place by choice.
     */
    public void chooseOperation() {

        int loopExit = 1;

        while (loopExit != 0) {
            try {
                System.out.println("Enter the opertaion to be done \na.CREATE"
                                   + "\nb.Display \nc.SEARCH \nd.UPDATE \ne.DELETE \nf.EXIT");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "a":
                        this.createEmployee();
                        break;

                    case "b":
                        this.displayEmployee();
                        break;

                    case "c":
                        this.searchEmployee();
                        break;

                    case "d":
                        this.updateEmployee();
                        break;

                    case "e":
                        this.deleteEmployee();
                        break;

                    case "f":
                        System.out.println("**EXIT**");
                        System.exit(0);

                    default:
                        System.out.println("Try Again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter the valid choice");
            }
        }
    }

    /**
     * Employee details are received and added to database
     * and Shows if they are added or not.
     */
    public void createEmployee() {

        EmployeeManagementUtil employeeUtil = new EmployeeManagementUtil();
        System.out.println(EmployeeManagementConstant.VALID_DETAILS);
        try {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_NAME);
            String name = scanner.nextLine();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_ID);
            String id = scanner.nextLine();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_NUMBER);
            String phoneNumber = scanner.nextLine();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_DOJ);
            LocalDate dateOfJoining = employeeUtil.getDate();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_EMAIL);
            String email = scanner.nextLine();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_BLOODGROUP);
            String bloodGroup = scanner.nextLine();

            System.out.print(EmployeeManagementConstant.EMPLOYEE_SALARY);
            double salary = Double.parseDouble(scanner.nextLine()); 

            AddressDTO addressDTO = addAddress();
            employeeDTO = new EmployeeDTO(id, name, email, phoneNumber, dateOfJoining, salary, bloodGroup, addressDTO);

            if (employeeController.addEmployee(employeeDTO)) {
                System.out.println("Added");
            } else {
                System.out.println("Not Added...Try Again");
            }

        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.VALID_DETAILS);
        }
    }

    /**
     * Combine the seperate address details 
     * and provides organised address.
     * @return Employee address.
     */
    public AddressDTO addAddress() {

        System.out.print("Enter the DoorNumber:");
        String doorNumber = scanner.nextLine();

        System.out.print("Enter the StreetName:");
        String street = scanner.nextLine();

        System.out.print("Enter the City:");
        String city = scanner.nextLine();

        System.out.print("Enter the State:");
        String state = scanner.nextLine();

        System.out.print("Enter the Pincode:");
        int pinCode = Integer.parseInt(scanner.nextLine());

        AddressDTO addressDTO = new AddressDTO(doorNumber, street, city, state, pinCode);

        return addressDTO;
    }

    /**
     * Display's all the employee details that are 
     * saved in the the database.
     */
    public void displayEmployee() {
        List<EmployeeDTO> employees = employeeController.displayEmployee();
        Iterator<EmployeeDTO> iterator = employees.iterator();
        while (iterator.hasNext()) {
            EmployeeDTO employeeDTO = iterator.next();
            System.out.println(employeeDTO);
        }
    }

    /**
     * Searches for the employee by name and returns if exists
     * or returns no record found.
     */
    public void searchEmployee() {

        System.out.println(EmployeeManagementConstant.EMPLOYEE_NAME);
        String name = scanner.nextLine();
        EmployeeDTO selectEmployee = employeeController.searchEmployee(name);
        if (selectEmployee != null) {
            System.out.println(selectEmployee);
        } else {
            System.out.println("Not found the Employee");
        }
    }


    /**
     * Transfers the employee details to check if the name exists
     * and if exists it replaces the employee details.
     *
     * If the given name not found it shows record not found.
     */
    public void updateEmployee() {

        EmployeeManagementUtil employeeUtil = new EmployeeManagementUtil();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        System.out.print(EmployeeManagementConstant.EMPLOYEE_NAME);
        String name = scanner.nextLine();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_ID);
        String id = scanner.nextLine();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_NUMBER);
        String phoneNumber = scanner.nextLine();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_DOJ);
        LocalDate dateOfJoining = employeeUtil.getDate();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_EMAIL);
        String email = scanner.nextLine();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_BLOODGROUP);
        String bloodGroup = scanner.nextLine();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_SALARY);
        double salary = Double.parseDouble(scanner.nextLine()); 

        AddressDTO addressDTO = addAddress();
        employeeDTO = new EmployeeDTO(id, name, email, phoneNumber, dateOfJoining, salary, bloodGroup, addressDTO);

        if (employeeController.updateEmployee(employeeDTO)) {
            System.out.println("Employee details has been added");
        } else {
            System.out.println("Employee details has not been Added");
        }
    }

    /**
     * Here we get the employee id to which
     * relevant details of the employee are deleted.
     */
    public void deleteEmployee() {

        System.out.println("Enter the Employee name to delete:");
        String name = scanner.nextLine();

        if (employeeController.deleteEmployee(name) != false) {
            System.out.println("Employee details deleted");
        } else {
            System.out.println("Employee details not deleted");
        }
    }

}