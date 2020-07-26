package cn.aezo.concurrent_msb.c08_1_Disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author smalle
 * @date 2020-05-31 11:53
 */
public class Main {
    public static void handleEvent(MyEvent event, long sequence, boolean endOfBatch) {
        System.out.println(event.get());
    }

    public static void translate(MyEvent event, long sequence, ByteBuffer buffer) {
        event.set(buffer.getLong(0));
    }

    public static void main(String[] args) throws InterruptedException {
        // Specify the size of the ring buffer, must be power of 2. (长度为2的n次幂，利于二进制计算)
        int bufferSize = 1024;

        // 使用Lambda传入EventFactory，也可手动实现EventFactory接口再传入
        Disruptor<MyEvent> disruptor = new Disruptor<>(MyEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(Main::handleEvent);

        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<MyEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        // 不推荐。原因是这是一个capturing lambda, 每一个lambda会产生一个对象来承接bb，这样会产生大量的小对象
        // ringBuffer.publishEvent((event, sequence) -> event.set(bb.getLong(0)));
        // 推荐
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent(Main::translate, bb);
            Thread.sleep(1000);
        }
    }
}
