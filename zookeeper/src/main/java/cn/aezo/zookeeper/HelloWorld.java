package cn.aezo.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 1.启动日志，可见session id = 0x100033765980001，且连接到的是192.168.6.131：
 *
 * 2020-07-25 13:24:23,918 [myid:] - INFO  [main:ZooKeeper@1005] - Initiating client connection, connectString=192.168.6.131:2181,192.168.6.132:2181,192.168.6.133:2181 sessionTimeout=5000 watcher=cn.aezo.zookeeper.App$1@27f8302d
 * 2020-07-25 13:24:23,923 [myid:] - INFO  [main:X509Util@77] - Setting -D jdk.tls.rejectClientInitiatedRenegotiation=true to disable client-initiated TLS renegotiation
 * 2020-07-25 13:24:24,634 [myid:] - INFO  [main:ClientCnxnSocket@239] - jute.maxbuffer value is 1048575 Bytes
 * 2020-07-25 13:24:24,649 [myid:] - INFO  [main:ClientCnxn@1703] - zookeeper.request.timeout value is 0. feature enabled=false
 * 2020-07-25 13:24:26,348 [myid:192.168.6.131:2181] - INFO  [main-SendThread(192.168.6.131:2181):ClientCnxn$SendThread@1154] - Opening socket connection to server 192.168.6.131/192.168.6.131:2181.
 * 2020-07-25 13:24:26,349 [myid:192.168.6.131:2181] - INFO  [main-SendThread(192.168.6.131:2181):ClientCnxn$SendThread@1156] - SASL config status: Will not attempt to authenticate using SASL (unknown error)
 * 2020-07-25 13:24:26,354 [myid:192.168.6.131:2181] - INFO  [main-SendThread(192.168.6.131:2181):ClientCnxn$SendThread@986] - Socket connection established, initiating session, client: /192.168.6.1:52677, server: 192.168.6.131/192.168.6.131:2181
 * 2020-07-25 13:24:26,394 [myid:192.168.6.131:2181] - INFO  [main-SendThread(192.168.6.131:2181):ClientCnxn$SendThread@1420] - Session establishment complete on server 192.168.6.131/192.168.6.131:2181, session id = 0x100033765980001, negotiated timeout = 3000
 * ZooKeeper watchedEvent = WatchedEvent state:SyncConnected type:None path:null
 * ZooKeeper path = null
 * ZooKeeper SyncConnected...
 * CONNECTED...
 * s = /aezo
 * ......此处省略testGetSync和testGetAsync的日志
 *
 * 2.当停掉192.168.6.131上的ZK服务时，日志如下：此时会自动重新连接192.168.6.132这台ZK服务器，且session id = 0x100033765980001不变
 *
 * 2020-07-25 13:25:39,978 [myid:192.168.6.131:2181] - WARN  [main-SendThread(192.168.6.131:2181):ClientCnxn$SendThread@1272] - Session 0x100033765980001 for sever 192.168.6.131/192.168.6.131:2181, Closing socket connection. Attempting reconnect except it is a SessionExpiredException.
 * java.io.IOException: 远程主机强迫关闭了一个现有的连接。
 * ......
 * getData watchedEvent = WatchedEvent state:Disconnected type:None path:null
 * 2020-07-25 13:25:40,236 [myid:192.168.6.132:2181] - INFO  [main-SendThread(192.168.6.132:2181):ClientCnxn$SendThread@1154] - Opening socket connection to server ingress.aezocn.local/192.168.6.132:2181.
 * 2020-07-25 13:25:40,237 [myid:192.168.6.132:2181] - INFO  [main-SendThread(192.168.6.132:2181):ClientCnxn$SendThread@1156] - SASL config status: Will not attempt to authenticate using SASL (unknown error)
 * 2020-07-25 13:25:40,240 [myid:192.168.6.132:2181] - INFO  [main-SendThread(192.168.6.132:2181):ClientCnxn$SendThread@986] - Socket connection established, initiating session, client: /192.168.6.1:52722, server: ingress.aezocn.local/192.168.6.132:2181
 * 2020-07-25 13:25:40,256 [myid:192.168.6.132:2181] - INFO  [main-SendThread(192.168.6.132:2181):ClientCnxn$SendThread@1420] - Session establishment complete on server ingress.aezocn.local/192.168.6.132:2181, session id = 0x100033765980001, negotiated timeout = 5000
 * ZooKeeper watchedEvent = WatchedEvent state:Disconnected type:None path:null
 * ZooKeeper path = null
 * getData watchedEvent = WatchedEvent state:SyncConnected type:None path:null
 * ZooKeeper watchedEvent = WatchedEvent state:SyncConnected type:None path:null
 * ZooKeeper path = null
 * ZooKeeper SyncConnected...
 */
