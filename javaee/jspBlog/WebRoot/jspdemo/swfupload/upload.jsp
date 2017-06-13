<%@page import="org.apache.struts2.json.JSONUtil"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	//获取文件的上传的具体目录，结果为D:\Java\tomcat6\webapps\jspBlog\
	String realPath = request.getRealPath("/");
	//定义上传的目录
	/*  后面加上/jspdemo/upload和jspdemo/upload都可以识别，即如果为
	 *	D:\Java\tomcat6\webapps\jspBlog\/jspdemo/upload
	 *	D:\Java\tomcat6\webapps\jspBlog\jspdemo/upload
	 */
	String dirPath = realPath + "/jspdemo/upload";
	File dirFile = new File(dirPath);
	//自动创建上传的目录
	if(!dirFile.exists()) dirFile.mkdirs();
	//创建一个词条的文件工厂
 	FileItemFactory factory = new DiskFileItemFactory();  
	//创建一个文件上传的处理实例
  	ServletFileUpload upload = new ServletFileUpload(factory);  
  	List<FileItem> items = null;
	String fileName = null;
	HashMap<String,Object> map = new HashMap<String,Object>();
 	try{  
      items = upload.parseRequest(request); //文件先上传到服务器的temp目录下(然后将文件复制到指定目录)   
	  if(null != items){
          Iterator iterator = items.iterator();  
          while(iterator.hasNext()){  
              FileItem item = (FileItem)iterator.next();  
              if(item.isFormField()){  
                  continue;  
              }else{  
            	//UUID 随机字符文件名，防止中文文件名乱码
                fileName = UUID.randomUUID().toString() + getExt(item.getName()); 
				//上传文件的目录，并将文件复制到指定目录
                File savedFile = new File(dirPath,fileName);  
                item.write(savedFile);
                 
                map.put("name", item.getName()); //文件的真实名称
                map.put("size", item.getSize()); //文件的真实大小
                map.put("sizeString", countFileSize(item.getSize())); //获取文件转换以后的大写
                map.put("url", "upload/" + fileName); //获取文件的具体服务器的目录
              }  
          }  
	  }  
	}catch(Exception e){  
	   e.printStackTrace();
	} 
 	out.print(JSONUtil.serialize(map));
%>

<%!
public static String countFileSize(long fileSize) {
	String fileSizeString = "";
	try {
		DecimalFormat df = new DecimalFormat("#.00");
		long fileS = fileSize;
		if (fileS == 0) {
			fileSizeString = "0KB";
		} else if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df
					.format(((double) fileS / 1024 / 1024) - 0.01)
					+ "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
					+ "G";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return fileSizeString;
}


/**
 * 获取一个文件的后缀(带有点)
 * 
 * @param fileName
 *            文件名
 * @return 返回文件的后缀
 */
public static String getExt(String fileName) {
	int pos = fileName.lastIndexOf(".");
	if (pos == -1)
		return "";
	return fileName.substring(pos, fileName.length());
}

/**
 * 获取一个文件的后缀(不带有点)
 * 
 * @param fileName
 *            文件名
 * @return 返回文件的后缀
 */
public static String getExtNoPoint(String fileName) {
	if (fileName.lastIndexOf(".") == -1)
		return "";
	int pos = fileName.lastIndexOf(".") + 1;
	return fileName.substring(pos, fileName.length());
}
%>
