package cn.aezo.designpattern.c14_strategy.t0_comparable;

/**
 * 实现Comparable接口，则此对象变成可排序对象。需实现compareTo方法
 *
 * @author smalle
 * @date 2020-06-07 22:38
 */
public class MyComparable implements Comparable<MyComparable> {
    private String name;

    public MyComparable(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(MyComparable o) {
        return this.name.compareTo(o.name);
    }

    public static void main(String[] args) {
        MyComparable t1 = new MyComparable("aa");
        MyComparable t2 = new MyComparable("bb");

        System.out.println(t1.compareTo(t2)); // -1
    }
}
