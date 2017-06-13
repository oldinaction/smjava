<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>文件上传界面</title>

  </head>
  
  <body>
  	<!-- 
  		描述:文件上传
  		1:引入common-fileupload-1.2.jar
  		2:建立upload.jsp文件作为上传服务器的处理类
  		3:建立uploadTest.jsp页面作为上传页面
  		form表单一定要指明:enctype="multipart/form-data"
  	 -->
  	 
  	 <h1>文件上传</h1>
  	 <form action="upload.jsp" method="post" enctype="multipart/form-data">
  	 	<input type="file" name="file"/><!-- 此处必须加上name="file"值可以随便 -->
  	 	<input type="submit" value="确定上传" />
  	 </form>
  
  </body>
</html>
