package cn.aezo.spring.base.annotation.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by smalle on 2017/6/3.
 */
@Configuration // java配置类
@ComponentScan("cn.aezo.spring.base.annotation.aop") // 对于没有在java配置类中声明@Bean的需要进行扫描
@EnableAspectJAutoProxy // 开启Spring对AspectJ代理的支持
public class AopConfig {
}
