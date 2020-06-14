package cn.aezo.designpattern.c22_memento;

/**
 * 结果：
 *
 * 原始状态:S0
 * 新的状态:S1
 * 恢复状态:S0
 *
 * @author smalle
 * @date 2020-06-14 11:38
 */
public class Main {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("S0");
        System.out.println("原始状态:" + originator.getState());
        caretaker.setMemento(originator.createMemento()); // 保存状态

        originator.setState("S1");
        System.out.println("新的状态:" + originator.getState());

        originator.restoreMemento(caretaker.getMemento()); // 恢复状态
        System.out.println("恢复状态:" + originator.getState());
    }
}
