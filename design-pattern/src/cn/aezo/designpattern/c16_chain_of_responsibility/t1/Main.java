package cn.aezo.designpattern.c16_chain_of_responsibility.t1;

/**
 * @author smalle
 * @date 2019-07-31 11:00
 */
public class Main {

    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setNext(handler2);

        //提交请求
        handler1.handleRequest();
    }

}
