package cn.aezo.designpattern.c12_composite;

/**
 * @author smalle
 * @date 2020-06-13 13:09
 */
public class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public void operation(int level) {
        String space = "";
        for (int i = 0; i < level; i++) {
            space += "    ";
        }
        System.out.println(space + name);
    }
}
