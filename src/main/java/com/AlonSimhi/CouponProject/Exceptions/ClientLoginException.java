package com.AlonSimhi.CouponProject.Exceptions;

public class ClientLoginException extends Exception {
    /**
     * An exception that indicates that the combination of email and password do not match in the database.
     */
    public ClientLoginException() {
        super("Wrong email/password");
    }
}
