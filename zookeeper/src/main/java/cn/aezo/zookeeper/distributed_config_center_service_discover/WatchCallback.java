package cn.aezo.zookeeper.distributed_config_center_service_discover;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author smalle
 * @date 2020-07-26 09:20
 */
public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private Config config;
    private ZooKeeper zk;

    private WatchCallback() {}

    public WatchCallback(Config config, ZooKeeper zk) {
        this.config = config;
        this.zk = zk;
    }

    public void await() throws InterruptedException {
        // 第一次不能使用getData而要用exists判断。因为如果节点不存在，此时监听事件将创建不成功，之后创建节点后就不会调用process处理
        // zk.getData("/config", this, this, "hello");
        zk.exists("/config", this, this, "hello");
        countDownLatch.await();
    }

    // zk.exists、zk.getData: Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("process..." + watchedEvent);
        Event.EventType type = watchedEvent.getType();
        switch (type) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/config", this, this, "hello");
                break;
            case NodeDeleted:
                config.setData(null);
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/config", this, this, "hello");
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }

    // zk.exists: AsyncCallback.StatCallback
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        if(stat != null) {
            zk.getData("/config", this, this, "hello");
        }
    }

    // zk.getData: AsyncCallback.DataCallback
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        System.out.println("processResult..." + bytes);
        if(bytes != null) {
            System.out.println("new String(bytes):" + new String(bytes));
            config.setData(new String(bytes));
            countDownLatch.countDown();
        }
    }
}
