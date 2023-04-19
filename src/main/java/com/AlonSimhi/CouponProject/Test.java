package com.AlonSimhi.CouponProject;

import com.AlonSimhi.CouponProject.Beans.Category;
import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Beans.Customer;
import com.AlonSimhi.CouponProject.Exceptions.NoSuchIdException;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Services.*;
import com.AlonSimhi.CouponProject.Threads.CouponsExpirationDailyJob;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class Test {
    private ApplicationContext ctx;
    private LoginManager loginManager;
    private final CouponsRepository couponsRepository;

    public Test(ApplicationContext ctx, LoginManager loginManager,
                CouponsRepository couponsRepository) {
        this.ctx = ctx;
        this.loginManager = loginManager;
        this.couponsRepository = couponsRepository;
    }

    public void testAll() {
        try {
            /** Success! */
            //Test #1 -  login (Admin):
//            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

            /** Success! */
            //Test #2 -  addCustomer:
//            adminService.addCustomer(new Customer("Levi", "Ackerman", "levi@aot.com", "1234", null));
//            adminService.addCustomer(new Customer("Eren", "Yeager", "eren@aot.com", "1234", null));
//            adminService.addCustomer(new Customer("Alon", "Simhi", "simhialon@gmail.com", "1234", null));
//            adminService.addCustomer(new Customer("Mikasa", "Ackerman", "mikasa@aot.com", "1234", null));

            /** Success! */
            //Test #3 - addCompany:
//            adminService.addCompany(new Company("John Bryce", "johnbryce@matrix.com", "johnbryce", null));
//            adminService.addCompany(new Company("Apple", "apple@apple.com", "apple", null));
//            adminService.addCompany(new Company("Lego", "Lego@Lego.com", "Lego", null));

            /** Success! */
            //Test #4 - getOneCompany, getOneCustomer:
//            Company company1 = adminService.getOneCompany(2);
//            company1.setEmail("update");
//            Customer customer1 = adminService.getOneCustomer(2);
//            customer1.setEmail("updated");

            /** Success! */
//            //Test #5 - updateCustomer, updateCompany:
//            adminService.updateCompany(company1);
//            adminService.updateCustomer(customer1);

            /** Success! */
//            //Test #6 - deleteCompany, deleteCustomer:
//            adminService.deleteCompany(1);
//            adminService.deleteCustomer(1);

            /** Success! */
//            //Test #7 - getAllCustomers, getAllCompanies:
//            System.out.println(adminService.getAllCompanies().toString());
//            System.out.println(adminService.getAllCustomers().toString());

/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

            //Testing of the CompanyService

//            CompanyService companyService = (CompanyService) loginManager.login("Lego@Lego.com", "Lego", ClientType.Company);

            /** Success! */
            //Test #1 - addCoupon:
//            companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.ELECTRICITY, "Coupon 1", "coupon 1", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 29), 5, 100, ""));
//            companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.FOOD, "Coupon 2", "Coupon 2", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 29), 5, 120, ""));
//            companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.ELECTRICITY, "Coupon 3", "coupon 3", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 29), 5, 150, ""));
//            companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.FOOD, "Coupon 4", "Coupon 4", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 29), 5, 200, ""));
//            companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.FOOD, "Coupon 5", "Coupon 5", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 29), 5, 200, ""));

            /** Success! */
            //Test #2 - deleteCoupon:
//            companyService.deleteCoupon(1);

            /** Success! */
            //Test #3 - updateCoupon:
//            Coupon coupon = companyService.getCompanyCoupons().stream().filter(c -> c.getTitle().equals("Coupon 4")).findFirst().orElse(null);
//            if (coupon != null) {
//                coupon.setAmount(10000);
//                companyService.updateCoupon(coupon);
//            }

            /** Success! */
            //Test #4 - getCompanyCoupons:
//            System.out.println(companyService.getCompanyCoupons());
//            System.out.println(companyService.getCompanyCoupons(Category.FOOD));
//            System.out.println(companyService.getCompanyCoupons(150));

            /** Success! */
            //Test #5 - getCompanyDetail:
//            System.out.println(companyService.getCompanyDetails());
/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

            //Testing of the customerService

//            CustomerService customerService = (CustomerService) loginManager.login("simhialon@gmail.com", "1234", ClientType.Customer);

            /** Success! */
            //Test #1 - getCustomerDetails:
//            System.out.println(customerService.getCustomerDetails());

            /** Success! */
            //Test #2 - purchaseCoupon:
//            for (Coupon c : companyService.getCompanyCoupons()) {
//                customerService.purchaseCoupon(c);
//            }

            /** Success! */
            //Test #3 - getCustomerCoupons:
//            System.out.println(customerService.getCustomerCoupons());
//            System.out.println(customerService.getCustomerCoupons(Category.ELECTRICITY));
//            System.out.println(customerService.getCustomerCoupons(150));

/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
//          companyService.addCoupon(new Coupon(companyService.getCompanyDetails(), Category.ELECTRICITY, "Coupon x", "coupon 1", new Date(2023 - 1900, 0, 24), new Date(2023 - 1900, 3, 18), 5, 100, ""));

            CouponsExpirationDailyJob job = ctx.getBean(CouponsExpirationDailyJob.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
