package com.AlonSimhi.CouponProject.Repositories;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponsRepository extends JpaRepository<Coupon, Integer> {
    public List<Coupon>  deleteByEndDateBefore(Date currentDate);
    public List<Coupon> findAllByCategory(Category category);
}
