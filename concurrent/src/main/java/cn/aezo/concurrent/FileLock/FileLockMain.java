package cn.aezo.concurrent.FileLock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;

/**
 * @author smalle
 * @since 2021-01-05
 */
public class FileLockMain {

    public static void main(String[] args) {
        FileChannel channel = null;
        FileLock lock = null;
        try {
            //1. 对于一个只读文件通过任意方式加锁时会报NonWritableChannelException异常
            //2. 无参lock()默认为独占锁，不会报NonReadableChannelException异常，因为独占就是为了写
            //3. 有参lock()为共享锁，所谓的共享也只能读共享，写是独占的，共享锁控制的代码只能是读操作，当有写冲突时会报NonWritableChannelException异常
            channel = new FileOutputStream("file-lock.txt",true).getChannel();
            RandomAccessFile raf = new RandomAccessFile("file-lock.txt","rw");

            //在文件末尾追加内容的处理
            raf.seek(raf.length());
            channel = raf.getChannel();

            //获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
            lock = channel.lock();//无参lock()为独占锁
            //lock = channel.lock(0L, Long.MAX_VALUE, true);//有参lock()为共享锁，有写操作会报异常

            //获得锁方法二：trylock()，非阻塞的方法，当文件锁不可用时，tryLock()会得到null值
            //do {
            //  lock = channel.tryLock();
            //} while (null == lock);

            //互斥操作
            ByteBuffer sendBuffer = ByteBuffer.wrap((new Date()+" 写入\n").getBytes());
            channel.write(sendBuffer);
            Thread.sleep(5000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
