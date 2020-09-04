package theThirdChapter;

public class Person {
    private String name;
    private String location;

    Person(String name) {
        this.name = name;
        location = "beijing";
    }

    Person(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String info() {
        return "name:" + name + "location:" + location;
    }


}
