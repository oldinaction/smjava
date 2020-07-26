package cn.aezo.zookeeper.distributed_lock;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author smalle
 * @date 2020-07-26 12:01
 */
public class WatchCallback implements AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback, Watcher, AsyncCallback.StatCallback {
    private ZooKeeper zk;
    private String nodeData;
    private String nodePath;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private WatchCallback() {}

    public WatchCallback(ZooKeeper zk, String nodeData) {
        this.zk = zk;
        this.nodeData = nodeData;
    }

    public void lock() throws InterruptedException {
        // 增加可重入功能
        if(nodePath != null) {
            try {
                byte[] bytes = zk.getData(nodePath, false, new Stat());
                if(bytes != null && new String(bytes).equals(nodeData)) {
                    countDownLatch.await();
                    return;
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }

        zk.create("/lock", nodeData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "hello");
        countDownLatch.await();
    }

    public void unLock() {
        try {
            zk.delete(nodePath, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

        nodePath = null;
        countDownLatch = new CountDownLatch(1);
    }

    // zk.create: AsyncCallback.StringCallback
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("StringCallback, nodeData=" + nodeData + ", s1=" + s1 + ", s=" + s); // StringCallback, nodeData=Thread-1, s1=/lock0000000190, s=/lock
        if(s1 != null) {
            this.nodePath = s1;
            // 获取根目录(/test)的子节点，且不监控子节点变化(而是让后一个节点监控前一个节点变化)
            zk.getChildren("/", false, this, "hello");
        }
    }

    // zk.getChildren: AsyncCallback.ChildrenCallback
    @Override
    public void processResult(int i, String s, Object o, List<String> list) {
        System.out.println("ChildrenCallback, nodeData=" + nodeData + ", list=" + list); // ChildrenCallback, nodeData=Thread-1, list=[lock0000000198, lock0000000197, lock0000000196, lock0000000195, lock0000000199, lock0000000190, lock0000000194, lock0000000193, lock0000000192, lock0000000191]
        if(list != null) {
            Collections.sort(list); // 升序排列：先创建的，序号小，放在前面先执行
            int index = list.indexOf(nodePath.substring(1));
            if(index == 0) {
                // 当前节点为第一个元素
                try {
                    // 类似延迟一下，防止出现情况：节点顺序为A-B-C，当B发现前面有A时，正准备执行zk.exists增加A的监控时，A执行完毕移除了，则B监控失败，从而C也监控失败
                    zk.setData("/", nodeData.getBytes(),-1);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            } else {
                // 当前节点不为第一个元素，则监控前一个节点状态
                zk.exists("/" + list.get(index - 1), this, this, "hello");
            }
        }
    }

    // zk.exists: Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.EventType type = watchedEvent.getType();
        switch (type) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/", false, this, "hello");
                break;
            case NodeDataChanged:
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

    }
}
