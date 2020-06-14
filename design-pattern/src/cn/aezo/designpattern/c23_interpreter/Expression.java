package cn.aezo.designpattern.c23_interpreter;

/**
 * @author smalle
 * @date 2020-06-14 11:48
 */
public interface Expression {
    boolean interpret(String info); // 解释方法
}
