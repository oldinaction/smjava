package cn.aezo.concurrent_msb.c06_RefType_ThreadLocal;

/**
 * @author smalle
 * @date 2020-06-06 09:58
 */
public class Z {
    private byte[] data;

    public Z() {}
    public Z(byte[] data) {
        this.data = data;
    }

    /**
     * 当一个对象被垃圾回收的时候会调用此方法，一般是不建议重写此方法的，此处仅为了测试
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize...");
    }
}
