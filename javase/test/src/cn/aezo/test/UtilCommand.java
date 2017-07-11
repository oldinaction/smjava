package cn.aezo.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UtilCommand {

	public static void main(String[] args) throws IOException {    
		//runCommand("notepad"); // 运行Window自带exe程序，如打开记事本
		runCommand("C:\\Program Files (x86)\\Tencent\\QQLite\\Bin\\QQScLauncher.exe");
//		runCommand("C:/Program Files (x86)/Tencent/QQLite/Bin/QQScLauncher.exe"); // 调用其他的可执行文件(下载安装的软件)，如打开QQ    
//		runCommand("rundll32 url.dll FileProtocolHandler file://C:/Users/smalle/Desktop/笔记.docx"); // 打开其他任意格式的文件(txt、word、excel等)，如打开word  
		//runCommand("cmd /C start C:\\Users\\smalle\\Desktop\\lightstreamer_message\\Lightstreamer6.0.1\\bin\\windows\\Start_LS_as_Application.bat"); // 打开bat文件  
		//Process process = runBat("C:\\Users\\smalle\\Desktop\\lightstreamer_message\\Lightstreamer6.0.1\\bin\\windows\\Start_LS_as_Application.bat");
		
		System.out.println(getProcessID());
		
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
        System.out.print("输入1关闭程序：");  
        String str = strin.readLine();
        
        if(str.equals("1")){
        	// runCommand("cmd /K start C:\\Users\\smalle\\Desktop\\lightstreamer_message\\Lightstreamer6.0.1\\bin\\windows\\Stop_LS_as_Application.bat");
        	// process.destroy();
        	//System.out.print(process); 
        }
		
	} 
	
	/**
	 * 运行window命令
	 * @Title: runCommand
	 * @author smalle
	 * @date 2016年5月12日 下午4:46:48
	 * @param command 命令
	 * @return Process
	 */
	public static Process runCommand(String command) { 
		Process process = null;
	    try {    
	    	process = Runtime.getRuntime().exec(command);
	    } catch (Exception e) {    
	        System.out.println("运行命令出错!");
	        e.printStackTrace();
	        return null;
	    }
	    System.out.println("运行命令完成!");
	    return process;
	}  
	
	/**
	 * 运行bat文件
	 * @Title: runBat
	 * @author smalle
	 * @date 2016年5月12日 下午4:46:23
	 * @param path 文件路径
	 * @return Process
	 */
	public static Process runBat(String path) {  
		Process process = null;
        try {  
        	String cmd = "cmd /c start " + path;
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) { 
        	System.out.println("读取文件失败!");
            ioe.printStackTrace(); 
            return null;
        }
        System.out.println("运行命令完成!");
        return process;
    }
	
	// process.destroy(); 关闭程序
	
	//获取当前java运行的PID(任务管理器中)
	public static final int getProcessID() {  
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]).intValue();  
    }
  
}