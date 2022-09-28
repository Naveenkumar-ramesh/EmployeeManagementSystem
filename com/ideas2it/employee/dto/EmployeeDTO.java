package com.ideas2it.employee.dto;

import com.ideas2it.employee.dto.AddressDTO;

import java.time.LocalDate;

/**
 * It presents employee details
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeDTO {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private double salary;
    private LocalDate dateOfJoining;
    private AddressDTO address;
    private LocalDate dateOfBirth;
    private String gender;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, String email,
                       long phoneNumber, double salary,LocalDate dateOfJoining,
                       AddressDTO address,LocalDate dateOfBirth,String gender, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.role = role;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Overiding toString() method
     * In thee toString method string builder is used to
     * concadinate the variables and return it.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nFirst Name            : ").append(firstName)
                     .append("\nLast Name             : ").append(lastName)
                     .append("\nEmail                 : ").append(email)
                     .append("\nPhoneNumber           : ").append(phoneNumber)
                     .append("\nSalary                : ").append(salary)
                     .append("\nDate Of Joining       : ").append(dateOfJoining)
                     .append("\nDate Of Joining       : ").append(dateOfJoining)
                     .append("\nGender                : ").append(gender)
                     .append("\nRole                  : ").append(role)
                     .append(getAddress());

        return stringBuilder.toString();
    }
}