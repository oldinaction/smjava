package cn.aezo.designpattern.c19_mediator.t1_interface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smalle
 * @date 2020-06-14 09:52
 */
public class ConcreteMediator implements Mediator {
    private final List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague c) {
        if(!colleagues.contains(c)) {
            colleagues.add(c);
            c.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague c) {
        for(Colleague obj : colleagues) {
            if(!obj.equals(c)) {
                obj.receive();
            }
        }
    }
}
