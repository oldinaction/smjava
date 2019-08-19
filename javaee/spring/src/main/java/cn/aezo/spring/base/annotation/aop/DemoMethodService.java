package cn.aezo.spring.base.annotation.aop;

import org.springframework.stereotype.Service;

/**
 * Created by smalle on 2017/6/3.
 */
@Service
public class DemoMethodService {
    public void add() {
        System.out.println("DemoMethodService.add");
    }
}
