package org.com.weng.config.queue.kafka.constant;

/**
 * @DATE: 2022/11/24 5:32 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaProducerConstant {



    /**
     * #同步还是异步发送消息，默认“sync”表同步，"async"表异步。异步可以提高发送吞吐量,
     *           #也意味着消息将会在本地buffer中,并适时批量发送，但是也可能导致丢失未发送过去的消息
     */
    public static final String SYNC_TYPE = "sync";
    public static final String ASYNC_TYPE = "async";
}
