package cn.aezo.javase.msb.c05_3_Queue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 结果：
 *
 * Thu Jun 04 08:17:19 CST 2020
 * Thread-1 ==> TestDelayed{name='t1', takeTime=Thu Jun 04 08:17:20 CST 2020}
 * Thread-0 ==> TestDelayed{name='t3', takeTime=Thu Jun 04 08:17:21 CST 2020}
 * Thread-2 ==> TestDelayed{name='t2', takeTime=Thu Jun 04 08:17:24 CST 2020}
 *
 * @author smalle
 * @date 2020-06-04 08:05
 */
public class T3_DelayQueue {

    public static void main(String[] args) {
        DelayQueue<TestDelayed> delayQueue = new DelayQueue<>();

        long l = System.currentTimeMillis();
        delayQueue.put(new TestDelayed("t1", new Date(l + 1000)));
        delayQueue.put(new TestDelayed("t2", new Date(l + 5000)));
        delayQueue.put(new TestDelayed("t3", new Date(l + 2000)));

        System.out.println(new Date());
        for (int i = 0;i < 3;i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " ==> " + delayQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    static class TestDelayed implements Delayed {
        private String name;
        private Date takeTime; // 延迟时间

        public TestDelayed(String name, Date takeTime) {
            this.name = name;
            this.takeTime = takeTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(Date takeTime) {
            this.takeTime = takeTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(takeTime.getTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return convert;
        }

        @Override
        public int compareTo(Delayed o) {
            TestDelayed t = (TestDelayed)o;
            long l = this.takeTime.getTime() - t.getTakeTime().getTime();
            if (l==0){
                return 0;
            }
            return l > 0 ? 1 : -1;
        }

        @Override
        public String toString() {
            return "TestDelayed{" +
                    "name='" + name + '\'' +
                    ", takeTime=" + takeTime +
                    '}';
        }
    }
}
