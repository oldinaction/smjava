package theEighthChapter;

import java.io.*;
import java.util.Date;

//先执行第二个例子（此时键盘输入的没有在第三个例子中打印，键盘的输入传给了args[0]，所以例三接收不到），再执行第三个例子，必须最后输入一个exit程序才会从例三的代码中跳出，否则例一永远不执行

public class TestPrintStream {
	//第二个例子的list方法，f传的是文件路径的字符串，p传的是System.out（标准输出流，输出到DOS界面）
	public static void list(String f, PrintStream p){
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));  //缓冲流才有readLine()方法
			String s = null;
			while((s = br.readLine()) != null){
				p.println(s);  //PrintStream里面有许多println和print方法，他继承了FilterOutputStream类
			}
			br.close();
		}catch (IOException e) {
		// TODO Auto-generated catch block
		p.println("无法读取文件");
		}
	}
	
	public static void main(String[] args){
		//第二个例子：在命令行输入一个文件名，把它里面的内容全部打印出来
		String filename = args[0];  //获取命令行中的字符串（如：C:\\Users\\Administrator\\Desktop/XX.txt），把他作为参数传给list方法
		if(filename != null)  list(filename, System.out);
		
		//第三个例子：日志记录，输出键盘上的输入和此时的时间。？？？为什么不能一直输入到文件？？？
		String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop/TPS.txt", true);  //在硬盘中创建文件
			PrintWriter log = new PrintWriter(fw);
			s = br.readLine();
			while(s  != null){
				if(s.equalsIgnoreCase("exit")) break;
				System.out.println(s.toUpperCase());
			    log.println(s.toUpperCase());
			    log.println(new Date());
			    log.flush();
			    log.close();
			    s = br.readLine();
			}	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//第一个例子：在一个文件里面打印0~60000的字符
		PrintStream ps = null;
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop/TPS.txt");  //上面已经创建了此文件
			ps = new PrintStream(fos);  //套装Print流
			if(ps != null){
				System.setOut(ps);  //重新分配“标准”输出流，原来输出在DOS控制台中，现在输出到ps流中
			}
			int num = 0;
			for(char c=0; c<=60000; c++){
				System.out.print(c + " ");  //上面已经setOut了，所以最终打印到文件里面了
				if(num++ >=100){
					System.out.println();
					num = 0;
				}  //每100个换行
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}




