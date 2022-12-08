package org.com.weng.kafka.starter;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.weng.config.WframeProperties;
import org.com.weng.config.queue.kafka.WframeKafkaConsumerProperties;
import org.com.weng.config.queue.kafka.WframeKafkaProducerProperties;
import org.com.weng.config.queue.kafka.WframeKafkaProperties;
import org.com.weng.kafka.starter.serialize.KafkaDeSerialize;
import org.com.weng.kafka.starter.serialize.KafkaSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static org.com.weng.config.queue.kafka.constant.KafkaConsumerConstant.*;

/**
 * @DATE: 2022/11/14 3:24 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class WframeKafkaSpringBootApplication {
    private final WframeKafkaProperties kafkaProperties;
    private final Environment env;

    @Autowired
    public WframeKafkaSpringBootApplication(WframeProperties wframeProperties, Environment env) {
        this.kafkaProperties = wframeProperties.getQueue().getKafka();
        this.env = env;
    }


    @Bean
    @ConditionalOnMissingBean(KafkaTemplate.class)
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }


    @Bean("batchFactory")
    @ConditionalOnMissingBean(name = "batchFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> batchFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    /**
     * 消费者配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "consumerFactory")
    public ConsumerFactory<String, String> consumerFactory() {
        if (null ==kafkaProperties){
            throw new RuntimeException("yaml kafka config is null 。。");
        }

        Map<String, Object> props = new HashMap<>(10);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.putIfAbsent(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.putIfAbsent(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaDeSerialize.class);
        props.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.application.name"));

        props.putIfAbsent(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        WframeKafkaConsumerProperties consumer = kafkaProperties.getConsumer();
        if (null != consumer){
            //enableAutoCommit:
            if (null != consumer.getEnableAutoCommit()){
                props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                        consumer.getEnableAutoCommit());
            }

            //groupId
            if (StrUtil.isNotBlank(consumer.getGroupId())){
                props.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, consumer.getGroupId());
            }else {
                props.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, "");
            }

            //maxPollRecords:
            if (null != consumer.getMaxPollRecords()){
                props.putIfAbsent(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,consumer.getMaxPollRecords());
            }

            //autoOffsetReset
            if (StrUtil.isNotBlank(consumer.getAutoOffsetReset())){
                switch (consumer.getAutoOffsetReset()){
                    case LATEST:
                        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,LATEST);
                        break;
                    case NONE:
                        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,NONE);
                        break;
                    default:
                    case EARLIEST:
                        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,EARLIEST);
                        break;
                }
            }

        }
        return new DefaultKafkaConsumerFactory<>(props);


    }

    /**
     * 生产者配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "producerFactory")
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> producerConfig =new HashMap<>(10);
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        producerConfig.putIfAbsent(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfig.putIfAbsent(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerialize.class);
        //生产者ack，只管生产，不关心消费结果
        //消息发送不进行重试
        producerConfig.putIfAbsent(ProducerConfig.RETRIES_CONFIG, 0);
        WframeKafkaProducerProperties producer = kafkaProperties.getProducer();
        if (producer != null){

            //acks
            if (null !=producer.getAcks()){
                producerConfig.putIfAbsent(ProducerConfig.ACKS_CONFIG,producer.getAcks());
            }

            //retries
            if (null != producer.getRetries()){
                producerConfig.putIfAbsent(ProducerConfig.RETRIES_CONFIG, producer.getRetries());
            }
            if (null != producer.getBatchSize()){
                producerConfig.putIfAbsent(ProducerConfig.BATCH_SIZE_CONFIG,producer.getBatchSize());
            }
        }
        return new DefaultKafkaProducerFactory<String, String>(producerConfig);
    }

}
