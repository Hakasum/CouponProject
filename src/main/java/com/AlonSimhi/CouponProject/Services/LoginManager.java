package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoginManager {
    private ApplicationContext ctx;

    public LoginManager(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * A method that checks the credentials provided against the database of the provided type and logs in if the credentials match.
     * @param email
     * @param password
     * @param clientType
     * @return the Facade of the fitting type if logged in.
     * @throws SQLException
     * @throws ClientLoginException
     */

    public ClientServices login(String email, String password, ClientType clientType) throws ClientLoginException {
        switch (clientType) {
            case Administrator: {
                AdminService adminService = ctx.getBean(AdminService.class);
                if (adminService.login(email, password))
                    return adminService;
                break;
            }
            case Company: {
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if(companyService.login(email, password)) return companyService;
                break;
            }
            case Customer: {
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if(customerService.login(email, password)) return customerService;
                break;
            }
        }
        return null;
    }
}
