package theSeventhChapter;

import java.util.*;

public class TestCollection {
    public static void main(String[] args) {
        Collection c = new ArrayList();  //ArrayList��Collection�����ࡣ���Է��벻ͬ���͵Ķ���
        c.add("hello");
        c.add(new Integer(100));
        System.out.println(c.size());
        System.out.println(c);
    }

}
