package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CompanyException;
import com.AlonSimhi.CouponProject.Exceptions.CouponException;
import com.AlonSimhi.CouponProject.Exceptions.NoSuchIdException;
import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class CompanyService extends ClientServices {
    private int companyID;

    public CompanyService(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        super(customersRepo, companiesRepo, couponsRepo);
    }
    /**
     * Checks if there is a company in the system with the provided credentials.
     *
     * @param email the email of the company
     * @param password the password of the company
     * @return true if the credentials match a company in the system, false otherwise
     */
    @Override
    public boolean login(String email, String password) {
        Company company = companiesRepo.findByEmailAndPassword(email, password);
        if(company!=null) {
            this.companyID = company.getId();
            return true;
        }
        return false;
    }
    /**
     * Adds a new coupon to the system.
     *
     * @param coupon the coupon to add
     * @throws CouponException if there is already a coupon with the same title listed under the same company
     * @throws NoSuchIdException if the company does not exist
     */
    public void addCoupon(Coupon coupon) throws CouponException, NoSuchIdException {
        if (getCompanyCoupons().stream().anyMatch(c -> c.getTitle().equals(coupon.getTitle()))){
            throw new CouponException("Cannot have two coupons with the same title listed under the same company");
        }
        couponsRepo.save(coupon);
    }
    /**
     * Updates an existing coupon in the system.
     *
     * @param coupon the updated coupon to save
     * @throws NoSuchIdException if the coupon does not exist
     * @throws CouponException if the company ID of the coupon does not match the ID of the company that owns the coupon
     */
    public void updateCoupon(Coupon coupon) throws NoSuchIdException, CouponException {
        if(couponsRepo.findById(coupon.getId()).orElseThrow(NoSuchIdException::new).getCompany().getId()==coupon.getCompany().getId()){
            couponsRepo.save(coupon);
        } else throw new CouponException("Cannot Change the companyID of a coupon");
    }
    /**
     * Deletes a coupon from the system.
     *
     * @param couponID the ID of the coupon to delete
     */
    public void deleteCoupon(int couponID){
        couponsRepo.deleteCustomerCouponsPurchase(couponID);
        couponsRepo.deleteById(couponID);
    }
    /**
     * Gets a list of all coupons owned by the currently logged-in company.
     *
     * @return a list of coupons owned by the company
     * @throws NoSuchIdException if the company does not exist
     * @throws CouponException if the company does not have any coupons yet
     */
    public List<Coupon> getCompanyCoupons() throws CouponException, NoSuchIdException {
        List<Coupon> companyCoupons = companiesRepo.findById(companyID).orElseThrow(NoSuchIdException::new).getCoupons();
        if (companyCoupons==null) throw new CouponException("This company does not have any coupons yet");
        return companyCoupons;
    }
    /**
     * Gets a list of all coupons owned by the currently logged-in company that belong to the given category.
     *
     * @param category the category to filter by
     * @return a list of coupons owned by the company in the specified category
     * @throws CouponException if the company does not have any coupons in the specified category
     */
    public List<Coupon> getCompanyCoupons(Category category) throws CouponException {
        List<Coupon> allByCategory = couponsRepo.findAllByCategory(category);
        if (allByCategory == null) throw new CouponException("This company does not have any coupons with '" + category + "' Catagory");
        return allByCategory;
    }
    /**
     * Gets a list of all coupons owned by the currently logged-in company with a price up to the specified maximum.
     *
     * @param maxPrice the maximum price to filter by
     * @return a list of coupons owned by the company with a price up to the specified maximum
     * @throws CouponException if the company does not have any coupons yet
     * @throws NoSuchIdException if the company does not exist
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponException, NoSuchIdException {
        ArrayList<Coupon> couponsUpToMax = getCompanyCoupons().stream().filter(c -> c.getPrice() <= maxPrice).
                sorted(Comparator.comparingDouble(Coupon::getPrice)).
                collect(Collectors.toCollection(ArrayList::new));
        return couponsUpToMax;
    }
    /**
     * Gets the details of the currently logged-in company.
     *
     * @return the company object
     * @throws CompanyException if the company does not exist
     */
    public Company getCompanyDetails() throws CompanyException {
        return companiesRepo.findById(companyID).orElseThrow(()->new CompanyException("A company with this id does not exist"));
    }
}
