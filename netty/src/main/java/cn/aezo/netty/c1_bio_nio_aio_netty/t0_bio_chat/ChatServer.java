package cn.aezo.netty.c1_bio_nio_aio_netty.t0_bio_chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args){		
		new ChatServer().start(); //不能直接这样调用start()
	}

	public void start(){
		try {
			ss = new ServerSocket(8888); //在端口8888建立监听
			started = true; //建立监听成功令此变量为真
		}catch (SocketException e){
			System.out.println("端口使用中。。。");
			System.out.println("请关掉相关程序！");
			System.exit(0);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			while(started){
				Socket s = ss.accept(); //***重要***若此处报错，尝试用360进行LSP修复
				Client c = new Client(s); //每接受一个客户端就会new一个线程 
				System.out.println("a client connected");
				new Thread(c).start(); //启动线程，start()方法只是启动线程,而线程中的run()方法这是实现此线程功能的代码
				clients.add(c);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//定义一个线程内部类
    class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s){
			this.s=s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true; // 有一个客户端连上后另此变量为真
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}

		public void send(String str){
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);//防止有一个客户端退出了，其他客户端还在发消息，此时List里面还有这个退出的客户端，服务器就会报错
				System.out.println("对方退出了，已经从List里面去除了！");
			}
		}
		
		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF(); //readUTF()是一个阻塞式的，main方法执行到此处就一直一行一行的读，程序停在此处
					System.out.println(str);
					// 读到消息将消息发送到所有的客户端，包括发送消息的客户端(从而显示在客户端的TextArea上)
					for(int i = 0; i < clients.size(); i++){
						Client c = clients.get(i);
						c.send(str);
					}
				}
			}catch(EOFException e){
				System.out.println("Client closed!");
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
    
}

    
    
