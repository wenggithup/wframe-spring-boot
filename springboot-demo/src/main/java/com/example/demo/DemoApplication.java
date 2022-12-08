package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
/*import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;*/

@SpringBootApplication
//@EntityScan(basePackages = "com.example.demo.business.entity")
//1、实体扫描
//2、实体管理ref
//3、事务管理
//@Entity
//@EnableJpaRepositories(
//        basePackages = "com.example.demo.business.mapper",
//        entityManagerFactoryRef = "entityManagerFactoryBean",
//        transactionManagerRef = "transactionManager"
//)
//@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
