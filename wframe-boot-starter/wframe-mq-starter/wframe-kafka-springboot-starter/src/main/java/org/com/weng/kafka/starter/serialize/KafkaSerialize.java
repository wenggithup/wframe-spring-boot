package org.com.weng.kafka.starter.serialize;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.com.weng.kafka.starter.serialize.Encrypto.KAFKA_SERIALIZE_HEADER_KEY;

/**
 * @DATE: 2022/11/3 11:19 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaSerialize implements Serializer<String> {
    private String encoding = StandardCharsets.UTF_8.name();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("serializer.encoding");
        }
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }


    @Override
    public byte[] serialize(String topic, String data) {
        return serialize(topic,new RecordHeaders(),data);
    }

    /**
     * kafka序列化方式
     * @param topic
     * @param headers
     * @param data
     * @return
     */
    @Override
    public byte[] serialize(String topic, Headers headers, String data) {
        try {
            if (data == null){
                return null;
            }
            boolean flag = false;
            for (Header header : headers) {
                String aTure = "true";
                if (KAFKA_SERIALIZE_HEADER_KEY.equals(header.key())
                        && aTure.equals(new String(header.value(),encoding))){
                    flag = true;
                    break;
                }
            }
            //如果包含了密钥，加密
            if (flag){

            }
            return data.getBytes(encoding);
        }catch (UnsupportedEncodingException e){
            throw new SerializationException("Error when kafka serializing string to byte[] due to unsupported encoding " + encoding);
        }
    }

}
