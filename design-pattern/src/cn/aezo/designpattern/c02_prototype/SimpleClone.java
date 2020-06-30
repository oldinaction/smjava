package cn.aezo.designpattern.c02_prototype;

/**
 * 结果：
 *
 * 具体原型创建成功！
 * 具体原型复制成功！
 * o1.name equals o2.name? true
 * o1.o equals o2.o? true
 * o1.o == o2.o? true
 * o1.data == o2.data? false
 *
 * @author smalle
 * @date 2020-06-09 12:56
 */
public class SimpleClone implements Cloneable {
    private String name;
    private Object o;
    private Data data;

    public SimpleClone(String name, Object o, Data data) {
        this.name = name;
        this.o = o;
        this.data = data;
        System.out.println("具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        // 实现Data的深克隆
        SimpleClone o = (SimpleClone) super.clone();
        o.data = (Data) data.clone();
        return o;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Object o = new Object();
        Data data = new Data("V1");
        SimpleClone o1 = new SimpleClone("o1", o, data);
        SimpleClone o2 = (SimpleClone) o1.clone();

        System.out.println("o1.name equals o2.name? " + (o1.name.equals(o2.name)));
        System.out.println("o1.o equals o2.o? " + (o1.o.equals(o2.o)));
        System.out.println("o1.o == o2.o? " + (o1.o == o2.o));
        System.out.println("o1.data == o2.data? " + (o1.data == o2.data));
    }
}

class Data implements Cloneable {
    String data;

    public Data(String data) {
        this.data = data;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
