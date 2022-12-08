package org.com.weng.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @DATE: 2022/10/13 4:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class JpaAutoConfiguration {
    //jpa其他参数配置
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private EntityManagerFactoryBuilder factoryBuilder;

    @Resource
    private DataSource dataSource;
    @Bean(name = "entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        return factoryBuilder.dataSource(dataSource)
                //这一行的目的是加入jpa的其他配置参数比如（ddl-auto: update等）
                //当然这个参数配置可以在事务配置的时候也可以
                .properties(jpaProperties.getProperties())
                //扫描实体
                .build();
    }
    @Bean
    @Primary
    public EntityManager entityManager() {
        return entityManagerFactoryBean().getObject().createEntityManager();
    }
    /**
     * jpa事务管理
     * @return
     */
    @Bean
    @Primary
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
//    @Bean(name ="entityManagerFactoryBean")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) throws IOException {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        //数据源配置
//        factory.setDataSource(dataSource);
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
//
//        vendorAdapter.setShowSql(true);
//
//        vendorAdapter.setDatabase(Database.valueOf("MYSQL"));
//        vendorAdapter.setGenerateDdl(false);
//
//        factory.setJpaVendorAdapter(vendorAdapter);
//        Properties properties = new Properties();
//
//        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
//        factory.setJpaProperties(properties);
//
//        factory.setPackagesToScan("com.example.demo.business.entity.*","com.example.demo.business.mapper.*");
//        return factory;
//    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(LocalContainerEntityManagerFactoryBean bean) {

        JpaTransactionManager txManager = new JpaTransactionManager();

        txManager.setEntityManagerFactory(bean.getObject());

        return txManager;

    }
}
