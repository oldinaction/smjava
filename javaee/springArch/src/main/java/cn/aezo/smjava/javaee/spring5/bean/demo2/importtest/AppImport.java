package cn.aezo.smjava.javaee.spring5.bean.demo2.importtest;

import cn.aezo.smjava.javaee.spring5.bean.demo2.importselector.MyImportSelector;
import cn.aezo.smjava.javaee.spring5.bean.demo2.importselector.MyImportSelectorBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

// http://blog.51cto.com/4247649/2118354
// @Configuration
@Import({MyImportSelector.class})
public class AppImport {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppImport.class);

        System.out.println(ctx.getBean(MyImportSelectorBean.class));

        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}









