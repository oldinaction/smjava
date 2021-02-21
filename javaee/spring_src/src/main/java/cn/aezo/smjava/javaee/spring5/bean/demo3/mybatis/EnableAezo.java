package cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(SmImportBeanDefinitionRegistrar.class)
public @interface EnableAezo {
}
