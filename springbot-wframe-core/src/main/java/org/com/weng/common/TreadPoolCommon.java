package org.com.weng.common;

import java.util.concurrent.*;

/**
 * @DATE: 2022/10/25 11:04 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TreadPoolCommon {
    /**
     * 线程池通用核心线程数
     */
    public static final int CORE_SIZE =
            Runtime.getRuntime().availableProcessors();

    /**
     * 线程池通用最大线程数
     */
    public static final int MAX_SIZE = CORE_SIZE << 1;

    /**
     * 队列最大数量
     */
    public static final int QUEUE_MAX_NUM = 5000;

    /**
     * 通用线程存活时长
     */
    public static final long COMMON_KEEP_ALIVE_TIME = 60L;

    /**
     * 通用线程池方法
     *
     * @param factoryName   工厂名称
     * @param businessName  业务名称
     * @return ExecutorService 线程池操作对象
     */
    public static ExecutorService newCommonThreadPool(String factoryName,
                                                      String businessName) {
        return new ThreadPoolExecutor(CORE_SIZE,
                MAX_SIZE, COMMON_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_MAX_NUM),
                new CommonDefaultFactory(factoryName, businessName));
    }

    /**
     * 通用线程池方法
     *
     * @param factoryName   工厂名称
     * @param businessName  业务名称
     * @param handler  队列满了如何处理
     * @return ExecutorService 线程池操作对象
     */
    public static ExecutorService newCommonThreadPool(String factoryName,
                                                      String businessName,
                                                      RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(CORE_SIZE,
                MAX_SIZE, COMMON_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_MAX_NUM),
                new CommonDefaultFactory(factoryName, businessName),
                handler);
    }

    /**
     * 单例线程池方法
     *
     * @param factoryName  工厂名称
     * @param businessName 业务名称
     * @return ExecutorService 线程池操作对象
     */
    public static ExecutorService newSingleThreadPool(String factoryName,
                                                      String businessName) {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(QUEUE_MAX_NUM),
                new CommonDefaultFactory(factoryName, businessName));
    }
}
