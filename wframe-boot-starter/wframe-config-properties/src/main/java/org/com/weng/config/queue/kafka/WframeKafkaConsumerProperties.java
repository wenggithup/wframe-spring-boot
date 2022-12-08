package org.com.weng.config.queue.kafka;

import lombok.Data;
import org.com.weng.config.queue.kafka.constant.KafkaConsumerConstant;
import org.com.weng.config.queue.kafka.constant.KafkaProducerConstant;

/**
 * @DATE: 2022/11/24 2:58 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class WframeKafkaConsumerProperties {

    private Boolean enableAutoCommit;

    private String groupId;

    /**
     * 最大批次拉取消息,默认一百
     */
    private Integer maxPollRecords = 100;

    /**
     * 默认earliest
     */
    private String autoOffsetReset = KafkaConsumerConstant.EARLIEST;

}
