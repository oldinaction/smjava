package theEighthChapter;

import java.io.*;

public class TestBufferStream {
	public static void main(String[] args){
		try {
			int c = 0;
			FileInputStream fis = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter\\TestBufferStream.java");
			BufferedInputStream bis = new BufferedInputStream(fis);
			System.out.println(bis.read());
			
			//因为前面读了p这个字符，所以下面从a开始读的
			bis.mark(100);  //在此输入流中标记当前的位置,不管里面的值是多少总是在当前位置 
			for(int i=0; i<10 && (c = bis.read()) != -1; i++){
				System.out.print((char)c+" ");
			}
			System.out.println();
			
			bis.reset();
			for(int i=0; i<10 && (c = bis.read()) != -1; i++){
				System.out.print((char)c+" ");
				System.out.print(c + " ");
				System.out.print(",");
			}
			
			bis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\java\\Workspaces\\MyEclipse 10\\msb/TBS.txt"));  //在硬盘上创建了一个空内容TBS.java文件
			BufferedReader br = new BufferedReader(new FileReader("H:\\java\\Workspaces\\MyEclipse 10\\msb/TBS.txt"));
			String s = null;
			for(int i=0; i<100; i++){
				s = String.valueOf(Math.random());  //把double型的随机数转换为字符串
				bw.write(s);
				bw.newLine();  //写入一个行分隔符
			}
			bw.flush();
			while((s = br.readLine()) != null){
				System.out.println(s);
			}  //readLine()读取一行字符串
			bw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
