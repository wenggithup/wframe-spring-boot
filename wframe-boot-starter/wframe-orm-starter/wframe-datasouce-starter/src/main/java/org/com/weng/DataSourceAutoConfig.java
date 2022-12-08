package org.com.weng;

import org.com.weng.datasource.aop.DynamicAdvice;
import org.com.weng.datasource.constant.DataSourceConstant;
import org.com.weng.datasource.domain.DataSourceConfig;
import org.com.weng.datasource.domain.DataSourceProperties;
import org.com.weng.datasource.factory.DruidDataSourceFactory;
import org.com.weng.datasource.factory.DynamicDataSource;
import org.com.weng.datasource.factory.HikariDataSourceFactory;
import org.com.weng.datasource.plguin.WframeDataSource;
import org.com.weng.datasource.util.ServiceLoaderHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * @DATE: 2022/9/18 7:22 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Import({DataSourceProperties.class, DynamicAdvice.class})
public class DataSourceAutoConfig {
    @Bean
    @ConditionalOnMissingBean(DataSourceConfig.class)
    @ConditionalOnExpression
    public DataSourceConfig dataSourceConfig(){
        return new DataSourceConfig(DataSourceConstant.HIKARI_DATASOURCE);
    }


    /**
     * 这里先实现单数据源
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource mydataSource(DataSourceProperties properties, DataSourceConfig dataSourceConfig) {
        //添加拓展数据源,引用此包的可以自定义数据源实现
        WframeDataSource wframeDataSource = ServiceLoaderHelper.getUniqueServiceInstance(WframeDataSource.class);
        if (null != wframeDataSource){
            return wframeDataSource.getDataSource(properties);
        }

        DataSourceConstant constant = dataSourceConfig.getDataSourceConstant();
        switch (constant){
            case DURID_DATASOURCE:
                return new DruidDataSourceFactory(properties).getDataSource();
            case HIKARI_DATASOURCE:
            default:
                return new HikariDataSourceFactory(properties).getDataSource();
        }

    }

    /**
     * 多数据源
     */
    public DataSource dataSource(DataSourceConfig dataSourceConfig){
        return new DynamicDataSource(dataSourceConfig);
    }


}
