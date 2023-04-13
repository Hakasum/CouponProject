package com.AlonSimhi.CouponProject.Beans;

public enum Category {
    FOOD(1),
    ELECTRICITY(2),
    VACATION(3);

    private int id;
    Category(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
