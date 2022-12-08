package com.weng.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.com.weng.common.TreadPoolCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;

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
        ExecutorService pool = TreadPoolCommon.newCommonThreadPool("testB", "/test");
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            pool.execute(RunnableWrapper.of(()->{
                restTemplate.getForObject("http://localhost:19902/test", String.class);
                log.info("pool excute :{}", finalI);
            }));
        }
        log.info("remote server c return :{}");
        return "success";
    }
}
