package org.com.weng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @DATE: 2022/10/12 8:14 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:核心主启动类，扫描的默认报名为当前core的默认包名
 */
@SpringBootApplication(scanBasePackages = {"org.com.weng","com.weng.demo"})
@MapperScan("com.weng.demo.*.mapper")
public class SpringBootWframeCore {

}
