package cn.aezo.spring.base.annotation.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by smalle on 2017/6/4.
 */
@Configuration
@ComponentScan("cn.aezo.spring.base.annotation.el")
@PropertySource("classpath:cn/aezo/spring/base/annotation/el/el.properties") // 注入配置文件
public class ELConfig {
    @Value("I Love You")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private String randomNumber;

    @Value("${site.url}") // 读取配置文件(需要注入配置文件)，使用$而不是#
    private Resource siteUrl;

    @Value("#{demoService.another}") // 读取其他类属性的@Value注解值
    private String fromAnother;

    @Value("classpath:cn/aezo/spring/base/annotation/el/test.txt")
    private Resource testFile;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Autowired
    private Environment environment;

    // @Bean
    // public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }

    public void outputResource() {
        System.out.println("normal = " + normal);
        System.out.println("osName = " + osName);
        System.out.println("randomNumber = " + randomNumber);
        System.out.println("normal = " + siteUrl);
        System.out.println("fromAnother = " + fromAnother);
        System.out.println("environment = " + environment.getProperty("site.url"));

        try {
            System.out.println("testFile = " + IOUtils.toString(testFile.getInputStream(), "UTF-8"));
            System.out.println("testUrl = " + IOUtils.toString(testUrl.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
