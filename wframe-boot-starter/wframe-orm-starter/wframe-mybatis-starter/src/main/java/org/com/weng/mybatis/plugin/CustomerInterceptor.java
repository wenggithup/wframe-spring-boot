package org.com.weng.mybatis.plugin;

import org.apache.ibatis.plugin.Interceptor;

/**
 * @DATE: 2022/10/11 4:08 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface CustomerInterceptor extends Interceptor {
    int Order();

}
