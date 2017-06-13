package cn.aezo.spring.base.annotation.aop;

import org.springframework.stereotype.Service;

/**
 * Created by smalle on 2017/6/3.
 */
@Service
public class DemoAnnotationService {

    // 使用注解被拦截的类
    @Action(name="addAction")
    public void add() {
        System.out.println("DemoAnnotationService.add");
    }
}
