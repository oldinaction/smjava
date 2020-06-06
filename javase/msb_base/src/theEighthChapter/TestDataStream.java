package theEighthChapter;

import java.io.*;

public class TestDataStream {
	public static void main(String[] args){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  //创建一个输出字节节点流
		DataOutputStream dos = new DataOutputStream(baos);  //套装一个数据流
		try {
			dos.writeDouble(Math.random());  //将东西从程序 写出 到内存
			dos.writeBoolean(true);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			System.out.println(bais.available());
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());  //将东西从内存 读入 到程序，在用print打印在控制台
			System.out.println(dis.readBoolean());  //先写的先读，故readDouble()在readBoolean()的前面
			dos.close();
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
