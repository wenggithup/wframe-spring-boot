/*
package com.example.demo.business.controller;

import com.example.demo.business.common.CommonDefaultFactory;
import com.example.demo.business.common.TreadPoolCommon;
*/
/*import com.example.demo.business.entity.User;
import com.example.demo.business.mapper.UserMapper;*//*

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static com.example.demo.business.common.TreadPoolCommon.CORE_SIZE;
import static com.example.demo.business.common.TreadPoolCommon.MAX_SIZE;

*/
/**
 * @DATE: 2022/10/12 3:10 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 *//*

@RestController
public class TestDemoController {
    @Resource
    UserMapper userMapper;


    @GetMapping("/getUser")
    public List<User> getUser(){
        List<User> users = userMapper.findAll();
        System.out.println(users);
        return users;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
//         userMapper.save(user);
    }

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(CORE_SIZE);
        System.out.println(MAX_SIZE);
        ExecutorService  pool =  TreadPoolCommon.newCommonThreadPool("test", "task");
        List<CompletableFuture> task = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            task.add( CompletableFuture.supplyAsync(() ->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI);
                return 123;
            }, pool));

        }
        CompletableFuture completableFuture = task.get(0);
        completableFuture.whenComplete((str,e)->{
            System.out.println("str is "+str);
            System.out.println("e is "+e.toString());
        });
        System.out.println(completableFuture.get());
        Void unused = CompletableFuture.allOf(task.toArray(new CompletableFuture[0])).get();
        System.out.println(unused);

//        future.join();
    }
}
*/
