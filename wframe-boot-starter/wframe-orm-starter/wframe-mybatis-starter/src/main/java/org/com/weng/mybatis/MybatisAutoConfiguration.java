package org.com.weng.mybatis;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.com.weng.mybatis.plugin.DefaultInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.io.IOException;

import static org.apache.ibatis.session.LocalCacheScope.SESSION;

/**
 * @DATE: 2022/10/11 2:22 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class MybatisAutoConfiguration {


    /**
     * 使用mybatis-plus进行编译
     * @return
     */
    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(@Qualifier("mydataSource") DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //mp全局配置
        GlobalConfig globalConfig = new GlobalConfig();

        //banner
        globalConfig.setBanner(false);

        globalConfig.setEnableSqlRunner(true);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        //mybatis部分配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        //是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射。 默认值：true
        configuration.setMapUnderscoreToCamelCase(true);

        //当设置为 true 的时候，懒加载的对象可能被任何懒属性全部加载，否则，每个属性都按需加载。需要和 lazyLoadingEnabled 一起使用 默认值：true
        configuration.setAggressiveLazyLoading(true);

        //MyBatis 自动映射策略，通过该配置可指定 MyBatis 是否并且如何来自动映射数据表字段与对象的属性，总共有 3 种可选值：
        //
        //AutoMappingBehavior.NONE：不启用自动映射
        //AutoMappingBehavior.PARTIAL：只对非嵌套的 resultMap 进行自动映射
        //AutoMappingBehavior.FULL：对所有的 resultMap 都进行自动映射
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);

        //MyBatis 自动映射时未知列或未知属性处理策略，通过该配置可指定 MyBatis 在自动映射过程中遇到未知列或者未知属性时如何处理，总共有 3 种可选值：
        //
        //AutoMappingUnknownColumnBehavior.NONE：不做任何处理 (默认值)
        //AutoMappingUnknownColumnBehavior.WARNING：以日志的形式打印相关警告信息
        //AutoMappingUnknownColumnBehavior.FAILING：当作映射失败处理，并抛出异常和详细信息
        configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.NONE);

        //Mybatis 一级缓存，默认为 SESSION。
        //
        //SESSION session 级别缓存，同一个 session 相同查询语句不会再次查询数据库
        //STATEMENT 关闭一级缓存
        configuration.setLocalCacheScope(SESSION);

        //MyBatis 在使用 resultMap 来映射查询结果中的列，如果查询结果中包含空值的列，则 MyBatis 在映射的时候，不会映射这个字段，这就导致在调用到该字段的时候由于没有映射，取不到而报空指针异常。
        configuration.setCallSettersOnNulls(false);
        sqlSessionFactoryBean.setConfiguration(configuration);
        //mapper location
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/**/*.xml"));

        //设置mp 拦截器
//        sqlSessionFactoryBean.setPlugins(new DefaultInterceptor());
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
