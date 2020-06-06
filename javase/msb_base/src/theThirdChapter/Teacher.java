package theThirdChapter;

public class Teacher extends Person {	
	private String capital;
	
	Teacher(String name, String capital){
		this(name, "beijing", capital); //调用此类中相应的另一个构造函数
	}
	//重载构造函数
	Teacher(String n, String l, String capital){
		super(n, l); //执行父类相应的构造函数
		this.capital = capital;
	}
	
	//重写父类的函数info()
	public String info(){
		return super.info() + "capital:" + capital;
	}

}
