package cn.aezo.zookeeper.distributed_config_center_service_discover;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author smalle
 * @date 2020-07-25 22:26
 */
public class ZooKeeperUtil {
    private static ZooKeeper zk;
    // 需提前创建好test目录，且之后使用时根目录为/test
    private static final String connectString = "192.168.6.131:2181,192.168.6.132:2181,192.168.6.133:2181/test";
    private static DefaultWatch defaultWatch = new DefaultWatch();

    public static ZooKeeper getInstance() {
        try {
            zk = new ZooKeeper(connectString, 3000, defaultWatch);
            defaultWatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return zk;
    }

}
