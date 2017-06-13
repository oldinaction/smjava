package cn.aezo.spring.base.annotation.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smalle on 2017/6/10.
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) // 5000毫秒. fixedRate每隔固定时间执行
    public void reportCurrentTime() {
        System.out.println("每隔5秒执行一次：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 50 14 ? * *") // 每天14.50执行
    public void fixTimeException() {
        System.out.println("在指定时间执行：" + dateFormat.format(new Date()));
    }
}
