package theThirdChapter;

public class TestCircle {
    public static void main(String args[]) {
        Circle c1 = new Circle(new Point2(1.0, 2.0), 2.0);
        Circle c2 = new Circle(5.0);
        System.out.println("c1:(" + c1.getO().getX() + "," + c1.getO().getY() + "," + c1.getRadius() + ")");
        System.out.println("c2:(" + c2.getO().getX() + "," + c2.getO().getY() + "," + c2.getRadius() + ")");
        System.out.println("c1 area" + c1.area());
        System.out.println("c2 area" + c2.area());

        c1.set(5, 6);
        c2.setRadius(9.0);
        System.out.println("c1:(" + c1.getO().getX() + "," + c1.getO().getY() + "," + c1.getRadius() + ")");
        System.out.println("c2:(" + c2.getO().getX() + "," + c2.getO().getY() + "," + c2.getRadius() + ")");
        System.out.println("c1 area" + c1.area());
        System.out.println("c2 area" + c2.area());

        Point2 p1 = new Point2(5.2, 6.3);
        System.out.println(c1.contains(p1));
        System.out.println(c1.contains(new Point2(10.0, 9.0)));
    }

}
