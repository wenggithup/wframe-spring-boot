package org.com.weng.datasource.domain;

import org.com.weng.datasource.annotation.DynamicSource;
import org.com.weng.datasource.constant.DataSourceConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DATE: 2022/9/14 2:10 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceConfig {

    private DataSourceConstant dataSourceConstant;
}
