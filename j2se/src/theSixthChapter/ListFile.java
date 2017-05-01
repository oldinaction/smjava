package theSixthChapter;

import java.io.File;

public class ListFile {
	public static void main(String[] args){
		File f = new File("h:/demo");
		System.out.println(f.getName());
		tree(f, 1);
	}
	//level代表的是子目录的层数，即要缩进的格数
	private static void tree(File f, int level){
		String preString = "";
		for(int i=0; i<level; i++){
			preString += "    ";
		}  //因为是递归，所以每次调用tree,那么level就发生了改变，此时preString得到的是循环完了的一个值，即第level层要缩进的格数
		
		File[] childFile = f.listFiles();  //listFiles()返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。即指返回他的子目录
		for(int i=0; i<childFile.length; i++){
			System.out.println(preString + childFile[i].getName());  //getName()返回由此抽象路径名表示的文件或目录的名称。
			if(childFile[i].isDirectory()){
				tree(childFile[i], level + 1); //递归的运用（在一个函数里面调用此函数）
			}  //isDirectory()测试此抽象路径名表示的文件是否是一个目录。
		}
	}

}
