package com.AlonSimhi.CouponProject.Beans;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private int amount;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title, description, image;
    private Date startDate, endDate;
    private double price;

    public Coupon() {
    }

    public Coupon(Company company, int amount, Category category, String title, String description, String image, Date startDate, Date endDate, double price) {
        this.company = company;
        this.amount = amount;
        this.category = category;
        this.title = title;
        this.description = description;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Coupon(int id, Company company, int amount, Category category, String title, String description, String image, Date startDate, Date endDate, double price) {
        this.id = id;
        this.company = company;
        this.amount = amount;
        this.category = category;
        this.title = title;
        this.description = description;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category categoryId) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
