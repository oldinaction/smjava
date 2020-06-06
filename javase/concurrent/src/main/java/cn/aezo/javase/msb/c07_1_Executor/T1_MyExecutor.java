package cn.aezo.javase.msb.c07_1_Executor;

import java.util.concurrent.Executor;

/**
 * @author smalle
 * @date 2020-06-06 21:15
 */
public class T1_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        T1_MyExecutor o = new T1_MyExecutor();
        o.execute(() -> {
            System.out.println(Thread.currentThread().getName()); // main
        });

    }
}
