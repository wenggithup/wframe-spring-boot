package org.com.weng.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @DATE: 2022/10/26 8:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class HttpConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                //设置连接超时时间
                .setConnectTimeout(Duration.ofSeconds(5000))
                //设置读取超时时间
                .setReadTimeout(Duration.ofSeconds(5000))
                //设置认证信息
                .basicAuthentication("username","password")
                //设置根路径
//                .rootUri("https://api.test.com/")
                //构建
                .build();
    }
}
