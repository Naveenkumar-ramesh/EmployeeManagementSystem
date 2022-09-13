package com.ideas2it.employee.model;

import java.time.LocalDate;

/**
 * It presents employee details
 *
 * @version 1.8
 * @author Naveenkumar R
 */
public class Employee {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfJoining;
    private double salary;
    private String bloodGroup;
    private Address address;

    public Employee() {
    }

    public Employee(String id, String name, String email, String phoneNumber, 
                          LocalDate dateOfJoining, double salary, String bloodGroup, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.bloodGroup = bloodGroup;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Overiding toString() method
     * In thee toString method string builder is used to
     * concadinate the variables and return it.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nId              : ").append(id)
                     .append("\nName            : ").append(name)
                     .append("\nEmail           : ").append(email)
                     .append("\nPhoneNumber     : ").append(phoneNumber)
                     .append("\nDate Of Joining : ").append(dateOfJoining)
                     .append("\nSalary          : ").append(salary)
                     .append("\nBlood Group     : ").append(bloodGroup)
                     .append("\nEmployee Address ").append(address);
        return stringBuilder.toString();
    }
}