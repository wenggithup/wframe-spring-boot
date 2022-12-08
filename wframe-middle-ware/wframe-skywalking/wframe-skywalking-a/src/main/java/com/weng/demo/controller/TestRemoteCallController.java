package com.weng.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @DATE: 2022/10/27 10:16 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
@Slf4j
public class TestRemoteCallController {

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/test")
    public String getTest(){
        String forObject = restTemplate.getForObject("http://localhost:19901/test", String.class);
        log.info("remote server b return :{}",forObject);
        return "success";
    }
}
