package org.com.weng.mybatis.plugin;

import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * @DATE: 2022/10/11 4:13 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class DefaultInterceptor implements CustomerInterceptor{
    //不进行处理
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return  Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public int Order() {
        return 0;
    }
}
