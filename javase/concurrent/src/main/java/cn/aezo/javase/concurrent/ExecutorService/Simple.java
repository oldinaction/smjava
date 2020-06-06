package cn.aezo.javase.concurrent.ExecutorService;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smalle
 * @date 2019-07-11 15:03
 */
public class Simple {

    /**
     * 结果：
     * End...
     * Asynchronous task...(是否打印是不确定的)
     */
    @Test
    public void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 睡眠后则不会打印"Asynchronous task..."
                // try {
                //     Thread.sleep(1000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }

                System.out.println("Asynchronous task...");
            }
        });

        System.out.println("End...");

        executorService.shutdown();
    }

    /**
     * 结果：
     * run...
     * future.get()=null
     * call...
     * future2.get() = my result...
     * end...
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void submit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // #1
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("run...");
            }
        });
        // 如果任务结束执行则返回 null。注意：只有调用了`future.get()`才会阻塞主线程
        System.out.println("future.get()=" + future.get());

        // #2
        Future future2 = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("call...");

                return "my result...";
            }
        });
        // 注意：只有调用了`future.get()`才会阻塞主线程
        System.out.println("future2.get() = " + future2.get());

        System.out.println("end...");
    }


    @Test
    public void inVokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);

                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);

        System.out.println("result = " + result);

        executorService.shutdown();
    }


    /**
     * 结果：
     * 333333
     * 1111111
     * 222222
     * end...
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(1111111);
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);

                System.out.println(222222);
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(333333);
                return "Task 3";
            }
        });

        // 主线程会阻塞在此处等待所有子线程结束
        List<Future<String>> futures = executorService.invokeAll(callables);

        // for(Future<String> future : futures){
        //     System.out.println("future.get = " + future.get());
        // }


        System.out.println("end...");

        executorService.shutdown();
    }


    /**
     * 结果：
     * 1...
     * e.getMessage() = java.util.concurrent.ExecutionException: java.lang.IllegalStateException
     * end...
     */
    @Test
    public void exception() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future future2 = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("1...");

                if( 2 > 1) {
                    throw new ExecutionException(new IllegalStateException());
                }

                System.out.println("2...");

                return "my result...";
            }
        });

        try {
            Object ret = future2.get(); // 注意：只有调用了`future.get()`才会阻塞主线程
            System.out.println("ret=" + ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        System.out.println("end...");
    }
}
