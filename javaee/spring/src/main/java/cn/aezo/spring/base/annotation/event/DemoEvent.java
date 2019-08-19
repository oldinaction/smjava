package cn.aezo.spring.base.annotation.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by smalle on 2017/6/5.
 * 自定义事件
 */
public class DemoEvent extends ApplicationEvent {
    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
