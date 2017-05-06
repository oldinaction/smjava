package cn.aezo.spel;

public class MyBean {
	private double randomNumber;
	private String userRegion;
	
	public double getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(double randomNumber) {
		this.randomNumber = randomNumber;
	}
	public String getUserRegion() {
		//System.getProperty("user.country");
		return userRegion;
	}
	public void setUserRegion(String userRegion) {
		this.userRegion = userRegion;
	}

}
