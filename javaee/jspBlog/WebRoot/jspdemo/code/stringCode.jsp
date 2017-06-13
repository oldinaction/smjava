<%@ page language="java" import="java.awt.*, 
java.awt.image.*,java.util.*,javax.imageio.*,java.io.OutputStream" pageEncoding="UTF-8"%> 

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
%>

<%
	//什么时候使用验证码:用户注册，登陆，发帖（如果要确保数据真的安全有效那么必须进行js验证和服务端验证，只要是牵扯到金钱的时候就一定要服务器验证）
	/* 
	验证码原理：
		利用java技术生成一张图片
		往图片画入干扰线、文字、数字、字母表达式等等
		利用流将图片写到浏览器
		并把结果放在session中
		当用户在前端进行输入的时候就会和服务端的session存入进行比较 
	*/

	 response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
     response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expire", 0); //防止浏览器缓存 
     
     //验证码图片的高度和宽度
     int width = 80;
     int height = 30;
	 //新建画布
	 BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	 //获取画笔
	 Graphics g = image.getGraphics();
	 //设置画笔颜色
	 g.setColor(getRandColor(200, 250));
	 //给整个图片渲染,给画布添加背景颜色
	 g.fillRect(0, 0, width, height); 
	 
	 //绘制干扰线
	 g.setColor(getRandColor(160,200));
	 Random random  = new Random();
	 for(int i=0; i<155; i++){
		 int x = random.nextInt(width);
		 int y = random.nextInt(height);
		 int x1 = random.nextInt(12);
		 int y1 = random.nextInt(12);
	 	 g.drawLine(x, y, x+x1, y+y1);
	 }
	 //绘制数字字母(数字0和字母O容易混所以去掉了数字0，也可以加入文字)
	 g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	 String[] str = {"1","2","3","4","5","6","7","8","9","Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"}; 
	 String rand = "";
	 for(int i=0; i<4; i++){
		 int index = random.nextInt(28);
		 g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110))); 
	 	 rand += str[index];
	 	 g.drawString(str[index], 13*i+14, 22);
	 }
	 //关闭画笔，释放资源
	 g.dispose();
	 
	 //利用输出流输出到浏览器
	 	//OutputStream os = new FileOutputStream(); //往磁盘中输出
	 OutputStream os = response.getOutputStream(); //往浏览器输出的功能
	 //输出图片
	 ImageIO.write(image, "JPEG", os);
	 //将随机放入session中
	 session.setAttribute("stringCode", rand);
	 
	 //刷新并关闭流
	 os.flush();
	 os.close();
	 os = null;
	 
	 //去掉下面三行可以运行，但是报错java.lang.IllegalStateException: getOutputStream() has already been called for this response
	 response.flushBuffer();
	 out.clear();
	 out = pageContext.pushBody();
%>