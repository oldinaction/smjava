package cn.aezo.smjava.javaee.spring5.bean.demo1.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyWindowsCondition implements Condition {
    /**
     * 判断是否符合条件
     * @param context 判断条件能使用的上下文
     * @param metadata 注解信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 1.可以获取到IOC使用的BeanFactory
        ConfigurableListableBeanFactory ctx = context.getBeanFactory();
        // 2.可以获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 3.可以获取当前环境信息
        Environment environment = context.getEnvironment();
        // 4.可以获取Bean定义注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        ResourceLoader resourceLoader = context.getResourceLoader();

        if(environment.getProperty("os.name").contains("Windows")) {
            return true;
        }

        return false;
    }
}
