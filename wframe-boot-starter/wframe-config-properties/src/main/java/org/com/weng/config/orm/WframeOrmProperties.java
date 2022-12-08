package org.com.weng.config.orm;

import lombok.Data;

/**
 * @DATE: 2022/11/24 2:31 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class WframeOrmProperties {

    private WframeMybatisProperties mybatis;

    private WframeJpaProperties jpa;
}
