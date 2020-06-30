package cn.aezo.netty.c1_bio_nio_aio_netty.t0_bio_chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
//客户端ChatClient
public class ChatClient extends Frame{
	//响应他的事件，需要去访问他的内容，所以定义为成员变量
	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	private boolean bConnected = false;
	Thread tRecv = new Thread(new RecvThread());


	public static void main(String[] args) {
		new ChatClient().launchFrame();

	}
	
	//运行客户端窗口的方法
	public void launchFrame(){
		this.setLocation(400,300);
		setSize(300,300);
		add(tfTxt,BorderLayout.SOUTH); //BorderLayout为Frame默认布局管理器。调用其他应调用setLayout(某个布局管理器对象)                         
		add(taContent,BorderLayout.NORTH);
		pack(); //如果没有pack();则两个控件中有空的部分
		//使用匿名类添加窗口的事件监听器，注意WindowAdapter()的使用，windowClosing将要关闭窗口
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		//把监听器添加在文本框上，此时回车直接返回对象的信息，不需要添加Enter的KeyListener
		tfTxt.addActionListener(new TFListener());
		setVisible(true);

		connect();

		tRecv.start();
	}
	
	public void connect(){
		try {
		s = new Socket("127.0.0.1",8888);
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		System.out.println("connected!");
		bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void disconnect() {
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim(); //String里面的方法trim()去掉两边的空格
			tfTxt.setText(""); //设置一个空字符串，让文本框里面的内容为空
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class RecvThread implements Runnable {
		@Override
		public void run() {
			try {
				while (bConnected) {
					// TextArea显示的内容都是从服务器读取的，包括自己发送的消息
					String str = dis.readUTF();
					taContent.setText(taContent.getText() + str + '\n');
				}
			} catch(SocketException e){
				System.out.println("退出了，bye!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
