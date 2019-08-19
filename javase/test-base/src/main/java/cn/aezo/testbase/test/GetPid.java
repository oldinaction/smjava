package cn.aezo.testbase.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetPid {
	List<String> killTaskList = new ArrayList<String>();

	/**
	 * 启动进程
	 * 
	 * @throws IOException
	 */
	protected void doRun() throws IOException {
		//得到运行地址
		//String url = "C:\\Program Files (x86)\\Tencent\\QQLite\\Bin\\QQScLauncher.exe";
		String url = "cmd /K start C:\\Users\\smalle\\Desktop\\lightstreamer_message\\Lightstreamer6.0.1\\bin\\windows\\Start_LS_as_Application.bat";

		//得到任务列表ID集合
		List<String> oldTaskList = null;

		oldTaskList = getPids();

		//启动进程
		//Runtime.getRuntime().exec("cmd /c " + url);
		Runtime.getRuntime().exec(url);

		//得到最新任务列表ID集合
		List<String> newTaskList = null;

		newTaskList = getPids();

		//得到刚启动进程的ID集合
		for (int i = 0; i < newTaskList.size(); i++) {
			if (!oldTaskList.contains(newTaskList.get(i))) {
				killTaskList.add(newTaskList.get(i));
			}
		}
	}

	/**
	 * 停止进程
	 * 
	 * @throws IOException
	 */
	protected void doStop() throws IOException {

		//停止进程
		for (int i = 0; i < killTaskList.size(); i++) {
			Runtime.getRuntime().exec("Taskkill /f /IM " + killTaskList.get(i));
		}

	}

	/**
	 * 
	 * 取得所有进程ID集合
	 */
	private List<String> getPids() throws IOException {
		String path = System.getProperty("user.dir");
		final File createFileName = new File(path + "\\mytempscript.vbe");

		final PrintWriter pw = new PrintWriter(new FileWriter(createFileName,
				false), true);
		pw
				.println("for each ps in getobject(\"winmgmts:\\\\.\\root\\cimv2:win32_process\").instances_");
		pw.println("wscript.echo ps.handle&vbtab&ps.name");
		pw.println("next");
		pw.close();

		final InputStream ii = Runtime.getRuntime().exec(
				"cscript " + path + "\\mytempscript.vbe").getInputStream();

		final InputStreamReader ir = new InputStreamReader(ii);

		final BufferedReader br = new BufferedReader(ir);
		String str = null;
		String[] ss = null;
		List<String> taskList = new ArrayList<String>();
		while ((str = br.readLine()) != null) {
			if (str.endsWith(".exe")) {
				ss = str.split("\\s");
				for (int i = 0; i < ss.length; i += 2) {
					taskList.add(ss[i]);
				}
			}
		}
		ir.close();
		ii.close();
		br.close();
		return taskList;
	}
	
	public static void main(String[] args) throws IOException {
		GetPid getPid = new GetPid();
		getPid.doRun();
		
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
        System.out.print("输入1关闭程序："); 
        String str = strin.readLine();
        
        if(str.equals("1")){
        	getPid.doStop();
        }
	}

}
