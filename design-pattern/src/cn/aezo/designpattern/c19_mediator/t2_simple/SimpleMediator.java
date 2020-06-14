package cn.aezo.designpattern.c19_mediator.t2_simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smalle
 * @date 2020-06-14 09:52
 */
public class SimpleMediator {
    private static final List<Colleague> colleagues = new ArrayList<>();
    private static final SimpleMediator simpleMediator = new SimpleMediator();

    public static void register(Colleague c) {
        if(!colleagues.contains(c)) {
            colleagues.add(c);
        }
    }

    public static void relay(Colleague c) {
        for(Colleague obj : colleagues) {
            if(!obj.equals(c)) {
                obj.receive();
            }
        }
    }
}
