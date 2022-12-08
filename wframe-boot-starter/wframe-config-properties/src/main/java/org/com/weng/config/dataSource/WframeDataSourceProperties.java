package org.com.weng.config.dataSource;

import lombok.Data;

/**
 * @DATE: 2022/11/18 10:38 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:数据源配置类
 */
@Data
public class WframeDataSourceProperties {
    private String username;

    private String password;

    private String url;

    private String driverClassName;

    private boolean primary;

    private DataSourcePoolConfig pool;


}
