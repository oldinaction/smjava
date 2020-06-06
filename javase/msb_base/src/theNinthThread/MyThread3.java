package theNinthThread;

class MyThread3 extends Thread {
	  MyThread3(String s){super(s);}
	  public void run(){
	    for(int i =1;i<=100;i++){
	      System.out.println(getName()+": "+i);
	      if(i%10==0){
	        yield();
	      }
	    }
	  }
}
