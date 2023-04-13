package com.AlonSimhi.CouponProject.Services;

import com.AlonSimhi.CouponProject.Exceptions.ClientLoginException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoginManager {
//    private static LoginManager instance;

    private LoginManager() {
    }

//    public static LoginManager getInstance() {
//        if (instance == null) {
//            instance = new LoginManager();
//        }
//        return instance;
//    }

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
                return new AdminService(email, password);
            }
            case Company: {
                return new CompanyService(email, password);
            }
            case Customer: {
                return new CustomerService(email, password);
            }
            default: return null;
        }
    }
}
