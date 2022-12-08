package com.weng.demo.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.demo.business.entity.ImEnterpriseOrganization;
import com.weng.demo.business.mapper.ImEnterpriseOrganizationMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @DATE: 2022/10/11 4:00 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    ImEnterpriseOrganizationMapper imEnterpriseOrganizationMapper;
    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> containerFactory;
    int i =0;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/test")
    public String getTest(){
        List<ImEnterpriseOrganization> organizationList = imEnterpriseOrganizationMapper.selectList(new QueryWrapper<>());
        System.out.println(organizationList.toString());

//        i++;
//        if (i <=1){
//            String forObject = restTemplate.getForObject("http://localhost:18080/test", String.class);
//            System.out.println(forObject);
//        }

        return "test2";
    }
    @GetMapping("/testKafka")
    public void testKafka(String groupId){
//        ConcurrentMessageListenerContainer<String, String> kafkaSpringBoot = containerFactory.createContainer("testKafka_spring_boot");
//        System.out.println(kafkaSpringBoot.getGroupId());
//        byte[] listenerInfo = kafkaSpringBoot.getListenerInfo();
//        System.out.println(new String(listenerInfo));
//        //动态注册
        Map<String, Object> configurationProperties = containerFactory.getConsumerFactory().getConfigurationProperties();
        Map<String,Object> test = new HashMap<>(configurationProperties);

        test.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        containerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(test));
        ConsumerFactory<String,String> consumerFactory = (ConsumerFactory<String, String>) containerFactory.getConsumerFactory();
        Consumer<String,String> consumer = consumerFactory.createConsumer();
        consumer.subscribe(Collections.singleton("testKafka_spring_boot"));
;        ConsumerRecords<String,String> poll = consumer.poll(100);
//        for (ConsumerRecord<String,String> consumerRecord : poll) {
//            System.out.println(consumerRecord.key());
//            System.out.println(consumerRecord.value());
//        }
    }

    @KafkaListener(topics = "testKafka_spring_boot",groupId = "test-group1")
    public void listener(ConsumerRecord<String,Object> record){
        System.out.println(record.toString());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
            Map<String, Object> producerConfig = new HashMap<>();
            producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.85:11090");
            producerConfig.putIfAbsent(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            producerConfig.putIfAbsent(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
             producerConfig.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, "testOD");
            //生产者ack，只管生产，不关心消费结果
            //消息发送不进行重试
            producerConfig.putIfAbsent(ProducerConfig.RETRIES_CONFIG, 0);

        KafkaTemplate<String,String> kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<String, String>(producerConfig));
        for (int i = 0; i < 50; i++) {
            String topic ="testKafka_spring_boot";
            String key ="test";
            String value ="valuie";
            System.out.println(i);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
            SendResult<String, String> stringStringSendResult = future.get();

        }

    }
}
