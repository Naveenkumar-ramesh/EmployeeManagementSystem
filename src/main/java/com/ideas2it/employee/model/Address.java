package com.ideas2it.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

/**
 * It presents employee address.
 *
 * @version 2.0 13-09-2022
 * @author Naveenkumar R
 */

@Entity
@Component
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String doorNumber;
	private String street;
	private String city;
	private String state;
	private int pinCode;
	private String type;

	public Address() {
	}

	public Address(String doorNumber, String street, String city, String state, int pinCode, String type) {
		this.doorNumber = doorNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.type = type;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		StringBuilder stringBuilderAddress = new StringBuilder();
		stringBuilderAddress.append("\nDoorNumber            : ").append(doorNumber)
				.append("\nStreet                : ").append(street).append("\nCity                  : ").append(city)
				.append("\nState                 : ").append(state).append("\nPincode               : ").append(pinCode)
				.append("\nType                  : ").append(type);

		return stringBuilderAddress.toString();
	}
}
