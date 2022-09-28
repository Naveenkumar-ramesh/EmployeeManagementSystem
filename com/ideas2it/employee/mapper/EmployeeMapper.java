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

        //employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDateOfJoining(employee.getDateOfJoining());
        employeeDTO.setAddress(toAddressDTO(employee.getAddress()));
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setRole(employee.getRole());

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

        //employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setAddress(toAddress(employeeDTO.getAddress()));
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(employeeDTO.getGender());
        employee.setRole(employeeDTO.getRole());

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

        //addressDTO.setAddressId(address.getAddressId());
        //addressDTO.setEmployeeId(address.getEmployeeId());
        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setType(address.getType());

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

        //address.setAddressId(addressDTO.getAddressId());
        //address.setEmployeeId(addressDTO.getEmployeeId());
        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        address.setType(addressDTO.getType());

        return address;

    }

}
