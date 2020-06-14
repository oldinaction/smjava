package cn.aezo.designpattern.c15_command;

/**
 * @author smalle
 * @date 2020-06-13 17:35
 */
public class ConcreteCommand implements Command {
    Receiver receiver = new Receiver();

    @Override
    public void execute() {
        receiver.action();
    }
}
