package com.example.demo.business.config;

import org.com.weng.config.WframeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @DATE: 2022/11/17 2:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class TestConfiguration {
    private final AConfiguration aConfiguration;
    private final WframeProperties wframeProperties;
    @Autowired
    public TestConfiguration(AConfiguration aConfiguration, WframeProperties wframeProperties){
        this.wframeProperties = wframeProperties;
        System.out.println("构造器注入。。");
        this.aConfiguration = aConfiguration;
    }


    @Bean
    public BConfiguration b(){
        System.out.println(aConfiguration.getUsername());
        System.out.println(wframeProperties.getDataSource());
        return new BConfiguration();
    }


}
