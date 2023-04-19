package com.AlonSimhi.CouponProject.Repositories;

import com.AlonSimhi.CouponProject.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    @Modifying
    @Query(value = "DELETE FROM customers_vs_coupons WHERE customer_id=:customerID", nativeQuery = true)
    public void deleteCustomerCouponsPurchase(int customerID);
    @Modifying
    @Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (:customerId, :couponId)", nativeQuery = true)
    public void addCouponPurchase(@Param("customerId") int customerId, @Param("couponId") int couponId);
    public Customer findByEmailAndPassword(String email, String password);
    public boolean existsByEmail(String email);
}
