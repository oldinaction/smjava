package theSeventhChapter;

import java.util.*;

public class TestIterator {
    public static void main(String[] args) {
        Collection c = new HashSet();  //��������ָ���������
        c.add(new Name("aaaa", "bbb"));
        c.add(new Name("ccc", "dd"));
        c.add(new Name("ee", "f"));
        Iterator i = c.iterator();  //c.iterator()�����ڴ� collection ��Ԫ���Ͻ��е����ĵ�������

        while (i.hasNext()) {
            Name n = (Name) i.next();
            System.out.print(n.getFirstName() + "  ");
        }
        System.out.println();
        //ע���forѭ��
        for (i = c.iterator(); i.hasNext(); ) {
            Name n = (Name) i.next();  //����ǿ��ת��
            if (n.getFirstName().length() < 3) {
                i.remove();  //i.remove()�ӵ�����ָ��� collection ���Ƴ����������ص����һ��Ԫ��
            }
        }
        System.out.println(c);
    }
}
