package com.AlonSimhi.CouponProject.Repositories;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface CouponsRepository extends JpaRepository<Coupon, Integer> {
    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE coupons_id=:couponID", nativeQuery = true)
    public void deleteCustomerCouponsPurchase(int couponID);
    public List<Coupon> findByEndDateBefore(Date endDate);
    public List<Coupon> findAllByCategory(Category category);
}
