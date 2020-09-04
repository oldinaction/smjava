package theThirdChapter;

public class TestEquals {
    public static void main(String args[]) {
        Cat c1 = new Cat(1, 2, 3);
        Cat c2 = new Cat(1, 2, 3);
        System.out.println(c1 == c2); //c1 == c2�Ƚϵ���c1 �� c2�������Ƿ���ͬ��ÿnewһ���������ö��ǲ�ͬ��
        System.out.println(c1.equals(c2)); //��ΪCat������д��Object���е�equals��������д�󣬱Ƚϵ���c1 �� c2�������Ƿ���ͬ�����û��д����Ч��c1 == c2�Ƚϵ���c1 �� c2�������Ƿ���ͬc1 == c2һ���Ƚϵ���c1 �� c2�������Ƿ���ͬ

        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2)); //���շ���ʱtrue����ΪString������Ҳ��д��equals�������μ�API�ĵ��е�String��
    }
}
