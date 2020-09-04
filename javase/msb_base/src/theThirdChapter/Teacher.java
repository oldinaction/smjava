package theThirdChapter;

public class Teacher extends Person {
    private String capital;

    Teacher(String name, String capital) {
        this(name, "beijing", capital); //���ô�������Ӧ����һ�����캯��
    }

    //���ع��캯��
    Teacher(String n, String l, String capital) {
        super(n, l); //ִ�и�����Ӧ�Ĺ��캯��
        this.capital = capital;
    }

    //��д����ĺ���info()
    public String info() {
        return super.info() + "capital:" + capital;
    }

}
