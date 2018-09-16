package cn.aezo.demo.jackson.jsontypeinfo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by smalle on 2017/3/26.
 */
public class Zoo {
    public Animal animal;

    public Zoo() {}

    public Zoo(final Animal animal) {
        this.animal = animal;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({ @JsonSubTypes.Type(value = Dog.class, name = "dog"), @JsonSubTypes.Type(value = Cat.class, name = "cat") })
    public static class Animal {
        public String name;

        public Animal() {
        }

        public Animal(final String name) {
            this.name = name;
        }
    }

    @JsonTypeName("dog")
    public static class Dog extends Animal {
        public double barkVolume;

        public Dog() {
        }

        public Dog(final String name) {
            this.name = name;
        }
    }

    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;

        public Cat() {
        }

        public Cat(final String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        final Zoo.Dog dog = new Zoo.Dog("lacy");
        final Zoo zoo = new Zoo(dog);

        final String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println("result = " + result);
    }
}
