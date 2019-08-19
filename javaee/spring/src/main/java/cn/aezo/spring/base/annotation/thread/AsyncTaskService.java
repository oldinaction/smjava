package cn.aezo.spring.base.annotation.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by smalle on 2017/6/10.
 */
@Service
public class AsyncTaskService {
    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("i = " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("i+1 = " + (i+1));
    }
}
