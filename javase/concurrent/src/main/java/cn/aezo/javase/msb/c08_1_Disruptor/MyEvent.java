package cn.aezo.javase.msb.c08_1_Disruptor;

/**
 * @author smalle
 * @date 2020-05-31 10:51
 */
public class MyEvent {
    private long value;

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }
}
