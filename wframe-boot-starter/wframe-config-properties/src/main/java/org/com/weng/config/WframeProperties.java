package org.com.weng.config;

import lombok.Data;
import org.com.weng.config.dataSource.WframeDataSourceProperties;
import org.com.weng.config.orm.WframeOrmProperties;
import org.com.weng.config.queue.WframeQueueProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @DATE: 2022/11/15 3:31 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "wframe")
@Component
public class WframeProperties {
    /**
     * 数据源相关配置
     */
    private Map<String, WframeDataSourceProperties> dataSource;

    /**
     * orm框架相关配置
     */
    private WframeOrmProperties orm;

    /**
     * 队列相关配置
     */
    private WframeQueueProperties queue;
}
