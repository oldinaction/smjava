package cn.aezo.zookeeper.distributed_lock;

import cn.aezo.zookeeper.distributed_config_center_service_discover.ZooKeeperUtil;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 实现分布式锁
 *
 * @author smalle
 * @date 2020-07-26 11:17
 */
public class Main {

    ZooKeeper zk;

    @Before
    public void before() {
        // 需清除/test目录下子节点
        zk = ZooKeeperUtil.getInstance();
    }

    @Test
    public void test() {
        // 创建10个线程模拟10个客户端(实际可能是10个客户端，每个只启动一个线程)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                WatchCallback watchCallback = new WatchCallback(zk, Thread.currentThread().getName());
                // 获取锁
                try {
                    watchCallback.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 执行操作
                System.out.println("===========>" + Thread.currentThread().getName() + " working......");

                // 释放锁
                watchCallback.unLock();
            }).start();
        }

        while (true) {

        }
    }

    @After
    public void after() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
