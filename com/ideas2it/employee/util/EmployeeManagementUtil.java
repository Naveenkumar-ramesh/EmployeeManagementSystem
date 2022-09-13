package com.ideas2it.employee.util;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Organizes the utility code segments.
 * 
 * @version 1.0 13-09-2022
 * @author Naveenkumar R
 */
public class EmployeeManagementUtil {

    Scanner scanner = new Scanner(System.in);

    public LocalDate getDate() {
        String date = scanner.nextLine();
        LocalDate dateOfJoining = LocalDate.parse(date);
        return dateOfJoining;
    }
}