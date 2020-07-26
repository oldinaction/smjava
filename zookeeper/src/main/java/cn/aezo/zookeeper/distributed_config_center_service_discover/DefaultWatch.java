package cn.aezo.zookeeper.distributed_config_center_service_discover;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author smalle
 * @date 2020-07-25 22:32
 */
public class DefaultWatch implements Watcher {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void await() throws InterruptedException {
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("DefaultWatch watchedEvent = " + watchedEvent);
        Event.KeeperState state = watchedEvent.getState();
        switch (state) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                countDownLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            case Closed:
                break;
        }
    }
}
