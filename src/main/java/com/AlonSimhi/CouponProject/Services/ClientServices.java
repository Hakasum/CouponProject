package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientServices {
    @Autowired
    protected CustomersRepository customersRepo;
    @Autowired
    protected CompaniesRepository companiesRepo;
    @Autowired
    protected CouponsRepository couponsRepo;

    public abstract boolean login(String email, String password);
}
