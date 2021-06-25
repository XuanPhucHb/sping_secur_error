package com.nxp.test_spring_secur;

import com.nxp.test_spring_secur.utils.Migrate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestSpringSecurApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TestSpringSecurApplication.class, args);
        Migrate migrate = ctx.getBean(Migrate.class);
        migrate.dataSeeding();
    }
}
