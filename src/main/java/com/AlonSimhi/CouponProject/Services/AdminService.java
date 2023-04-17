package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Customer;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CompanyException;
import com.AlonSimhi.CouponProject.Exceptions.CustomerException;
import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ClientServices {
    public AdminService(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        super(customersRepo, companiesRepo, couponsRepo);
    }

    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) return true;
        return false;
    }
    public void addCompany(Company company) throws CompanyException {
        if (companiesRepo.existsByEmail(company.getEmail()))
            throw new CompanyException("Email already exists");
        if (companiesRepo.existsByName(company.getName()))
            throw new CompanyException("Name already exists");
        companiesRepo.save(company);
    }
    public void updateCompany(Company company) throws CompanyException {
        if (companiesRepo.findById(company.getId()).orElseThrow(() -> new CompanyException("Cannot find a company with this id.")).getName().equals(company.getName())) {
            companiesRepo.save(company);
        } else throw new CompanyException("Id and Name don't match (Cannot Change the name or id of a company)");
    }
    public void deleteCompany(int companyID) {
        companiesRepo.deleteById(companyID);
    }
    public List<Company> getAllCompanies() {
        return companiesRepo.findAll();
    }
    public Company getOneCompany(int companyID) throws CompanyException {
        return companiesRepo.findById(companyID).orElseThrow(() -> new CompanyException("Cannot find a company with this ID"));
    }
    public void addCustomer(Customer customer) throws CustomerException {
        if (customersRepo.existsById(customer.getId()))
            throw new CustomerException("A customer with this ID already exists");
        customersRepo.save(customer);
    }
    public void updateCustomer(Customer customer) throws CustomerException {
        if (customersRepo.existsById(customer.getId())) {
            customersRepo.save(customer);
        } else throw new CustomerException("Cannot find a customer with this id");
    }
    public void deleteCustomer(int customerID) {
        customersRepo.deleteById(customerID);
    }
    public List<Customer> getAllCustomers() {
        return customersRepo.findAll();
    }
    public Customer getOneCustomer(int customerID) throws CustomerException {
        return customersRepo.findById(customerID).orElseThrow(() -> new CustomerException("Cannot find a customer with this ID"));
    }
}
