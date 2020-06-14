package cn.aezo.designpattern.c05_builder;

/**
 * @author smalle
 * @date 2020-06-09 21:52
 */
public class PersonBuilder {
    protected Person person = new Person();

    public PersonBuilder buildName(String name) {
        person.setName(name);
        return this;
    }

    public PersonBuilder buildAge(Integer age) {
        person.setAge(age);
        return this;
    }

    public PersonBuilder buildSex(Integer sex) {
        person.setSex(sex);
        return this;
    }

    public Person build() {
        return person;
    }
}
