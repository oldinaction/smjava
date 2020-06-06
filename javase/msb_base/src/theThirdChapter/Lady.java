package theThirdChapter;

public class Lady {
	private String name;
	private Animal pet;
	Lady(String name, Animal pet){
		this.name = name;
		this.pet = pet; 
	}
	
	public void myPetEnjoy(){
		pet.enjoy();
	}

}
