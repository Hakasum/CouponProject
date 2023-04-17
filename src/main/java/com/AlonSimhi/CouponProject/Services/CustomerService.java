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
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ClientServices{
    private Customer customer;

    public CustomerService(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        super(customersRepo, companiesRepo, couponsRepo);
    }

    @Override
    public boolean login(String email, String password) {
        customer = customersRepo.findByEmailAndPassword(email, password);
        if (customer!=null)return true;
        return false;
    }
    public void purchaseCoupon(Coupon coupon) throws NoSuchIdException, CustomerException, CouponException {
        if (getCustomerCoupons().stream().anyMatch(c->c.getId()==coupon.getId())) throw new CustomerException("Cannot purchase a coupon more than once");
        if (coupon.getAmount()==0) throw new CouponException("This coupon is out of stock");
        if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) throw new CouponException("Coupon expired");
        coupon.setAmount(coupon.getAmount()-1);
        couponsRepo.save(coupon);
        customer.getCoupons().add(coupon);
        customersRepo.save(customer);
    }
    public List<Coupon> getCustomerCoupons() {
        return getCustomerDetails().getCoupons();
    }
    public List<Coupon> getCustomerCoupons(Category category) throws CustomerException {
        List<Coupon> categoryCoupons = getCustomerCoupons().stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
        if (categoryCoupons==null) throw new CustomerException("Customer has no Coupons yet");
        return categoryCoupons;
    }
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        ArrayList<Coupon> couponsUpToMax = getCustomerCoupons().stream().filter(c -> c.getPrice() <= maxPrice).
                sorted(Comparator.comparingDouble(Coupon::getPrice)).
                collect(Collectors.toCollection(ArrayList::new));
        return couponsUpToMax;
    }
    public Customer getCustomerDetails() {
        return customer;
    }
}
