package com.ideas2it.employee.mapper;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;

/**
 * Converts DTO Employee details to model Employee details and 
 * model Employee details to DTO Employee details.
 * 
 * @version 1.8 15-09-2022
 * @author Naveenkumar R
 */
public class EmployeeMapper {

    /**
     * Convert model Employee details to DTO Employee details
     *
     * @param employeeDTO 
     * @return employee
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setDateOfJoining(employee.getDateOfJoining());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setBloodGroup(employee.getBloodGroup());
        employeeDTO.setAddress(toAddressDTO(employee.getAddress()));

        return employeeDTO;

    }


    /**
     * Convert DTO Employee details to model Employee details
     *
     * @param employee 
     * @return employeeDTO
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setSalary(employeeDTO.getSalary());
        employee.setBloodGroup(employeeDTO.getBloodGroup());
        employee.setAddress(toAddress(employeeDTO.getAddress()));

        return employee;

    }

    /**
     * Convert model address to DTO address
     *
     * @param addressDTO 
     * @return address
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());

        return addressDTO;

    }

    /**
     * Convert DTO address to model address
     *
     * @param address
     * @return addressDTO
     */
    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());

        return address;

    }

}
