package theSeventhChapter;

public class Name {
    String firstName;
    String lastName;

    Name(String f, String l) {
        firstName = f;
        lastName = l;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLasttName() {
        return this.lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
