package com.AlonSimhi.CouponProject.Threads;

import com.AlonSimhi.CouponProject.Beans.Coupon;
import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Services.CompanyService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Service
public class CouponsExpirationDailyJob {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CouponsRepository repo;

    /**
     * Initializes a scheduled thread pool that runs a task to delete expired coupons once a day at 00:00 and when initialized.
     */
    private CouponsExpirationDailyJob(CouponsRepository couponsRepository) {
        this.repo = couponsRepository;
        System.out.println("REACHED");
        deleteCustomerCoupons();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //Deletes all coupons from the repository whose end date is before the current date.
                deleteCustomerCoupons();
            }
            //the method getDelayToNextMidnight returns the seconds until 23:59:59 so to make sure that the date changes i add 2 seconds
        }, getDelayToNextMidnight()+2, 24 * 60 * 60, TimeUnit.SECONDS);
    }

    public void deleteCustomerCoupons(){
        List<Coupon> expiredCoupons = repo.findByEndDateBefore(new Date(System.currentTimeMillis()));
        System.out.println("Expired coupon:  ---   "+expiredCoupons);
        for(Coupon c : expiredCoupons){
            repo.deleteCustomerCouponsPurchase(c.getId());
            repo.deleteById(c.getId());
        }
    }
    /**
     * @return the delay (in seconds) to the next midnight
     */
    public long getDelayToNextMidnight() {
        return LocalTime.now().until(LocalTime.MAX, ChronoUnit.SECONDS);
    }
}

/** Need to add a stop?.*/