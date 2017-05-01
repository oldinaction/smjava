package theThirdChapter;

public class Cat {
	int colour, weight, height;
	Cat(int colour, int weight, int height){
		this.colour = colour;
		this.weight = weight;
		this.height = height;
	}
	
	public boolean equals(Object obj){
		if(obj == null) return false;
		else{
			if(obj instanceof Cat){
				Cat c = (Cat)obj;
				if(c.colour == this.colour && c.weight == this.weight && c.height == this.height)
					return true;
			}
		}
		return false;
	}

}
