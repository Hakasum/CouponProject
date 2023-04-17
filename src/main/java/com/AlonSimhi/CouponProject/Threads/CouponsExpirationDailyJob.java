package com.AlonSimhi.CouponProject.Threads;

import com.AlonSimhi.CouponProject.Repositories.CouponsRepository;
import com.AlonSimhi.CouponProject.Services.CompanyService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Service
public class CouponsExpirationDailyJob {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CouponsRepository repo;

    /**
     * Initializes a scheduled thread pool that runs a task to delete expired coupons once a day.
     */
    private CouponsExpirationDailyJob(CouponsRepository couponsRepository) {
        this.repo = couponsRepository;
        repo.deleteByEndDateBefore(new Date(System.currentTimeMillis()));
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //Deletes all coupons from the repository whose end date is before the current date.
                repo.deleteByEndDateBefore(new Date(System.currentTimeMillis()));
            }
        }, getDelayToNextMidnight(), 24 * 60 * 60, TimeUnit.SECONDS);
    }
    /**
     * @return the delay (in seconds) to the next midnight
     */
    public long getDelayToNextMidnight() {
        return LocalTime.now().until(LocalTime.MAX, ChronoUnit.SECONDS);
    }
}

/** Needs to add a stop.*/