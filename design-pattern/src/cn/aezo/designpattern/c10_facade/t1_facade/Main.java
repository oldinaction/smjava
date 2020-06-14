package cn.aezo.designpattern.c10_facade.t1_facade;

/**
 * 结果：
 *
 * 接受到客户请求...
 * 子系统 SubSystemA 执行了一系列操作...
 * 子系统 SubSystemB 执行了一系列操作...
 * 子系统 SubSystemC 执行了一系列操作...
 * 向客户反馈结果...
 *
 * @author smalle
 * @date 2020-06-13 08:57
 */
public class Main {

    public static void main(String[] args) {

        Facade facade = new Facade();
        facade.service();
    }
}
