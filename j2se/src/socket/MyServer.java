package socket;

import java.io.IOException;
import java.net.*;

public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true) {
				Socket s = ss.accept();
				System.out.println("有客户端连上");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

