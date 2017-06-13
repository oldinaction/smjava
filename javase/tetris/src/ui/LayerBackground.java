package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer {
	private static Image IMG_GB_TEMP=new ImageIcon("graphics/background/aaa.jpg").getImage();

	protected LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(IMG_GB_TEMP,0,0,1162,654,null);

	}

}
