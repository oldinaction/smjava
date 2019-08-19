package cn.aezo.javase.concurrent.scattered;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * 基于 MultiThreadedTestRunner 第三方jar的测试模板
 * @author smalle
 * @date 2019-07-23 13:58
 */
public abstract class MultiThreadTestTemplate {
    private static int runnerCount = 20;

    abstract void beforeExec();
    abstract void exec();
    abstract void afterExec();

    public void run(int runnerCount) {
        MultiThreadTestTemplate.runnerCount = runnerCount;
        this.run();
    }

    public void run() {
        beforeExec();

        // 构造一个Runner
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                // 测试内容
                exec();
            }
        };

        // Runner数组，想当于并发多少个
        TestRunnable[] arrTestRunner = new TestRunnable[runnerCount];
        for (int i = 0; i < runnerCount; i++) {
            arrTestRunner[i] = runner;
        }

        // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(arrTestRunner);
        try {
            // 并发执行数组里定义的内容
            mttr.runTestRunnables();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        afterExec();
    }

    static class TestDemo extends MultiThreadTestTemplate {
        public static void main(String[] args) {
            new TestDemo().run(100);
        }

        @Override
        void beforeExec() {
            System.out.println("开始...");
        }

        @Override
        void exec() {
            System.out.println(Thread.currentThread().getName() + "测试内容...");
        }

        @Override
        void afterExec() {
            System.out.println("结束...");
        }
    }
}
