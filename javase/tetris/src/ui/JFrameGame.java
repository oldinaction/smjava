package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameGame extends JFrame {
	public JFrameGame(){
	//���ñ���
	this.setTitle("java����˹����");
	//���Ĭ�Ϲر����ԣ����������
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//���ô��ڴ�С
	this.setSize(1168,680);
	//�������û��ı䴰�ڴ�С
	this.setResizable(false);
	//����
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	Dimension screen=toolkit.getScreenSize();
	int x=(screen.width-this.getWidth())/2;
	int y=(screen.height-this.getHeight())/2-32;
	this.setLocation(x,y);
	//���Ĭ��Panel
	this.setContentPane(new JPanelGame());
	}
}
