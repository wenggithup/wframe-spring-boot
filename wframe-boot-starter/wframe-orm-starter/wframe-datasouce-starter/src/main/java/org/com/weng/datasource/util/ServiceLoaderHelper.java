package org.com.weng.datasource.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @DATE: 2022/9/23 3:36 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ServiceLoaderHelper {



    public static <T> T getUniqueServiceInstance(Class<T> classz){
        List<T> list = getServiceInstance(classz);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        if (list.size() >1){
            throw new RuntimeException("定了了多个实现，请检查，，，");
        }

        return list.get(0);
    }


    public static <T> List<T> getServiceInstance(Class<T> classz){
        List<T> service = new ArrayList<>(10);

        if (null != classz && classz.isInterface()){
            ServiceLoader<T> load = ServiceLoader.load(classz);
            load.forEach(t->service.add(t));
        }

        return service;

    }

}
