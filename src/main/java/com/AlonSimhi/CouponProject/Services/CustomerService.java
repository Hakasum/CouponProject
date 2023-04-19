package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Beans.Customer;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CouponException;
import com.AlonSimhi.CouponProject.Exceptions.CustomerException;
import com.AlonSimhi.CouponProject.Exceptions.NoSuchIdException;
import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class CustomerService extends ClientServices{
    private Customer customer;

    public CustomerService(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        super(customersRepo, companiesRepo, couponsRepo);
    }
    /**
     * Checks if there is a customer in the system with the provided credentials.
     *
     * @param email the email of the customer
     * @param password the password of the customer
     * @return true if the credentials match a customer in the system, false otherwise
     */
    @Override
    public boolean login(String email, String password) {
        customer = customersRepo.findByEmailAndPassword(email, password);
        if (customer!=null)return true;
        return false;
    }
    /**
     * Purchase a coupon for the current customer and update it in the database.
     *
     * @param coupon
     * @throws NoSuchIdException if the customer does not exist
     * @throws CustomerException if the customer tries to buy the same coupon more than once
     * @throws CouponException if the coupon is out of stock
     */
    public void purchaseCoupon(Coupon coupon) throws NoSuchIdException, CustomerException, CouponException {
        if (getCustomerCoupons().stream().anyMatch(c->c.getId()==coupon.getId())) throw new CustomerException("Cannot purchase a coupon more than once");
        if (coupon.getAmount()==0) throw new CouponException("This coupon is out of stock");
        if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) throw new CouponException("Coupon expired");
        coupon.setAmount(coupon.getAmount()-1);
        couponsRepo.save(coupon);
        customer.getCoupons().add(coupon);
        customersRepo.addCouponPurchase(customer.getId(), coupon.getId());
    }
    /**
     * Retrieves all coupons for the current customer.
     *
     * @return A list of all the customer's coupons.
     */
    public List<Coupon> getCustomerCoupons() {
        return getCustomerDetails().getCoupons();
    }
    /**
     * Retrieves all coupons of a specific category for the current customer.
     *
     * @param category The category to filter the coupons by.
     * @return A list of all the customer's coupons within the given category.
     * @throws CustomerException if the customer has not purchased any coupons yet.
     */
    public List<Coupon> getCustomerCoupons(Category category) throws CustomerException {
        List<Coupon> categoryCoupons = getCustomerCoupons().stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
        if (categoryCoupons==null) throw new CustomerException("Customer has no Coupons yet");
        return categoryCoupons;
    }
    /**
     * Retrieves all coupons up to a certain maximum price for the current customer.
     *
     * @param maxPrice The maximum price to filter the coupons by.
     * @return A list of all the customer's coupons up to the given maximum price, sorted by price in ascending order.
     */
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        ArrayList<Coupon> couponsUpToMax = getCustomerCoupons().stream().filter(c -> c.getPrice() <= maxPrice).
                sorted(Comparator.comparingDouble(Coupon::getPrice)).
                collect(Collectors.toCollection(ArrayList::new));
        return couponsUpToMax;
    }
    /**
     * Retrieves the details of the current customer.
     *
     * @return The current customer object.
     */
    public Customer getCustomerDetails() {
        return customer;
    }
}
