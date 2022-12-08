package org.com.weng.kafka.starter.serialize;



import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.com.weng.kafka.starter.serialize.Encrypto.KAFKA_SERIALIZE_HEADER_KEY;


/**
 * @DATE: 2022/11/3 2:13 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaDeSerialize implements Deserializer<String> {
    private String encoding = StandardCharsets.UTF_8.name();

    /**
     * 可通过配置key.deserializer.encoding，value.deserializer.encoding 指定字符集，如果不指定，则使用utf-8
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("deserializer.encoding");
        }
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    @Override
    public String deserialize(String topic, byte[] data) {
        return deserialize(topic,new RecordHeaders(),data);
    }

    @Override
    public String deserialize(String topic, Headers headers, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            String aTrue = "true";
            boolean flag = false;
            //用于做kafka序列化与反序列化
            for (Header header : headers) {
                if (KAFKA_SERIALIZE_HEADER_KEY.equals(header.key())
                        && aTrue.equals(new String(header.value(),encoding))){
                    flag = true;
                    break;
                }
            }

            if (flag){
            }
            return new String(data,encoding);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error when kafka deserializing byte[] to string due to unsupported encoding " + encoding);
        }
    }
}
