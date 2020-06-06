package theThirdChapter;

public class Circle {
	private Point2 o; //圆类有圆心和半径的属性，圆心又是一个点对象
	private double radius;
	Circle(Point2 p, double r){
		o = p;
		radius = r;
	}
	Circle(double r){
		o = new Point2(0.0, 0.0);
		radius = r;	
	}
	
	boolean contains(Point2 p){
		double x = p.getX() - o.getX();
		double y = p.getY() - o.getY();
		if(x*x + y*y > radius * radius) return false;
		else return true;
	}
	
	public void set(double x, double y){
		o.setX(x);
		o.setY(y);
	}
	public Point2 getO() { return o; }
	public double getRadius() { return radius; }
	public void setRadius(double r) { radius = r; }
	public double area(){
		return 3.14 * radius * radius;
	}
	
	
	
	
	

}
