package cn.aezo.spring.base.annotation;

import cn.aezo.spring.base.annotation.aop.Action;
import cn.aezo.spring.base.annotation.aop.AopConfig;
import cn.aezo.spring.base.annotation.aop.DemoAnnotationService;
import cn.aezo.spring.base.annotation.aop.DemoMethodService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by smalle on 2017/6/3.
 */
public class AopTest {

    @Test
    public void aop() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService demoAnnotationService = ac.getBean("demoAnnotationService", DemoAnnotationService.class);
        DemoMethodService demoMethodService = ac.getBean("demoMethodService", DemoMethodService.class);

        demoAnnotationService.add();
        demoMethodService.add();

        ac.close();
    }

    @Test
    public void annotation() {
        for(Method method : DemoAnnotationService.class.getMethods()) {
            Action action = method.getAnnotation(Action.class);
            if(action != null) {
                System.out.println("Method Name: " + method.getName());
                System.out.println("Annotation Name: " + action.name());
                try {
                    DemoAnnotationService demoAnnotationService = new DemoAnnotationService();
                    method.invoke(demoAnnotationService);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
