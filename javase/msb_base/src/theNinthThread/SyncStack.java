package theNinthThread;

public class SyncStack {
    int index = 0;
    WoTou[] arrWT = new WoTou[6];

    public synchronized void push(WoTou wt) {
        arrWT[index] = wt;
        index++;
    }

    public synchronized WoTou pop() {
        index--;
        return arrWT[index];
    }


}
