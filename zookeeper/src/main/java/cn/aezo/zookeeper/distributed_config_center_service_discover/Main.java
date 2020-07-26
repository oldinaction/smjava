package cn.aezo.zookeeper.distributed_config_center_service_discover;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 可作为分布式配置中心，或者相当于服务发现(数据中存放IP)
 *
 * 启动后同zk客户端连接，执行`create /test/config "v1"`创建配置节点，执行`set /test/config "v2"`修改节点数据，执行`delete /test/config`，观测数据变化
 *
 * @author smalle
 * @date 2020-07-25 22:14
 */
public class Main {

    ZooKeeper zk;

    @Before
    public void before() {
        zk = ZooKeeperUtil.getInstance();
    }

    @Test
    public void test() throws InterruptedException {
        Config config = new Config();
        WatchCallback watchCallback = new WatchCallback(config, zk);
        watchCallback.await();

        while (true) {
            String data = config.getData();
            if(data == null) {
                // 如果节点被删除了，则监听都会丢失，那么再创建时就无法监听到，因此此处再重新监听一次
                System.out.println("data lost...");
                watchCallback.await();
            } else {
                System.out.println("config.getData() = " + config.getData());
            }

            Thread.sleep(1000);
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
