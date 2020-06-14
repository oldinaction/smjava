package cn.aezo.designpattern.c17_state;

/**
 * @author smalle
 * @date 2020-06-13 18:25
 */
public class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
