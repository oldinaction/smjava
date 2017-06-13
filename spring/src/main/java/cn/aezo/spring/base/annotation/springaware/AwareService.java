package cn.aezo.spring.base.annotation.springaware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by smalle on 2017/6/5.
 */
@Component
public class AwareService implements BeanNameAware, ResourceLoaderAware {
    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void outputResult() {
        System.out.println("beanName = " + beanName);
        Resource resource = loader.getResource("classpath:cn/aezo/spring/base/annotation/springaware/test.txt");
        try {
            String test = IOUtils.toString(resource.getInputStream(), "UTF-8");
            System.out.println("test = " + test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
