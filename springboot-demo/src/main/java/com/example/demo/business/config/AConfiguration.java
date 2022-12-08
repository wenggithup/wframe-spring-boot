package com.example.demo.business.config;

import org.springframework.stereotype.Component;

/**
 * @DATE: 2022/11/17 2:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
public class AConfiguration {
    private String username = "123";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
