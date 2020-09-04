package theSixthChapter;

public class TestEnum {
    public enum MyColor {red, green, blue}

    ;  //����ö�����ͣ�enum�ؼ��ֺ�����������Ǵ�д��ͷ���ô����ţ������зֺ�

    public static void main(String[] args) {
        MyColor m = MyColor.red;
        //ע��switch��䣬case�����У�
        switch (m) {
            case red:
                System.out.println("red");
                break;
            case green:
                System.out.println("red");
                break;
            default:
                System.out.println("default");
        }
        System.out.println(m);
    }
}
