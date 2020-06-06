package cn.aezo.javase.msb.c07_2_Callable_Future;


import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smalle
 * @date 2020-06-06 17:28
 */
public class T1_Callable {

    public static void main(String[] args) throws IOException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("run...");
                return "hello...";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(callable);
        executorService.shutdown();
    }

}
