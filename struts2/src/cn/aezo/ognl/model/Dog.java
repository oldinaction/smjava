package cn.aezo.ognl.model;


public class Dog {
	
	private String name;
	
	public Dog() {
		System.out.println("dog constructor!");
	}
	
	public Dog(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "D:" + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
