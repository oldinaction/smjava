package cn.aezo.designpattern.strategy;

// 或者实现java.lang.Comparable
public class Cat implements Comparable<Cat> {
	private int height;
	private int weight;
	private Comparator comparator = new CatHeightComparator();

	public Cat(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
	}

	@Override
	public int compareTo(Cat o) {
		return comparator.compare(this, o);
	}
	
	@Override
	public String toString() {
		return height + "|" + weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Comparator getComparator() {
		return comparator;
	}

	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
}
