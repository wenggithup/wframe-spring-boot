/*
package com.example.demo.business.entity;

//import com.baomidou.mybatisplus.annotation.TableName;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;

*/
/**
 *  <p>
 *
 * @author ChuanJie.Weng
 * @date 2022-10-12
 *//*

@Data
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("userID",1l);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("userID",15l);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("userID",12l);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        list.add(map3);

        Map<String, Object> map2 = list.stream().max((x, y) -> {
            return (int) ((long) x.get("userID") - (long) y.get("userID"));
        }).get();

        System.out.println(map2);


    }
}
*/
