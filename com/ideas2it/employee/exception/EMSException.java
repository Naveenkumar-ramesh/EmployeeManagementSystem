package com.ideas2it.employee.exception;

/**
 * Here custom exception is used to handle multiple error codes
 */
public class EMSException extends Exception {

    private String  errorCode;

    public EMSException(String exception, String errorCode) {
        super(exception);
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
