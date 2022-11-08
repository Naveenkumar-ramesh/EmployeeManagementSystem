package com.ideas2it.employee.model;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * It presents employee details
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private double salary;
    private LocalDate dateOfJoining;
    private List<Address> addresses;
    private LocalDate dateOfBirth;
    private String gender;
    private String role;
    private List<Project> projects;

    public Employee() {
    }

    public Employee(int employeeId, String firstName,String lastName,
                    String email, long phoneNumber,double salary,
                    LocalDate dateOfJoining, List<Address> addresses,
                    LocalDate dateOfBirth, String gender, String role, List<Project> projects)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.addresses = addresses;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.role = role;
        this.projects = projects;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Overiding toString() method
     * In thee toString method string builder is used to
     * concadinate the variables and return it.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nEmployee Id           : ").append(employeeId)
                     .append("\nFirst Name            : ").append(firstName)
                     .append("\nLast Name             : ").append(lastName)
                     .append("\nEmail                 : ").append(email)
                     .append("\nPhoneNumber           : ").append(phoneNumber)
                     .append("\nSalary                : ").append(salary)
                     .append("\nDate Of Joining       : ").append(dateOfJoining)
                     .append("\nDate Of Birth         : ").append(dateOfBirth)
                     .append("\nGender                : ").append(gender)
                     .append("\nRole                  : ").append(role)
                     .append(getAddresses())
                     .append(getProjects());

        return stringBuilder.toString();
    }
}