package cn.aezo.spring.base.annotation.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by smalle on 2017/6/10.
 */
@Configuration
@ComponentScan("cn.aezo.spring.base.annotation.scheduled")
@EnableScheduling
public class TaskScheduledConfig {
}
