package org.com.weng.config.queue;

import lombok.Data;
import org.com.weng.config.queue.kafka.WframeKafkaProperties;

/**
 * @DATE: 2022/11/24 2:46 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class WframeQueueProperties {

    private WframeKafkaProperties kafka;

    private WframeRabbitMqProperties rabbitMq;

    private WframeRocketMqProperties rocketMq;

}
