package cn.aezo.designpattern.c17_state;

/**
 * @author smalle
 * @date 2020-06-13 18:25
 */
public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}