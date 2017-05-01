package theSixthChapter;

import java.io.File;
import java.io.IOException;
//如果此java文件在一个包中，则先找到这个包的顶级包目录，再在这个顶级包目录的父目录下创建抽象路径，而不是在java文件的父目录下创建
//如果这个文件不存在，第一次运行控制台无反应，第二次则有输出
public class TestFile {
	public static void main(String[] args){
		String separator = File.separator;  //查API文档separator是File类的一个final属性，指分隔符（例如：'\'）
		String filename = "myFile.txt";
		String directory = "myFile1" + separator + "myFile2";
		File f = new File(directory, filename); //File(String parent, String child) 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。
	
		if(f.exists()){
			System.out.println("文件名" + f.getAbsolutePath()); //getAbsolutePath()返回此抽象路径名的绝对路径名字符串。(即文件在硬盘的那个位置)
			System.out.println("文件大小" + f.length());
		}else{
			f.getParentFile().mkdirs(); //mkdirs()创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。因为myFile1中有myFile2，所以是一系列的
			try{
				f.createNewFile(); //createNewFile()如果指定的文件不存在并成功地创建，则返回 true；如果指定的文件已经存在，则返回 false 
			}catch(IOException e){
				e.printStackTrace();
			}
		}	
	}
}
