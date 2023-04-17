package com.AlonSimhi.CouponProject.Exceptions;

public class CouponException extends Exception{
    /**
     * An exception that prevents a company from having 2 coupons with identical title
     */
    public CouponException(String message) {
        super(message);
    }
}
