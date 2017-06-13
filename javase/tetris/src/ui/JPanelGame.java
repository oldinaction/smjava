package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class JPanelGame extends JPanel{
	private Layer[] lays=null;  
	public JPanelGame(){
		lays=new Layer[]{
				new LayerBackground(0,0,0,0),              //此处也可以是new LayBackground(0,0,1162,654),
				new LayerDataBase(40,32,334,279),
				new LayerDisk(40,343,334,279),
				new LayerGame(414,32,334,590),
				new LayerButton(788,32,334,124),
				new LayerNext(788,188,176,148),
				new LayerLevel(964,188,158,148),
				new LayerPoint(788,368,334,200),
		};	 
    }
    
    @Override
    public void paintComponent(Graphics g){
    	//循环刷新游戏画面
    	for(int i=0;i<lays.length;i++){
            //刷新层窗口,和Lay类中的paint对应
    		lays[i].paint(g);
    	}
    
    }
}
    