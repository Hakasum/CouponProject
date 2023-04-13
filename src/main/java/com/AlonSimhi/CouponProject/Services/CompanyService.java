package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CompanyException;
import com.AlonSimhi.CouponProject.Exceptions.CouponException;
import com.AlonSimhi.CouponProject.Exceptions.NoSuchIdException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService extends ClientServices {
    private int companyID;

    public CompanyService(String email, String password) throws ClientLoginException {
        if (!login(email, password)){
            throw new ClientLoginException();
        }
        this.companyID = companiesRepo.findByEmail(email).getId();
    }
    @Override
    public boolean login(String email, String password) {
        if(companiesRepo.existsByEmailAndPassword(email, password)) return true;
        return false;
    }
    public void addCoupon(Coupon coupon) throws CouponException, NoSuchIdException {
        if (getCompanyCoupons().stream().anyMatch(c -> c.getTitle().equals(coupon.getTitle()))){
            throw new CouponException("Cannot have two coupons with the same title listed under the same company");
        }
        couponsRepo.save(coupon);
    }
    public void updateCoupon(Coupon coupon) throws NoSuchIdException, CouponException {
        if(couponsRepo.findById(coupon.getId()).orElseThrow(NoSuchIdException::new).getCompany().getId()==coupon.getCompany().getId()){
            couponsRepo.save(coupon);
        } throw new CouponException("Cannot Change the companyID of a coupon");
    }
    public void deleteCoupon(int couponID){
        couponsRepo.deleteById(couponID);
    }
    public List<Coupon> getCompanyCoupons() throws NoSuchIdException, CouponException {
        ArrayList<Coupon> companyCoupons = companiesRepo.findById(companyID).orElseThrow(NoSuchIdException::new).getCoupons();
        if (companyCoupons==null) throw new CouponException("This company does not have any coupons yet");
        return companyCoupons;
    }
    public List<Coupon> getCompanyCoupons(Category category) throws CouponException {
        List<Coupon> allByCategory = couponsRepo.findAllByCategory(category);
        if (allByCategory == null) throw new CouponException("This company does not have any coupons with '" + category + "' Catagory");
        return allByCategory;
    }
    public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponException, NoSuchIdException {
        ArrayList<Coupon> couponsUpToMax = getCompanyCoupons().stream().filter(c -> c.getPrice() <= maxPrice).
                sorted(Comparator.comparingDouble(Coupon::getPrice)).
                collect(Collectors.toCollection(ArrayList::new));
        return couponsUpToMax;
    }
    public Company getCompanyDetails() throws CompanyException {
        return companiesRepo.findById(companyID).orElseThrow(()->new CompanyException("A company with this id does not exist"));
    }
}
