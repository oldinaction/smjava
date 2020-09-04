
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(100, 200);
        f.setVisible(true);
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            System.out.println("�Ѿ������Ϸ�������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
