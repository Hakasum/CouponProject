package com.AlonSimhi.CouponProject.Repositories;

import com.AlonSimhi.CouponProject.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Integer> {
    public boolean existsByEmail(String email);
    public boolean existsByName(String name);
    public Company findByEmail(String email);
    public boolean existsByEmailAndPassword(String email, String password);
}
