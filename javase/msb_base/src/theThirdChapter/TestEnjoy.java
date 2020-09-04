package theThirdChapter;

public class TestEnjoy {
    public static void main(String args[]) {
        Cat1 c = new Cat1("catname", "blue");
        Dog d = new Dog("dogname", "black");
        Lady l1 = new Lady("l1", c);
        Lady l2 = new Lady("l2", d);

        l1.myPetEnjoy();
        l2.myPetEnjoy();
    }

}
