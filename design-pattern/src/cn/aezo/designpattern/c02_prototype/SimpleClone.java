package cn.aezo.designpattern.c02_prototype;

/**
 * 结果：
 *
 * 具体原型创建成功！
 * 具体原型复制成功！
 * o1 == o2? false
 *
 * @author smalle
 * @date 2020-06-09 12:56
 */
public class SimpleClone implements Cloneable {
    private String name;
    private Object o;

    public SimpleClone(String name, Object o) {
        this.name = name;
        this.o = o;
        System.out.println("具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Object o = new Object();
        SimpleClone o1 = new SimpleClone("o1", o);
        SimpleClone o2 = (SimpleClone) o1.clone();

        System.out.println("o1.name equals o2.name? " + (o1.name.equals(o2.name)));
        System.out.println("o1.o equals o2.o? " + (o1.o.equals(o2.o)));
        System.out.println("o1.o == o2.o? " + (o1.o == o2.o));
    }
}
