package cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class AezoFactoryBean<T> implements FactoryBean {
    private Class<T> classzz;

    public Class<T> getClasszz() {
        return classzz;
    }

    public void setClasszz(Class<T> classzz) {
        this.classzz = classzz;
    }

    @Override
    public T getObject() throws Exception {
        // MyMapper myMapper = new MyMapper();

        // 接口无法实例化，需要通过反射获取其代理对象
        T mapper = (T) Proxy.newProxyInstance(
                classzz.getClassLoader(),
                new Class[]{classzz},
                new MyMapperInvocationHandler());
        return mapper;
    }

    @Override
    public Class<?> getObjectType() {
        return classzz;
    }
}
