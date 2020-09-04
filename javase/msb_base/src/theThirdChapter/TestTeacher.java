package theThirdChapter;

public class TestTeacher {
    public static void main(String args[]) {
        Person p1 = new Person("A");
        Person p2 = new Person("B", "shanghai");
        Teacher t1 = new Teacher("D", "Professor");
        System.out.println(p1.info());
        System.out.println(p2.info());
        System.out.println(t1.info());
    }

}
