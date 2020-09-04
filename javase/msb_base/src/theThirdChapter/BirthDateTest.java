package theThirdChapter;

public class BirthDateTest {
    public static void main(String args[]) {
        BirthDateTest test = new BirthDateTest();
        int date = 9;
        BirthDate b1 = new BirthDate(7, 7, 1970);
        BirthDate b2 = new BirthDate(1, 1, 2000);

        test.change1(date);
        test.change2(b1);
        test.change3(b2);

        System.out.println("date=" + date);
        b1.disPlay();
        b2.disPlay();
    }

    public void change1(int d) {
        d = 1234;
    }

    public void change2(BirthDate b) {
        b = new BirthDate(22, 2, 2004);
    }

    public void change3(BirthDate b) {
        b.setDay(22);
    }
}
