package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientServices {
    protected CustomersRepository customersRepo;
    protected CompaniesRepository companiesRepo;
    protected CouponsRepository couponsRepo;

    public ClientServices(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        this.customersRepo = customersRepo;
        this.companiesRepo = companiesRepo;
        this.couponsRepo = couponsRepo;
    }

    public abstract boolean login(String email, String password);
}
