package cn.aezo.designpattern.c16_chain_of_responsibility.t1;

/**
 * @author smalle
 * @date 2019-07-31 10:59
 */
public abstract class Handler {
    protected Handler next; // 持有后继的责任对象

    // 示意处理请求的方法，虽然这个示意方法是没有传入参数的。但实际是可以传入参数的，根据具体需要来选择是否传递参数
    // 也可以通过boolean判断是否继续执行
    public abstract boolean handleRequest();

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
