package cn.aezo.designpattern.c05_builder;

/**
 * @author smalle
 * @date 2020-06-09 22:06
 */
public class Main {

    public static void main(String[] args) {

        PersonBuilder builder = new PersonBuilder();
        Person person = builder.buildName("smalle").buildAge(18).buildSex(1).build();
        System.out.println(person); // Person{name='smalle', age=18, sex=1}
    }

}
