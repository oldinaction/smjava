package theSeventhChapter;

import java.util.*;
public class TestMap2 {
  public static void main(String args[]) {
    Map<String, Integer> m1 = new HashMap<String , Integer>();  //<String, Integer>此时运用了泛型，定义了容器类元素的类型
    m1.put("one", 1);
    m1.put("two", 2);
    m1.put("three", 3);

    System.out.println(m1.size());
    System.out.println(m1.containsKey("one"));

    if(m1.containsKey("two")) {
      //int i = ((Integer)m1.get("two")).intValue(); 运用泛型后，不需要强制转换
      int i = m1.get("two");
      System.out.println(i);
    }

  }
}
