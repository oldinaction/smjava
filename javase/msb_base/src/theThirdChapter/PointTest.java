package theThirdChapter;

public class PointTest {
	public static void main(String args[]){
		Point p = new Point(1.0, 2.0, 3.0);
		Point p1 = new Point(0.0, 0.0, 0.0);
		System.out.println(p.getDistance(p1));
		
		p.setX(5.0);
		System.out.println(p.getDistance(new Point(1.0, 1.0, 1.0)));
	}

}
