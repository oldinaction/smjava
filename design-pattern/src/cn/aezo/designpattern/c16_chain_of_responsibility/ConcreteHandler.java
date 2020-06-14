package cn.aezo.designpattern.c16_chain_of_responsibility;

/**
 * @author smalle
 * @date 2019-07-31 11:00
 */
public class ConcreteHandler extends Handler {
    @Override
    public boolean handleRequest() {
        // 判断是否有后继的责任对象。如果有，就转发请求给后继的责任对象；如果没有，则处理请求
        if(getNext() != null) {
            System.out.println("放过请求");

            return getNext().handleRequest();
        } else {
            System.out.println("处理请求");
            return true;
        }
    }
}