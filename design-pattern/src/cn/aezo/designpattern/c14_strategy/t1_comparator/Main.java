package cn.aezo.designpattern.c14_strategy.t1_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 结果：
 *
 * [Person{name='b', age=18}, Person{name='c', age=30}, Person{name='a', age=50}]
 * [Person{name='a', age=50}, Person{name='b', age=18}, Person{name='c', age=30}]
 *
 * @author smalle
 * @date 2020-06-13 17:17
 */
public class Main {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("c", 30));
        list.add(new Person("b", 18));
        list.add(new Person("a", 50));

        // 传入被排序集合和排序策略
        Collections.sort(list, new PersonAgeComparator());
        System.out.println(list);

        // Comparator使用了策略模式，因此此处可以很方便的改变排序策略
        Collections.sort(list, (Person o1, Person o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
        System.out.println(list);
    }
}
