package cn.aezo.spring.base.annotation.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by smalle on 2017/6/5.
 * 时间监听器
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String message = demoEvent.getMessage();
        System.out.println("DemoListener.onApplicationEvent==" + message);
    }
}
