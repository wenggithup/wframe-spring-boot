package org.com.weng.config.queue.kafka;

import lombok.Data;
import org.com.weng.config.queue.kafka.constant.KafkaProducerConstant;

/**
 * @DATE: 2022/11/24 2:55 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class WframeKafkaProducerProperties {

    private Integer acks;

    /**
     * 重试次数
     */
    private Integer retries;



    private Integer batchSize;

}
