package theFifthChapter;

public class Date {
    int year, month, day;

    Date(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    public int compare(Date date) {
        return year > date.year ? 1
                : year < date.year ? -1
                : month < date.month ? 1
                : month > date.month ? -1
                : day > date.day ? 1
                : day < date.day ? -1 : 0;
    }

    public String toString() {
        return year + "." + month + "." + day;
    }
}
