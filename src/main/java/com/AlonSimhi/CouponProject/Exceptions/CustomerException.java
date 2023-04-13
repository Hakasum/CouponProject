package com.AlonSimhi.CouponProject.Exceptions;

public class CustomerException extends Exception {
    /**
     * An exception that prevents the creation or update of a customer with an existing email
     */
    public CustomerException(String message) {
        super(message);
    }
}
