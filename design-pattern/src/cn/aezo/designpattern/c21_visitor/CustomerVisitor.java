package cn.aezo.designpattern.c21_visitor;

/**
 * 访问者模式
 *
 * Created by smalle on 2020-06-14 10:54.
 */
public interface CustomerVisitor {
    void visit(Wine mouse);
    void visit(Makeup keyboard);
}
