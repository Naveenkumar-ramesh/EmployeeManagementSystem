package com.ideas2it.employee.constant;

/**
 * Declare the constants 
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeManagementConstant {

    public static final String EMPLOYEE_FIRST_NAME = "Enter the Employee First Name : ";
    public static final String VALID_FIRST_NAME = "Invalid First Name ";
    public static final String EMPLOYEE_LAST_NAME = "Enter the Employee Last Name   : ";
    public static final String VALID_LAST_NAME = "Invalid Last Name ";
    public static final String VALID_DETAILS = "Enter validEmployee details ";
    public static final String VALID_SALARY = "Invalid Employee salary ";
    public static final String VALID_DATE = "Invalid date";
    public static final String EMPLOYEE_PINCODE = "Enter the PinCode                : ";
    public static final String VALID_PINCODE = "Invalid PinCode  ";
    public static final String EMPLOYEE_ID = "Enter the Employee Id                 : ";
    public static final String VALID_ID = "Invalid Employee Id : ";
    public static final String EMPLOYEE_NUMBER = "Enter the Phone Number            : ";
    public static final String VALID_NUMBER = "Invalid Employee Phone Number";
    public static final String EMPLOYEE_DOJ = "Enter the Date of Joining (yyyy-mm-dd): ";
    public static final String EMPLOYEE_DOB = "Enter the Date of Birth (yyyy-mm-dd)  : ";
    public static final String EMPLOYEE_EMAIL = "Enter the Employee Email ID : ";
    public static final String VALID_EMAIL = "Invalid Employee Email ID";
    public static final String EMPLOYEE_GENDER = "Enter the Employee Gender          : ";
    public static final String EMPLOYEE_SALARY = "Enter the Employee salary (RRRR.PP): ";
    public static final String EMPLOYEE_ROLE = "Enter the Employee Role              : ";
    public static final String EMPLOYEE_DOOR_NUMBER = "Enter the Door Number         : ";
    public static final String VALID_DOOR_NUMBER = "Invalid Door Number ";
    public static final String REGEX_FIRST_NAME = "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";
    public static final String REGEX_LAST_NAME = "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";      
    public static final String REGEX_NAME = "^[a-zA-Z]{1,15}([ ]?[a-zA-Z]{0,15}){0,}$";   
    public static final String REGEX_NUMBER = "^[6-9]{1}[0-9]{9}$";
    public static final String REGEX_ID = "^[0-9]{1,}$";
    public static final String REGEX_SALARY = "^[0-9]{3,}([.]{1}[0-9]{2})?$";
    public static final String REGEX_ADDRESS = "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";
    public static final String REGEX_PINCODE = "^([0-9]{6})$";
    public static final String REGEX_DOOR_NUMBER = "([no|No|NO]?[#]?[A-Za-z]*[-/:]?[0-9]+[-/:]?[0-9]*)";
    public static final String REGEX_EMAIL = "^[a-zA-Z]{1}[0-9a-zA-Z]{0,15}?[.\\-_]"
                                            .concat("?[a-zA-Z0-9]{1,20}")
                                            .concat("[@][a-z]{1,20}[a-z0-9]")
                                            .concat("{0,10}[.][a-z]{2,3}[.]?[a-z]{1,3}$");

}