public class HelloWorld {
    public static void main( String[] args ) throws IOException, InterruptedException, KeeperException {

        // 创建时，会迅速返回一个ZooKeeper对象，然后异步去连接服务器，因此可通过CountDownLatch等待
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 此处设置session超时时间为3s(即表示断开连接，如此线程执行完毕，等待3s之后，session消失，对应的临时节点也会消失)。由于session的存在，因此zookeeper连接时不存在线程池的概念
        // 如果连接的服务器挂掉，重新连接由ZooKeeper完成，无需重新创建ZooKeeper对象
        ZooKeeper zk = new ZooKeeper("192.168.6.131:2181,192.168.6.132:2181,192.168.6.133:2181", 3000, new Watcher() {
            // ZooKeeper连接服务的监听
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.EventType type = watchedEvent.getType();
                Event.KeeperState state = watchedEvent.getState();
                String path = watchedEvent.getPath();

                System.out.println("ZooKeeper watchedEvent = " + watchedEvent);
                System.out.println("ZooKeeper path = " + path);

                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        System.out.println("ZooKeeper NodeCreated...");
                        break;
                    case NodeDeleted:
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

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        System.out.println("ZooKeeper SyncConnected...");
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
        });

        countDownLatch.await();
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("CONNECTING...");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("CONNECTED...");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        // 创建节点
        String s = zk.create("/aezo", "v1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("s = " + s);

        // 获取数据
        testGetSync(zk);
        testGetAsync(zk);

        Thread.sleep(1000*60);
        zk.close();

    }

    /**
     * 打印如下：
     *
     * new String(b) = v1
     * stat = 25769803781,25769803781,1595651178729,1595651178729,0,0,0,216176315692285952,2,0,25769803781
     *
     * getData watchedEvent = WatchedEvent state:SyncConnected type:NodeDataChanged path:/aezo
     * newStat1 = 25769803781,25769803782,1595651178729,1595651178802,1,0,0,216176315692285952,2,0,25769803781
     *
     * getData watchedEvent = WatchedEvent state:SyncConnected type:NodeDataChanged path:/aezo // 此行为增加zk.getData("/aezo", this, stat);时才会有
     * newStat2 = 25769803781,25769803783,1595651178729,1595651178821,2,0,0,216176315692285952,2,0,25769803781
     *
     */
    private static void testGetSync(ZooKeeper zk) throws KeeperException, InterruptedException {
        // 创建一个Stat用于存储节点元信息；节点的数据信息同步方法可直接返回异步方法可通过回调获取
        Stat stat = new Stat();
        byte[] b = zk.getData("/aezo", new Watcher() {
            // 此Watcher是监控节点在数据发生变化时触发，且只会触发一次
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData watchedEvent = " + watchedEvent);

                // 由于Watcher只会触发一次，因此此处重新watch，从而每次修改数据都可以监控到
                try {
                    zk.getData("/aezo", this, stat);
                    // zk.getData("/aezo", true, stat); // true表示重新创建new ZooKeeper时的监控
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println("new String(b) = " + new String(b)); // new String(b) = v1
        System.out.println("stat = " + stat);


        Stat newStat = zk.setData("/aezo", "v2".getBytes(), 0);
        System.out.println("newStat1 = " + newStat);

        // 如果不在Watcher中增加zk.getData("/aezo", this, stat);的重新调用，则第二次修改数据不会被监听到
        newStat = zk.setData("/aezo", "v2".getBytes(), newStat.getVersion());
        System.out.println("newStat2 = " + newStat);
    }

    /**
     * ------async start--------
     * ------async end--------
     * ------async callback--------
     * o = abc
     * new String(bytes) = v2
     */
    private static void testGetAsync(ZooKeeper zk) {
        System.out.println("------async start--------");
        zk.getData("/aezo", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData2 watchedEvent = " + watchedEvent);
            }
        }, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("------async callback--------");
                System.out.println("o = " + o); // o = abc
                System.out.println("new String(bytes) = " + new String(bytes)); // new String(bytes) = v2
            }
        }, "abc");
        System.out.println("------async end--------");
    }
}
