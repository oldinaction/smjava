<%@page import="java.awt.Font"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%!
	//获取随机颜色。bc必须大于fc
	public static Color getRandColor(int fc, int bc) 
	{ 
		Random random = new Random(); 
		if(fc > 255) fc=255; 
		if(bc > 255) bc=255; 
		int r = fc + random.nextInt(bc - fc); 
		int g = fc + random.nextInt(bc - fc); 
		int b = fc + random.nextInt(bc - fc); 
		return new Color(r, g, b); 
	}

	//得到随机的字符串，如：1+1
	public static HashMap<String,String> getRandDomString(){
		Random random = new Random();
		int start = random.nextInt(10); //产生0-9的数字
		int end = random.nextInt(10);
		int mark = random.nextInt(4);
		int result = 0;
		String rm = "";
		StringBuffer buffer = new StringBuffer();
		switch(mark){
			case 1:
				result = start + end;
				rm = "+";
				break;
			case 2:
				result = start - end;
				rm = "-";
				break;
			case 3:
				rm = "*";
				result = start * end;
				break;
			default :
				while(end == 0){
					end = random.nextInt(10);
				}
				rm = "/";
				result = start / end;
				break;
		}
		buffer.append(start);
		buffer.append(rm);
		buffer.append(end);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("countString", buffer.toString());
		map.put("result", String.valueOf(result));
		return map;
	}
%>

<%
	 response.setContentType("image/jpeg");
     response.setHeader("Pragma", "No-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expire", 0);
	 
     int width = 80;
     int height = 30;
     
	 BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	 
	 Graphics g = image.getGraphics();
	 g.setColor(getRandColor(200, 250));
	 g.fillRect(0, 0, width, height);
	 
	 g.setColor(getRandColor(160, 200)); 
	 Random random  = new Random();
	 for(int i=0; i<155; i++){
		 int x = random.nextInt(width);
		 int y = random.nextInt(height);
		 int x1 = random.nextInt(12);
		 int y1 = random.nextInt(12);
	 	 g.drawLine(x, y, x+x1, y+y1);
	 }
	 
	 g.setFont(new Font("微软雅黑",Font.PLAIN,18));
	 int start = random.nextInt(100);
	 int end = random.nextInt(100);
	 g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110))); 
	 HashMap<String,String> map = getRandDomString();
 	 g.drawString(map.get("countString"), 20, 20);
	 g.dispose();
	 
	 OutputStream os = response.getOutputStream();
	 ImageIO.write(image, "JPEG", os);
	 session.setAttribute("countCode", map.get("result"));
	 
	 os.flush();
	 os.close();
	 os = null;
	 
	 response.flushBuffer();
	 out.clear();
	 out = pageContext.pushBody();
%>


