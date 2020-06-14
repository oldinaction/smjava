package cn.aezo.designpattern.c14_strategy.t1_comparator;

import java.util.Comparator;

/**
 * @author smalle
 * @date 2020-06-13 17:19
 */
public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
