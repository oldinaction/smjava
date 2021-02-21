package cn.aezo.smjava.javaee.spring5.bean.demo2.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    // AnnotationMetadata可获取当前注解@Import类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"cn.aezo.smjava.javaee.spring5.bean.demo2.MyBean", MyImportSelectorBean.class.getName()};
    }
}
