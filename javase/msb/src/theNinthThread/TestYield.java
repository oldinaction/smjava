package theNinthThread;

public class TestYield {
	  public static void main(String[] args) {
	    MyThread3 t1 = new MyThread3("t1");
	    MyThread3 t2 = new MyThread3("t2");
	    t1.start();
	    t2.start();
	  }
}
