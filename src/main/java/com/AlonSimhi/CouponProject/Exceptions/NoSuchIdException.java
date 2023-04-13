package com.AlonSimhi.CouponProject.Exceptions;

public class NoSuchIdException extends Exception{
    /**
     * An exception that detects if a non-existing id is being passed for the update of an object.
     */
    public NoSuchIdException() {
        super("This ID is not in the system");
    }
}
