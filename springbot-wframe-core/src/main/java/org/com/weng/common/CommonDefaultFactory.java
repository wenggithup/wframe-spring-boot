package org.com.weng.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @DATE: 2022/10/25 11:07 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class CommonDefaultFactory implements ThreadFactory {

    /**
     * 线程池名称前缀
     */
    private StringBuffer namePrefix = new StringBuffer();

    /**
     * 线程数量的计数器
     */
    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 线程池构造方法
     *
     * @param factoryName  工厂名称
     * @param businessName 业务名称
     */
    public CommonDefaultFactory(String factoryName, String businessName) {
        namePrefix.append("pool-").append(factoryName).append("-").append(businessName).append("-Worker-");
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(null, runnable, namePrefix.toString() + nextId.getAndIncrement(), 0);
    }
}
