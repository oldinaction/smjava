package cn.aezo.designpattern.c15_command;

/**
 * @author smalle
 * @date 2020-06-13 17:37
 */
public class Main {

    public static void main(String[] args) {
        Command command = new ConcreteCommand();
        command.execute();
    }
}
