package cn.aezo.ognl.model;


public class Cat {
	
	private Dog friend;
	
	public Cat() {
		System.out.println("cat constructor!");
	}
	
	public String miaomiao() {
		return "miaomiao";
	}

	public Dog getFriend() {
		return friend;
	}

	public void setFriend(Dog friend) {
		this.friend = friend;
	}

}
