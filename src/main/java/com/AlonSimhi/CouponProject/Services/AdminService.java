package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Beans.Customer;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CompanyException;
import com.AlonSimhi.CouponProject.Exceptions.CustomerException;
import com.AlonSimhi.CouponProject.Exceptions.NoSuchIdException;
import com.AlonSimhi.CouponProject.Repositories.CompaniesRepository;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Repositories.CustomersRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class AdminService extends ClientServices {
    public AdminService(CustomersRepository customersRepo, CompaniesRepository companiesRepo, CouponsRepository couponsRepo) {
        super(customersRepo, companiesRepo, couponsRepo);
    }
    /**
     * Authenticates the user with the given email and password.
     *
     * @param email
     * @param password
     * @return true if the email and password match the admin's email and password, false otherwise.
     */
    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) return true;
        return false;
    }
    /**
     * Adds a new company to the system.
     *
     * @param company the company to add.
     * @throws CompanyException if the company email or name already exist in the system.
     */
    public void addCompany(Company company) throws CompanyException {
        if (companiesRepo.existsByEmail(company.getEmail()))
            throw new CompanyException("Email already exists");
        if (companiesRepo.existsByName(company.getName()))
            throw new CompanyException("Name already exists");
        companiesRepo.save(company);
    }
    /**
     * Updates an existing company in the system.
     *
     * @param company the company to update.
     * @throws CompanyException if the company ID does not exist in the system, or if the provided name does not match the current name of the company.
     */
    public void updateCompany(Company company) throws CompanyException {
        if (companiesRepo.findById(company.getId()).orElseThrow(() -> new CompanyException("Cannot find a company with this id.")).getName().equals(company.getName())) {
            companiesRepo.save(company);
        } else throw new CompanyException("Id and Name don't match (Cannot Change the name or id of a company)");
    }
    /**
     * Deletes a company from the system.
     *
     * @param companyID the ID of the company to delete.
     */
    public void deleteCompany(int companyID) {
        companiesRepo.deleteById(companyID);
    }
    /**
     * Returns a list of all the companies in the system.
     *
     * @return a list of all the companies.
     */
    public List<Company> getAllCompanies() {
        return companiesRepo.findAll();
    }
    /**
     * Returns a company with the specified ID.
     *
     * @param companyID the ID of the company to retrieve.
     * @return the company with the specified ID.
     * @throws CompanyException if the company with the specified ID does not exist in the system.
     */
    public Company getOneCompany(int companyID) throws CompanyException {
        return companiesRepo.findById(companyID).orElseThrow(() -> new CompanyException("Cannot find a company with this ID"));
    }
    /**
     * Adds a new customer to the system.
     *
     * @param customer the customer to add.
     * @throws CustomerException if the customer with the same Email already exists in the system.
     */
    public void addCustomer(Customer customer) throws CustomerException {
        if (customersRepo.existsByEmail(customer.getEmail()))
            throw new CustomerException("A customer with this Email already exists");
        customersRepo.save(customer);
    }
    /**
     * Updates an existing customer in the system.
     *
     * @param customer the customer to update.
     * @throws CustomerException if the customer with the provided ID does not exist in the system.
     */
    public void updateCustomer(Customer customer) throws CustomerException {
        if (customersRepo.existsById(customer.getId())) {
            customersRepo.save(customer);
        } else throw new CustomerException("Cannot find a customer with this id");
    }
    /**
     * Deletes a customer from the system.
     *
     * @param customerID the ID of the customer to delete.
     */
    public void deleteCustomer(int customerID) throws NoSuchIdException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(NoSuchIdException::new);
        customer.setCoupons(null);
        customersRepo.save(customer);
//      customersRepo.deleteCustomerCouponsPurchase(customerID);
        customersRepo.deleteById(customerID);
    }

    /**
     * Returns a list of all the customers in the system.
     *
     * @return a list of all customers
     */
    public List<Customer> getAllCustomers() {
        return customersRepo.findAll();
    }

    /**
     * Returns a customer with the specified ID.
     *
     * @param customerID the ID of the customer to retrieve.
     * @return the customer with the specified ID.
     * @throws CustomerException if the customer with the specified ID does not exist in the system.
     */
    public Customer getOneCustomer(int customerID) throws CustomerException {
        return customersRepo.findById(customerID).orElseThrow(() -> new CustomerException("Cannot find a customer with this ID"));
    }
}
