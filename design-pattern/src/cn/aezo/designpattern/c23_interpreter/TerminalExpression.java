package cn.aezo.designpattern.c23_interpreter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 终结符表达式类
 *
 * @author smalle
 * @date 2020-06-14 11:48
 */
public class TerminalExpression implements Expression {
    private Set<String> set= new HashSet<>();

    public TerminalExpression(String[] data) {
        set.addAll(Arrays.asList(data));
    }

    public boolean interpret(String info) {
        return set.contains(info);
    }
}