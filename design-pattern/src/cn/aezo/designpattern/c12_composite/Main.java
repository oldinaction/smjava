package cn.aezo.designpattern.c12_composite;

/**
 * 结果：
 *
 * 枝干节点1
 *     叶子1
 *     枝干节点2
 *         叶子2
 *         叶子3
 *
 * @author smalle
 * @date 2020-06-13 13:13
 */
public class Main {

    public static void main(String[] args) {
        Component composite1 = new Composite("枝干节点1");
        Component composite2 = new Composite("枝干节点2");
        Component leaf1 = new Leaf("叶子1");
        Component leaf2 = new Leaf("叶子2");
        Component leaf3 = new Leaf("叶子3");

        composite1.add(leaf1);
        composite1.add(composite2);
        composite2.add(leaf2);
        composite2.add(leaf3);

        composite1.operation(0);
    }
}
