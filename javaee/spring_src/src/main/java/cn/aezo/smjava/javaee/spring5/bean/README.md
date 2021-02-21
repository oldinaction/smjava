- demo1
    - `AnnotationConfigApplicationContext` 注册配置类，并取出Bean
    - `Condition`
    - Bean的生命周期
- demo2
    - `@Import` 动态加入Bean到IOC容器
    - `ImportSelector`
    - `ImportBeanDefinitionRegistrar` 基于BeanDefinition往IOC容器放入Bean时，必须先定义好这个Bean的BeanDefinition
- demo3 
    - 模拟mybatis. 通过反射将接口(Mapper)的代理对象加入到IOC容器
    - `BeanFactory`和`FactoryBean`区别(https://www.cnblogs.com/aspirant/p/9082858.html)





