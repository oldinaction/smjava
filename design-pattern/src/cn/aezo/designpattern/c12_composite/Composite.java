package cn.aezo.designpattern.c12_composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smalle
 * @date 2020-06-13 13:10
 */
public class Composite implements Component {
    private final List<Component> children = new ArrayList<>();

    private String name;

    public Composite(String name) {
        this.name = name;
    }


    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public void operation(int level) {
        String space = "";
        for (int i = 0; i < level; i++) {
            space += "    ";
        }
        System.out.println(space + name);

        level++;
        for(Component obj: children) {
            obj.operation(level);
        }
    }
}
