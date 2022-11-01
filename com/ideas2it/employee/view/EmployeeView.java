package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.time.DateTimeException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    static Logger logger = LogManager.getLogger(EmployeeView.class);
    EmployeeDTO employeeDTO = new EmployeeDTO();

    /**
     * Create, read, search, update, delete and exit 
     * operations takes place by choice.
     */
    public void chooseOperation() {

        int operations = 0;
        do {
            try {
                System.out.println("Enter the opertaion to be done \n\n1.CREATE"
                                   + "\n2.Display \n3.SEARCH "
                                   + "\n4.UPDATE \n5.DELETE \n6.EXIT");
                operations = Integer.valueOf(scanner.nextLine());
                switch (operations) {
                    case 1:
                        this.createEmployee();
                        break;

                    case 2:
                        this.displayEmployee();
                        break;

                    case 3:
                        this.searchEmployee();
                        break;

                    case 4:
                        this.updateEmployee();
                        break;

                    case 5:
                        this.deleteEmployee();
                        break;

                    case 6:
                        System.out.println("**EXIT**");
                        break;

                    default:
                        System.out.println("Try Again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
            }
        } while (6 != operations);
    }

    /**
     * Employee details are received and added to database
     * and Shows if they are added or not.
     */
    public void createEmployee() {
        List<AddressDTO> addressDTOs = new ArrayList<>();
        System.out.println(EmployeeManagementConstant.VALID_DETAILS);
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        boolean isCheck;
        try {

            int employeeId = 0;
            String firstName = getName("First Name");
            String lastName = getName("Last Name");
            String email = getEmail();
            long phoneNumber = getPhoneNumber();
            double salary = getSalary();
            LocalDate dateOfBirth = getDateOfBirth();
            LocalDate dateOfJoining = getDateOfJoining(dateOfBirth);
            String gender = getName("Gender");
            String role = getName("Role");
            System.out.println("  Enter valid Address Details");

            AddressDTO address = addAddress();
            addressDTOs.add(address);
            address = addAnotherAddress();

            if (address != null)   {
                addressDTOs.add(address);
            }
            isCheck = getResponse();
            if (isCheck)   {
                projectDTOs = getProject(projectDTOs);
            }

            employeeDTO = new EmployeeDTO(employeeId, firstName, lastName,
                                          email, phoneNumber, salary,
                                          dateOfJoining, addressDTOs,dateOfBirth,
                                          gender, role, projectDTOs);

            employeeId = employeeController.addEmployee(employeeDTO);
            logger.info("Employee Details Added for ID = " + employeeId);
            System.out.println("Employee Details Added");
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

        String type = getType();
        String doorNumber = getDoorNumber();
        String street = getAddressDetail("Street Name");
        String city = getAddressDetail("City Name");
        String state = getAddressDetail("State Name");
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
            List<EmployeeDTO> employees = employeeController.getEmployees();
            if(!(employees.isEmpty())) {
                Iterator<EmployeeDTO> iterator = employees.iterator();
                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO = iterator.next();
                    System.out.println(employeeDTO);
                }
            } else {
                logger.info("No Employee details found ");
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
            List<EmployeeDTO> employees = employeeController.
                                           searchEmployee(firstName);
            if(!(employees.isEmpty())) {
                Iterator<EmployeeDTO> iterator = employees.iterator();
                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO = iterator.next();
                    System.out.println(employeeDTO);
                }
                logger.info("Employee details found for First Name = " +
                             firstName);
            } else {
                logger.info("No Employee details found for First Name = " +
                             firstName);
                System.out.println("No Employee details found ");
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
        List<AddressDTO> addressDTOs = new ArrayList<>();
        int employeeId;
        int operations;
        try {
            do {
                System.out.println("Enter existing Employee Id");
                employeeId = getId();
            } while(!(employeeController.isEmployeePresent(employeeId)));

            EmployeeDTO employeeDTO = employeeController.getEmployeeById(employeeId);

            do {
                System.out.println("Enter the opertaion to be done \n\n1.FIRST NAME"
                                   + "\n2.LAST NAME \n3.EMAIL \n4.PHONE NUMBER"
                                   + " \n5.SALARY \n6.DATE OF BIRTH "
                                   + " \n7.DATE OF JOINING \n8.GENDER \n9.ROLE"
                                   + " \n10.ADDRESS \n11.Project \n12.EXIT");
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                    case 1:
                        employeeDTO.setFirstName(getName("First Name"));
                        break;

                    case 2:
                        employeeDTO.setLastName(getName("Last Name"));
                        break;

                    case 3:
                        employeeDTO.setEmail(getEmail());
                        break;

                    case 4:
                        employeeDTO.setPhoneNumber(getPhoneNumber());
                        break;

                    case 5:
                        employeeDTO.setSalary(getSalary());
                        break;

                    case 6:
                        employeeDTO.setDateOfBirth(getDateOfBirth());
                        break;

                    case 7:
                        employeeDTO.setDateOfJoining(getDateOfJoining(employeeDTO.getDateOfBirth()));
                        break;

                    case 8:
                        employeeDTO.setGender(getName("Gender"));
                        break;

                    case 9:
                        employeeDTO.setRole(getName("Role"));
                        break;

                    case 10:
                        employeeDTO.setAddresses(updateAddress(employeeDTO));
                        break;

                    case 11:
                        employeeDTO.setProjects(getProject(employeeDTO.getProjects()));
                        break;

                    case 12:
                        break;

                    default:
                        System.out.println("Wrong choice ,Try again ");
                }
            } while (12 != operations);

            employeeController.updateEmployee(employeeDTO);
            logger.info("Employee details has been updated");
            System.out.println("Employee details has been updated");
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        } catch (NumberFormatException numberFormatError) {
            logger.error("Error while choosing update");
            System.out.println("Error while choosing update , Try again ");
        }
    }

    /**
     * gets employee id and updates the address of the employee.
     *
     * @return list of addresses
     */
    public List<AddressDTO> updateAddress(EmployeeDTO employeeDTO) {
        int operations = 0;
        List<AddressDTO> addressDTOs = employeeDTO.getAddresses();
        String addressType = getType();
        for (AddressDTO addressDTO : addressDTOs) {

            if (addressType.equals(addressDTO.getType())) {
                
                do {

                    try {
                        System.out.println("Enter the opertaion to be done \n\n1.DOORNUMBER"
                                   + "\n2.STREET \n3.CITY "
                                   + "\n4.STATE \n5.PINCODE \n6.EXIT");
                        operations = Integer.valueOf(scanner.nextLine());

                        switch (operations) {
                            case 1:
                                addressDTO.setDoorNumber(getDoorNumber());
                                break;

                            case 2:
                                addressDTO.setStreet(getAddressDetail("Street Name"));
                                break;

                            case 3:
                                addressDTO.setCity(getAddressDetail("City Name"));
                                break;

                            case 4:
                                addressDTO.setState(getAddressDetail("State Name"));
                                break;

                            case 5:
                                addressDTO.setPinCode(getPinCode());
                                break;

                           case 6:
                               break;

                           default:
                               System.out.println("Choose Correct Operation");
                       }
                   } catch (NumberFormatException numberFormatError) {
                       logger.error("Error while choosing address update");
                       System.out.println("Error while choosing address update, Try again");
                   }

                } while (6 != operations);
            }
        }
        return addressDTOs;
    }

    /**
     * Here we get the employee id to which
     * relevant details of the employee are deleted.
     */
    public void deleteEmployee() {

        int employeeId = getId();
        try {
            if (employeeController.isEmployeePresent(employeeId)) {
                employeeController.deleteEmployee(employeeId);
                logger.info("Employee details deleted for ID = " + employeeId);
                System.out.println("Employee details deleted for ID = "
                                    + employeeId);
            } else {
                System.out.println("This Employee ID does not exist");
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
        String email = null;
        boolean isValid = false;
        do {
            try {
                System.out.print(EmployeeManagementConstant.EMPLOYEE_EMAIL);
                email = scanner.nextLine();
                isValid = employeeController.validateField(
                                  EmployeeManagementConstant.REGEX_EMAIL, email);

                if (isValid) {
                    isValid = employeeController.validateEmail(email);
                }
                if (!(isValid)) {
                     System.out.println(EmployeeManagementConstant.VALID_EMAIL);
                }
            } catch (EMSException e) {
                System.out.println(e.getErrorCode() + " " + e.getMessage());
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
        long phoneNumber = 0;
        boolean isValid = false;
        do {
            try {
                System.out.print(EmployeeManagementConstant.EMPLOYEE_NUMBER);
                phoneNumber = Long.parseLong(scanner.nextLine());
                isValid = employeeController.validateField(
                                  EmployeeManagementConstant.REGEX_NUMBER,
                                  Long.toString(phoneNumber));

                if (isValid) {
                    isValid = employeeController.validatePhoneNumber(phoneNumber);
                }
                if (!(isValid)) {
                    System.out.println(EmployeeManagementConstant.VALID_NUMBER);
                }
            } catch (EMSException e) {
               System.out.println(e.getErrorCode() + " " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Phone number");
                isValid = false;
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
    public LocalDate getDateOfJoining(LocalDate dateOfBirth) {
        LocalDate dateOfJoining = null;
        boolean isValid;
        do {
            System.out.print(EmployeeManagementConstant.EMPLOYEE_DOJ);
            try {
                dateOfJoining = LocalDate.parse(scanner.nextLine());
                isValid = true;
            } catch (DateTimeException exception) {
                isValid = false;
            }
            if (isValid) {
                isValid = employeeController.validateJoiningDate(dateOfBirth, dateOfJoining);
            }
            if (!(isValid)) {
                System.out.println("  Invalid Joining Date ");
            }
        } while (!(isValid));
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
                isValid = true;
            } catch (DateTimeException exception) {
                isValid = false;
            }
            if (isValid) {
                isValid = employeeController.validateBirthDate(dateOfBirth);
            }
            if (!(isValid)) {
                System.out.println("Invalid Birth Date");
            }
        } while (!(isValid));
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

    /**
     * Get's another employee address if any to be added
     *
     * @return address
     */
    public AddressDTO addAnotherAddress() {
        boolean isValid = false;
        AddressDTO address = null;

        do {
            System.out.println("Do you wish to add another Address (Y or N) ?");
            String choice = scanner.nextLine();

            if (choice.equals("Y") || choice.equals("y")) {
                address = addAddress();
                isValid = true;
            } else if (choice .equals("N") || choice.equals("n")) {
                System.out.println("No second address added");
                isValid = true;
            } else {
                System.out.println("Invalid choice");
                isValid = false;
            }
        }while (!(isValid));
        return address;
    }

    /**
     * Get's the type of address
     */
    public String getType() {
        String type = null;
        try {
            System.out.println("Enter the opertaion to be done \n\na.TEMPORARY"
                               + "\nb.PERMANANT");
            String choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    type = "temporary";
                    break;

                case "b":
                    type = "permanant";
                    break;

                default:
                    System.out.println("Try Again");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice");
        }
        return type;
    }

    /**
     * Get's the project details for the project id.
     *
     * @return project details.
     */
    public List<ProjectDTO> getProject(List<ProjectDTO> projectDTOs) {
       ProjectDTO projectDTO = null;
       boolean isRepeat;
       try {
           do {
               projectDTO = employeeController.getProject(getProjectID());
               if (null != projectDTO) {
                   System.out.println(projectDTO);
                   projectDTOs.add(projectDTO);
               }
               isRepeat = getResponse();
           } while(isRepeat);
       } catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
       return projectDTOs;
    }

    /**
     * Get the employee id of the employee from the user.
     * @return if the given id is valid it returns the id else ask again.
     */
    public int getProjectID() {
        boolean isValid = true;
        String projectId;
        System.out.println("Enter project id");
        do{
            projectId = scanner.nextLine();
            if(employeeController.validateField(EmployeeManagementConstant.REGEX_ID,
                                                 projectId)) {
                isValid = false;
            } else {
                System.out.println("Invalid Id ");   
            }
        } while (isValid);
        return Integer.valueOf(projectId);
    }

    /**
     * Get's the response for the continuation of the addition
     * of project details .
     *
     * @return boolean
     */
    public boolean getResponse() {
        boolean isRepeat = false;
        boolean isValid;
        System.out.println("You want to add project, press y for yes/ n for no");
        do {
            isValid = true;

            switch (scanner.nextLine()) {
                case "y":
                    isRepeat = true;
                    break;

                case "n":
                    isRepeat = false;
                    break;

                default:
                    isValid = false;
                    System.out.println("Invalid choice ... Try again"); 
            }
        } while (!isValid);
        return isRepeat;
    }
                
}