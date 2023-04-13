package com.AlonSimhi.CouponProject.Repositories;

import com.AlonSimhi.CouponProject.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    public boolean existsByEmailAndPassword(String email, String password);
    public Customer findByEmail(String email);
}
