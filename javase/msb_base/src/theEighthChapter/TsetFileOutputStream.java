package theEighthChapter;

import java.io.*;

public class TsetFileOutputStream {
	public static void main(String[] args){
		int num = 0;
		int b = 0;
		FileInputStream in = null; //若用FileReader和FileWriter则不会吧中文读一半，而是可以输出中文
		FileOutputStream out = null;
		
		try{
			in  = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter/TsetFileOutputStream.java");
			while((b = in.read()) != -1){
				System.out.print((char)b); //in.read()返回读到的一个字节对应得整数，再强制转换为char型，但是文件中的中文是2个字节，读只能读取一个字节，故不完整，转换后输出乱码
				num++;
			}
			in.close();  //一定要关闭文件
		}catch(IOException e){
			System.out.println("文件读取错误");
			System.exit(-1);
		}
		System.out.println("文件大小：" + num);
		
		try{
			//System.out.println(in.read());如果写在这，结果出错，输出“文件复制错误”，？？？此时 in到底是什么？？？
			in  = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter/TsetFileOutputStream.java");
			//System.out.println(in.read());如果写在这,结果正确，输出112（对应的是字母p）,即in.read()读的是第一个字节
			out  = new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\TFOS.java"); //在硬盘上创建了一个空内容TFOS.java文件,硬盘上必须有前面的目录，他不能创建目录
			//用System.out.println(b);可得知此时 b = -1; 
			while((b = in.read()) != -1){
				out.write(b);
			}
			in.close();
			out.close();	
		}catch(IOException e){
			System.out.println("文件复制错误");
			System.exit(-1);
		}
		System.out.println("文件复制成功！");  //路径下多出一个TFOS.java文件
	
	}
}
