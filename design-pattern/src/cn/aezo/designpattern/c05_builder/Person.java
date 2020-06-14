package cn.aezo.designpattern.c05_builder;

/**
 * @author smalle
 * @date 2020-06-09 21:45
 */
public class Person {

    private String name;

    private Integer age;

    private Integer sex;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
