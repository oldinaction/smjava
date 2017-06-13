package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//创建一个方框（游戏中的窗口）类，用来绘制所有窗口
//@ysq0526

public abstract class Layer {
	private static final int SIZE=7;
	protected static final int PADDING=16;
	
	private static Image WINDOW_IMG=new ImageIcon("graphics/window/Window.png").getImage();
	
	private static int WINDOW_W=WINDOW_IMG.getWidth(null);
	
	private static int WINDOW_H=WINDOW_IMG.getHeight(null);
	/*
	 * 窗口左上角x坐标
	 */
	protected int x;
	/*
	 * 窗口左上角y坐标
	 */
	protected int y;
	/*
	 * 窗口宽度
	 */
	protected int w;
	/*
	 * 窗口高度
	 */
	protected int h;
	
	protected Layer(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	
	protected void createWindow(Graphics g){
	//左上
	g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
	//中上
	g.drawImage(WINDOW_IMG, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, WINDOW_W-SIZE, SIZE, null);
	//右上
	g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WINDOW_W-SIZE, 0, WINDOW_W, SIZE, null);
	//左中
	g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, WINDOW_H-SIZE, null);
	//中
	g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE, null);
	//右中
	g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WINDOW_W-SIZE, SIZE, WINDOW_W, WINDOW_H-SIZE, null);
	//左下
	g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h, 0, WINDOW_H-SIZE, SIZE, WINDOW_H, null);
	//中下
	g.drawImage(WINDOW_IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, WINDOW_H-SIZE, WINDOW_W-SIZE, WINDOW_H, null);
	//右下
	g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WINDOW_W-SIZE, WINDOW_H-SIZE, WINDOW_W, WINDOW_H, null);
	
	}
	//？？？8-08:33,第8讲，08：:33时出现的,abstract抽象话，上面的也改为抽象类
	/**
	 * 刷新游戏具体类容，抽象类不能实例化，下面的方法的具体类容让子类实现
	 * @author Joshua
	 * @param g 画笔
	 */
	abstract public void paint(Graphics g);
}
