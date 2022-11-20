package com.ideas2it.employee.util;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Organizes the utility code segments.
 * 
 * @version 1.0 13-09-2022
 * @author Naveenkumar R
 */
public class ValidationUtil {

    static Logger logger = LogManager.getLogger(ValidationUtil.class);

    /**
     * Employee details are validated returns true if valid.
     *
     * @param Employee field 
     * @return true if date is valid.
     */
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
        LocalDate currentDate = LocalDate.now();
        return (18 <= Period.between(dateOfBirth, currentDate).getYears()
                        && Period.between(dateOfBirth, currentDate).
                        getYears() <= 60);
    }

    /**
     * Project starting date is validated to not be in past.
     *
     * @param starting date
     * @return true if starting date is valid.
     */
    public boolean isValidStartDate(String startDate) {
        boolean isValid = false;
    
        try {
            LocalDate startingDate = LocalDate.parse(startDate);
            LocalDate currentDate = LocalDate.now();

            if ((currentDate.compareTo(startingDate)) <= 0) {
                isValid = true;
            }
        } catch (DateTimeParseException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }

    /**
     * Project ending date is validated according to starting date.
     *
     * @param starting date and end date
     * @return true if end date is valid.
     */
    public boolean isValidDate(LocalDate startDate, String date) {
        boolean isValid = false;
    
        try {
            LocalDate localDate = LocalDate.parse(date);

            if ((startDate.compareTo(localDate)) < 0) {
                isValid = true;
            }
        } catch (DateTimeParseException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }
}