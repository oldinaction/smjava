package cn.aezo.spring.base.annotation.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by smalle on 2017/6/5.
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext;

    public void publish(String message) {
        applicationContext.publishEvent(new DemoEvent(this, message));
    }
}
