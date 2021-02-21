package cn.aezo.smjava.javaee.spring5.bean.demo1;

import cn.aezo.smjava.javaee.spring5.bean.demo1.condition.MyWindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;

// @ComponentScan(value = "cn.aezo.demo", excludeFilters = {
//         @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})
// @ComponentScan(value = "cn.aezo.demo", includeFilters = {
//         @ComponentScan.Filter(type = FilterType.REGEX, pattern = "cn\\.aezo\\.test.*")}, useDefaultFilters = false)
// @ComponentScans({@ComponentScan()})
public class AppConfig {

    @Bean
    public MyBean myBean() {
        System.out.println("创建myBean...");
        MyBean myBean = new MyBean();
        myBean.setName("smalle");
        return myBean;
    }

    @Bean("myBean2")
    @Lazy
    public MyBean myBean2() {
        System.out.println("创建myBean2...");
        MyBean myBean = new MyBean();
        myBean.setName("aezocn");
        return myBean;
    }

    @Conditional({MyWindowsCondition.class}) // 必须符合所有的条件才会注入此Bean
    @Bean("myBean3")
    public MyBean myBean3() {
        return new MyBean();
    }
}
