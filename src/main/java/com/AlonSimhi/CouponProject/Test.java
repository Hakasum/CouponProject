package com.AlonSimhi.CouponProject;

import com.AlonSimhi.CouponProject.Beans.Company;
import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Beans.Customer;
import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import com.AlonSimhi.CouponProject.Exceptions.CustomerException;
import com.AlonSimhi.CouponProject.Services.*;
import com.AlonSimhi.CouponProject.Threads.CouponsExpirationDailyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class Test {
    @Autowired
    LoginManager loginManager;

    public void testAll() {
        AdminService adminService = null;
        CustomerService customerService = null;
        CompanyService companyService = null;
/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
        //Test #1 -  login (Admin):
        try {
            adminService = (AdminService) loginManager.login("", "", ClientType.Administrator);
        } catch (ClientLoginException e) {
            System.out.println(e.getMessage());
        }

        /** Success! */
        //Test #2 -  addCustomer:
        try {
            adminService.addCustomer(new Customer("Levi", "Ackerman", "levi@aot.com", "1234", null));
            adminService.addCustomer(new Customer("Eren", "Yeager", "eren@aot.com", "1234", null));
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        }


        /** Success! */
        //Test #3 - addCompany:
        adminService.addCompany(new Company("John Bryce", "johnbryce@matrix.com", "johnbryce",));
//            try {
//                adminFacade.addCompany(new Company("John Bryce", "johnbryce@matrix.com", "johnbryce"));
//            }  catch (CompanyExistsException e) {
//                System.out.println(e.getMessage());;
//            }

        /** Success! */
        //Test #3 - deleteCompany, deleteCustomer:
//            adminFacade.deleteCompany(1);
//            adminFacade.deleteCustomer(1);

        /** Success! */
        //Test #4 - getAllCustomers, getAllCompanies:
//            System.out.println(adminFacade.getAllCompanies().toString());
//            System.out.println(adminFacade.getAllCustomers().toString());

        /** Success! */
        //Test #5 - getOneCompany, getOneCustomer:
//            Company company1 = adminFacade.getOneCompany(2);
//            company1.setEmail("microsoft@microsoft.com");
//            Customer customer1 = adminFacade.getOneCustomer(3);
//            customer1.setEmail("somenew@email.com");

        /** Success! */
        //Test #6 - updateCustomer, updateCompany:
//            try {
//                adminFacade.updateCompany(company1);
//                adminFacade.updateCustomer(customer1);
//            } catch (CompanyUpdateException | CustomerUpdateException | NoSuchIdException e) {
//                System.out.println(e.getMessage());
//            }

        /** Success! */
        //Test #7 - Login:
//            System.out.println(adminFacade.login("admin@admin.com", "admin"));

        /** Success! */
        //Test #8 - deleteCoupon:
//            adminFacade.deleteCoupon(7);

/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

        //Testing of the CompanyFacade

//            CompanyFacade companyFacade =(CompanyFacade) LoginManager.getInstance().login("apple@apple.com", "apple", ClientType.Company);

        /** Success! */
        //Test #1 - addCoupon:
//            try {
//                companyFacade.addCoupon(new Coupon(companyFacade.getCompanyDetails().getId(), Category.ELECTRICITY.getId(), "Coupon 4", "coupon1", new Date(2023-1900, 0, 24), new Date(2023-1900,1,30), 5, 100, ""));
//                companyFacade.addCoupon(new Coupon(companyFacade.getCompanyDetails().getId(), Category.FOOD.getId(), "Coupon 5", "Coupon 2", new Date(2023-1900, 0, 24), new Date(2023-1900,1,30), 5, 200, ""));
//            }catch (CouponTitleException e) {
//                System.out.println(e.getMessage());
//            }

        /** Success! */
        //Test #2 - deleteCoupon:
//            companyFacade.deleteCoupon(9);

        /** Success! */
        //Test #3 - getCompanyCoupons:
//            System.out.println(companyFacade.getCompanyCoupon(10));
//            System.out.println(companyFacade.getCompanyCoupons());
//            System.out.println(companyFacade.getCompanyCoupons(Category.ELECTRICITY));
//            System.out.println(companyFacade.getCompanyCoupons(150));

        /** Success! */
        //Test #4 - getCompanyDetail:
//            System.out.println(companyFacade.getCompanyDetails());

        /** Success! */
        //Test #5 - login:
//            System.out.println(companyFacade.login("apple@apple.com", "apple"));

        /** Success! */
        //Test #6 - updateCoupon:
//            try {
//                Coupon coupon = companyFacade.getCompanyCoupon(10);
//                coupon.setAmount(10);
//                companyFacade.updateCoupon(coupon);
//            } catch (CouponTitleException e) {
//                System.out.println(e.getMessage());
//            }

/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

        //Testing of the CustomerFacade

//            CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("simhialon@gmail.com", "1234", ClientType.Customer);

        /** Success! */
        //Test #1 -  getCustomerCoupons:
//            System.out.println(customerFacade.getCustomerCoupons());
//            System.out.println(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
//            System.out.println(customerFacade.getCustomerCoupons(150));

        /** Success! */
        //Test #2 - getCustomerDetails:
//            System.out.println(customerFacade.getCustomerDetails());

        /** Success! */
        //Test #3 - login:
//            System.out.println(customerFacade.login("simhialon@gmail.com", "1234"));

        /** Success! */
        //Test #4 - purchaseCoupon:
//            customerFacade.purchaseCoupon(companyFacade.getCompanyCoupon(11));
//            customerFacade.purchaseCoupon(companyFacade.getCompanyCoupon(12));
//
//    } catch (SQLException | ClientLoginException e) {
//        System.out.println(e.getMessage());
//    }

/**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */


        /** Success! */
//        CouponExpirationDailyJob cedj = new CouponExpirationDailyJob();
//        Thread t1 = new Thread(cedj);
//        t1.start();
    }
}
