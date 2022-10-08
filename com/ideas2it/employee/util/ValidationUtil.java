package com.ideas2it.employee.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Organizes the utility code segments.
 * 
 * @version 1.0 13-09-2022
 * @author Naveenkumar R
 */
public class ValidationUtil {

    public static boolean isValid(String regexPattern, String fieldValue) {
        return Pattern.matches(regexPattern, fieldValue);
    }

    /**
     * Gets the birth date and joining date and validates if the joining date is valid
     *
     * @param birth date and joining date
     * @return true if joining date is valis
     */
    public static boolean validateJoiningDate(LocalDate dateOfBirth,LocalDate dateOfJoining) {
        boolean isValid = true;
        LocalDate currentDate = LocalDate.now();
        int compareValue = currentDate.compareTo(dateOfJoining);
        if (!(compareValue >= 0)) {
            isValid = false;
        }

        if (18 >  Period.between(dateOfBirth, dateOfJoining).getYears()) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Gets the birth date and validates if the joining date is valid
     *
     * @param birth date
     * @return true if birth date is valid
     */
    public static boolean validateBirthDate(LocalDate dateOfBirth) {
        boolean isValid = true;
        LocalDate currentDate = LocalDate.now();
        return isValid = (18 <= Period.between(dateOfBirth, currentDate).getYears()
                        && Period.between(dateOfBirth, currentDate).
                        getYears() <= 60);
    }

}