package org.com.weng.config.queue.kafka;

import lombok.Data;

/**
 * @DATE: 2022/11/24 2:46 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class WframeKafkaProperties {
    /**
     * 服务地址
     */
    private String bootstrapServers;

    /**
     * 生产者相关配置
     */
    private WframeKafkaProducerProperties producer;

    /**
     * 消费者相关配置
     */
    private WframeKafkaConsumerProperties consumer;
}
