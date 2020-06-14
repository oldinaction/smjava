package cn.aezo.designpattern.c14_strategy.t1_comparator;

import java.util.Comparator;

/**
 * 基于java.util.Comparator演示比较策略，需要实现compare方法
 *
 * @author smalle
 * @date 2020-06-13 17:20
 */
public class PersonAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if(o1.getAge() > o2.getAge()) return 1;
        else if(o1.getAge() < o2.getAge()) return -1;
        else return 0;
    }
}