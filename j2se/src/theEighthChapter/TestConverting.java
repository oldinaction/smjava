package theEighthChapter;

import java.io.*;

public class TestConverting {
	public static void main(String[] args){
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10/msb/TC.txt"));  //转换流
			osw.write("oldinaction");
			System.out.println(osw.getEncoding());  //getEncoding()返回此流使用的字符编码的名称。此时默认为系统编码（win中文版为GBK）
			osw.close();
			osw = new OutputStreamWriter(new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10/msb/TC.txt", true), "ISO8859_1");  //如果第二个参数为 true，则将字节写入文件末尾处，而不是写入文件开始处；"ISO8859_1"是一个西文编码
			osw.write('\t' + "oldinaction");  //'\t'是Tab的转义字符
			System.out.println(osw.getEncoding());
			osw.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InputStreamReader isr = new InputStreamReader(System.in);  //套装一个转换流，把字节流转换成字符流，System.in API文档中是一个InputStream，接受键盘的输入
		BufferedReader br = new BufferedReader(isr);  //套装一个缓冲流
		String s =  null;
		try {
			s =  br.readLine();  //缓冲流才有readLine()
			while(s != null){	
				if(s.equalsIgnoreCase("exit")) break;
				System.out.println(s.toUpperCase());
				br.close();	
				s =  br.readLine();  //每调用一次readLine()就要调用一次System.in（输入enter前的一行输入），到达连续输入
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
