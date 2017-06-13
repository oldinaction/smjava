package cn.aezo.spring.base.annotation.combineannotation;

import org.springframework.stereotype.Service;

/**
 * Created by smalle on 2017/6/11.
 */
@Service
public class DemoService {
    public void out() {
        System.out.println("combine annotation");
    }
}
