package cn.aezo.designpattern.strategy;

// 或者实现java.util.Comparator
public class CatHeightComparator implements Comparator<Cat> {
	@Override
	public int compare(Cat c1, Cat c2) {
		if (c1.getHeight() > c2.getHeight()) return 1;
		else if(c1.getHeight() < c2.getHeight()) return -1;
		return 0;
	}
}
