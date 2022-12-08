package org.com.weng.config.dataSource;

import lombok.Data;

/**
 * @DATE: 2022/11/18 1:59 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:连接池相关配置
 */
@Data
public class DataSourcePoolConfig {

    private String name;

    private Integer initialPoolSize;

    private Integer minimumIdlePoolSize;

    private Integer maximumIdlePooSize;

    private Integer maxPoolWaitTime;

    private Integer connectionTimeOut;

    private String connectInitSql;
}
