package org.com.weng.config.queue.kafka.constant;

/**
 * @DATE: 2022/12/8 3:53 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaConsumerConstant {

    /**
     * auto.offset.reset：在 Kafka 中，每当消费者组内的消费者查找不到所记录的消费位移或发生位移越界时，
     *
     * 就会根据消费者客户端参数 auto.offset.reset 的配置来决定从何处开始进行消费，这个参数的默认值为 “latest”
     * earliest ：当各分区下存在已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，从头开始消费。
     * latest ：当各分区下存在已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费该分区下新产生的数据。
     * none ：topic 各分区都存在已提交的 offset 时，从 offset 后开始消费；只要有一个分区不存在已提交的offset，则抛出异常。
     */
    public static final String EARLIEST = "earliest";

    public static final String LATEST = "latest";

    public static final String NONE = "none";
}
