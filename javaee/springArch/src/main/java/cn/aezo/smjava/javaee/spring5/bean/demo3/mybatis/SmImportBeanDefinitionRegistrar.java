package cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis;

import cn.aezo.smjava.javaee.spring5.bean.demo3.mapper.MyMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class SmImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // MyMapper为接口，无法直接实例化。需要通过FactoryBean间接创建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(AezoFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("classzz", MyMapper.class);
        GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("myMapper", genericBeanDefinition);

        // 将扫描到的其他Mapper加入IOC容器
    }
}
