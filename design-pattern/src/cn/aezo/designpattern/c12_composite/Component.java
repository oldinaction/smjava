package cn.aezo.designpattern.c12_composite;

/**
 * 透明方式：在该方式中，由于抽象构件声明了所有子类中的全部方法，所以客户端无须区别树叶对象和树枝对象，对客户端来说是透明的
 * 但其缺点是：树叶构件本来没有 add()、remove() 方法，却要实现它们（空实现或抛异常），这样会带来一些安全性问题
 *
 * 安全方式：将管理子构件的方法移到树枝构件中，抽象构件和树叶构件没有对子对象的管理方法，这样就避免了透明方式的安全性问题
 * 但由于叶子和分支有不同的接口，客户端在调用时要知道树叶对象和树枝对象的存在，所以失去了透明性
 *
 * @author smalle
 * @date 2020-06-13 13:07
 */
public interface Component {
    void add(Component c);
    void remove(Component c);
    void operation(int level);
}
