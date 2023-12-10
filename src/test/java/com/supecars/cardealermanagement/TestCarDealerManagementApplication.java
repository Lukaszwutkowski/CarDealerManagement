package com.supecars.cardealermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCarDealerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.from(CarDealerManagementApplication::main).with(TestCarDealerManagementApplication.class).run(args);
    }

}
