package com.AlonSimhi.CouponProject;

import com.AlonSimhi.CouponProject.Threads.CouponsExpirationDailyJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CouponProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CouponProjectApplication.class, args);
		Test test = ctx.getBean(Test.class);
		test.testAll();
	}

}
