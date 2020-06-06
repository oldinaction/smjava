package theThirdChapter;

public class Dog extends Animal {
	private String furcolour;
	Dog(String n, String c){
		super(n);
		this.furcolour = c;
	}
	
	public void enjoy(){
		System.out.println("昂出蕗。。。。");
	}

}
