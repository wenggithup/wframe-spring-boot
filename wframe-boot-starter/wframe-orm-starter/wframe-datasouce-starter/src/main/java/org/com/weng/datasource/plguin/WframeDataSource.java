package org.com.weng.datasource.plguin;

import org.com.weng.datasource.domain.DataSourceProperties;

import javax.sql.DataSource;

/**
 * @DATE: 2022/9/23 3:03 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:动态数据源对外接口，
 */
public interface WframeDataSource {

    /**
     * 获取数据源
     * @param dataSourceProperties
     * @return
     */
    DataSource getDataSource(DataSourceProperties dataSourceProperties);

    /**
     * 关闭链接
     */
    void closeConnection();
}
