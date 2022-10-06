package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.time.DateTimeException;
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
     * Create, read, search, update, delete and exit 
     * operations takes place by choice.
     */
    public void chooseOperation() {

        int loopExit = 1;

        while (loopExit != 0) {
            try {
                System.out.println("Enter the opertaion to be done \na.CREATE"
                                   + "\nb.Display \nc.SEARCH "
                                   + "\nd.UPDATE \ne.DELETE \nf.EXIT");
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
        boolean isValid;

        System.out.println(EmployeeManagementConstant.VALID_DETAILS);
        try {

            int employeeId = 0;
            String firstName = getName("First Name");
            String lastName = getName("Last Name");
            String email = getEmail();
            long phoneNumber = getPhoneNumber();
            double salary = getSalary();
            LocalDate dateOfJoining = getDateOfJoining();
            LocalDate dateOfBirth = getDateOfBirth();
            String gender = getName("Gender");
            String role = getName("Role");

            AddressDTO address = addAddress();
            employeeDTO = new EmployeeDTO(employeeId, firstName, lastName,
                                          email, phoneNumber, salary,
                                          dateOfJoining, address,dateOfBirth,
                                          gender, role);

            if (employeeController.addEmployee(employeeDTO)) {
                System.out.println("Employee Details Added");
            } else {
                System.out.println("Employee Details  Not Added...Try Again");
            }

        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Combine the seperate address details 
     * and provides organised address.
     * @return Employee address.
     */
    public AddressDTO addAddress() {

        String doorNumber = getDoorNumber();
        String street = getAddressDetail("Street Name");
        String city = getAddressDetail("City Name");
        String state = getAddressDetail("State Name");
        String type = getAddressDetail("Address type");
        int pinCode = getPinCode();

        AddressDTO address = new AddressDTO(doorNumber, street,
                                               city, state, pinCode, type);
        return address;
    }

    /**
     * Display's all the employee details that are 
     * saved in the the database.
     */
    public void displayEmployee() {

        try {
            List<EmployeeDTO> employees = employeeController.displayEmployee();
            if(!(employees.isEmpty())) {
                Iterator<EmployeeDTO> iterator = employees.iterator();
                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO = iterator.next();
                    System.out.println(employeeDTO);
                }
            } else {
                System.out.println("No Employee details found ");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Searches for the employee by name and returns if exists
     * or returns no record found.
     */
    public void searchEmployee() {
        String firstName = getName("First Name");

        try {
            EmployeeDTO selectEmployee = employeeController.searchEmployee(firstName);
            if (selectEmployee != null) {
                System.out.println(selectEmployee);
            } else {
                System.out.println("Not found the Employee");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }


    /**
     * Transfers the employee details to check if the name exists
     * and if exists it replaces the employee details.
     *
     * If the given name not found it shows record not found.
     */
    public void updateEmployee() {
        boolean isValid;
        EmployeeDTO employeeDTO = new EmployeeDTO();

        System.out.print(EmployeeManagementConstant.EMPLOYEE_ID);
        int employeeId = Integer.parseInt(scanner.nextLine());

        String firstName = getName("First Name");
        String lastName = getName("Last Name");
        String email = getEmail();
        long phoneNumber = getPhoneNumber();
        double salary = getSalary();
        LocalDate dateOfJoining = getDateOfJoining();
        LocalDate dateOfBirth = getDateOfBirth();
        String gender = getName("Gender");
        String role = getName("Role");
        AddressDTO address = addAddress();
        employeeDTO = new EmployeeDTO(employeeId, firstName, lastName,
                                      email, phoneNumber,salary, dateOfJoining,
                                      address, dateOfBirth, gender, role);

        try {
            if (employeeController.updateEmployee(employeeDTO)) {
                System.out.println("Employee details has been added");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Here we get the employee id to which
     * relevant details of the employee are deleted.
     */
    public void deleteEmployee() {

        int employeeId = getId();
        try {
            if (employeeController.deleteEmployee(employeeId) != false) {
                System.out.println("Employee details deleted");
            } else {
                System.out.println("Employee details not deleted");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Gets the First name of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return First name of the employee.
     */
   public String getDoorNumber() {
        String doorNumber;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_DOOR_NUMBER);
            doorNumber = scanner.nextLine();
            isValid = employeeController.validateField(
                              EmployeeManagementConstant.
                              REGEX_DOOR_NUMBER,
                              doorNumber);

            if (!(isValid)) {
                System.out.println(EmployeeManagementConstant.
                                   VALID_DOOR_NUMBER);
            }
        } while(!(isValid));
        return doorNumber;
    }

    /**
     * Gets the Email id of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Email id of the employee.
     */
    public String getEmail() {
        String email;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_EMAIL);
            email = scanner.nextLine();
            isValid = employeeController.validateField(
                              EmployeeManagementConstant.REGEX_EMAIL, email);
            if (!(isValid)) {
                System.out.println(EmployeeManagementConstant.VALID_EMAIL);
            }
        } while(!(isValid));
        return email;
    }

    /**
     * Gets the Contact number of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Contact number of the employee.
     */
    public long getPhoneNumber() {
        long phoneNumber;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_NUMBER);
            phoneNumber = Long.parseLong(scanner.nextLine());
            isValid = employeeController.validateField(
                              EmployeeManagementConstant.REGEX_NUMBER,
                              Long.toString(phoneNumber));
            if (!(isValid)) {
                System.out.println(EmployeeManagementConstant.VALID_NUMBER);
            }
        } while(!(isValid));
        return phoneNumber;
    }

    /**
     * Gets the Employee Id of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Id of the employee.
     */
    public int getId() {
        String employeeId = null;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_ID);
            try {
                employeeId = scanner.nextLine();
                isValid = employeeController.validateField(
                                  EmployeeManagementConstant.
                                  REGEX_ID,employeeId);
                if (!(isValid)) {
                    System.out.println(EmployeeManagementConstant.VALID_ID);
                }
            } catch (NumberFormatException exception) {
                System.out.println(EmployeeManagementConstant.VALID_ID);
                isValid = false;
            }
        }while (!(isValid));
        return Integer.parseInt(employeeId);
    }

    /**
     * Gets the Pincode of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return pincode of the employee.
     */
    public int getPinCode() {
        int pinCode = 0;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_PINCODE);
            try {
                pinCode = Integer.parseInt(scanner.nextLine());
                isValid = employeeController.validateField(
                                  EmployeeManagementConstant.REGEX_PINCODE,
                                  Integer.toString(pinCode));
                if (!(isValid)) {
                    System.out.println(EmployeeManagementConstant.
                                       VALID_PINCODE);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid PinCode ");
                isValid = false;
            }
        } while(!(isValid));
        return pinCode;
    }

    /**
     * Gets the salary of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Salary of the employee.
     */
    public double getSalary() {
        String salary = null;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_SALARY);
            try {
                salary =scanner.nextLine();
                isValid = employeeController.validateField(
                          EmployeeManagementConstant.REGEX_SALARY,salary);
                if (!(isValid)) {
                    System.out.println(EmployeeManagementConstant.
                                       VALID_SALARY);
                }
            } catch (NumberFormatException exception) {
                System.out.println(EmployeeManagementConstant.VALID_SALARY);
                isValid = false;
            }
        }while (!(isValid));
        return Double.parseDouble(salary);
    }

    /**
     * Gets the date of joining of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Date of joining of the employee.
     */
    public LocalDate getDateOfJoining() {
        LocalDate dateOfJoining = null;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_DOJ);
            try {
                dateOfJoining = LocalDate.parse(scanner.nextLine());
                isValid = false;
            } catch (DateTimeException exception) {
                System.out.println(" Enter valid date : ");
                isValid = true;
            }
        } while (isValid);
        return dateOfJoining;
    }

    /**
     * Gets the date of birth of the employee and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Date of birth of the employee.
     */
    public LocalDate getDateOfBirth() {
        LocalDate dateOfBirth = null;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_DOB);
            try {
                dateOfBirth = LocalDate.parse(scanner.nextLine());
                isValid = false;
            } catch (DateTimeException exception) {
                System.out.println(" Enter valid date : ");
                isValid = true;
            }
        } while (isValid);
        return dateOfBirth;
    }

    /**
     * Gets the address of the employee and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Address data of the employee.
     */
    public String getAddressDetail(String fieldName) {
        String addressDetail;
        boolean isValid;
        do {
            System.out.print("Enter the " + fieldName + ": ");
            addressDetail = scanner.nextLine();
            isValid = employeeController.validateField(
                              EmployeeManagementConstant.
                              REGEX_NAME, addressDetail);
            if (!(isValid)) {
                System.out.println("Invalid " + fieldName);
            }
        } while (!(isValid));
        return addressDetail;
    }

    /**
     * Gets Employee data and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Name of the employee.
     */
    public String getName(String fieldName) {
        String name;
        boolean isValid;
        do {
            System.out.print("Enter the " + fieldName + ": ");
            name = scanner.nextLine();
            isValid = employeeController.validateField(
                              EmployeeManagementConstant.REGEX_NAME, name);
            if (!(isValid)) {
                System.out.println("Invalid " + fieldName);
            }
        } while (!(isValid));
        return name;
    }
                
}