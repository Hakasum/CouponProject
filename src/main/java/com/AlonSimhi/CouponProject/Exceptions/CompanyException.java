package com.AlonSimhi.CouponProject.Exceptions;

public class CompanyException extends Exception {
    /**
     * An exception that disallows the adding of a company that has a name or email identical to that of an existing email
     * @param message
     */
    public CompanyException(String message) {
        super(message);
    }
}
