<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>详情页</title>
<link href="/servletBlog/css/sg.css" rel="stylesheet" type="text/css" />
<link href="/servletBlog/css/teacher.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/servletBlog/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/servletBlog/js/util.js"></script>
</head>

<body>
<!-- 引入头部页面 -->
<%@include file="header.jsp" %>
<input type="hidden" id="logId" value="${logMap.id}"/>

<div id="container">
     <div class="detail_con">
          <div class="det_bt">${logMap.title}</div>
          <div class="det_sm">
               <div class="fl"><span>发表于：${logMap.createtime}  作者：${userSession.nickname}</span></div>
               <div class="fr"><a href="#"><i class="discuss teac_icon"></i><span>评论</span></a><a href="#"><i class="forward teac_icon"></i><span>转发</span></a><a href="#"><i class="praise teac_icon"></i><span>点赞</span></a></div>
               <div class="clearfix"></div>
          </div>
          <div class="det_nr">${logMap.content}</div>
          <div id="message">
                <div class="mes_nr">
                	 <div style="height: 25px;color:pink"> <label id="errorMsg"></label>	</div>
                     <textarea name="" id="content" cols="" rows="" class="text_area" placeholder="在这里输入评论！"></textarea>
                     <div class="tongbu"><span>同步到：</span><a href="#" class="teac_icon xinlang"></a><a href="#" class="teac_icon tengxun"></a><a href="#" class="teac_icon renren"></a></div>
                     <div class="fabiao"><input name="" id="sendComment" type="button" class="submit" value="发表评论" /><span>还可以输入140字</span></div>
                     <div class="clearfix"></div>
                </div>
                <div class="mes_list">
                     <div class="sort"><a href="#" class="current">按时间</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">按热门</a></div>
                     
                     <div id="commentbox">
                     	 <!-- 开始===用commentMap来遍历commentMaps -->
	                     <c:forEach items="${commentMaps}" var="commentMap"> <!-- 下面的commentMap就是此处定义的 -->
	                     	<div class="disc_list">
	                        	<div class="pic"><a href="#"><img src="../images/${commentMap.headerPic}" width="43" height="43" /></a></div>
								<div class="liuyan1">
	                               <dl>
	                                   <dt>${commentMap.nickname}</dt>
	                                   <dd>${commentMap.content}</dd>                                   
	                               </dl>
	                               <div class="pubdata">
	                                    <span>发表于：${commentMap.createtime} |</span>
	                                    <a href="#" class="fl">只看该作者</a>
	                                    <a href="#" class="fr"><span class="nolike teac_icon"></span><p>不喜欢(21)</p></a>
	                                    <a href="#" class="fr"><span class="onlike teac_icon"></span><p>喜欢(123)</p></a>
	                               </div>
								</div>
								<div class="clearfix"></div>
	                    	 </div>
	                     </c:forEach>
          			 	<!-- 结束===用commentMap来遍历commentMaps -->
                     </div>
                     				
                     <div class="pagesize">
                          <ul>
                              <li><span>跳转到：</span><input name="" type="text" class="text_box" /><input name="" type="button" value="GO" class="text_btn" /></li>
                              <li><a href="#">下一页</a></li>
                              <li><a href="#">上一页</a></li>
                          </ul>                              
                     </div>
                     <div class="clearfix"></div>
                </div>
           </div>
     </div>
</div>
<div id="footer"><p>Copyright © 2013-2014 www.mooncollege.cn All Rights Reserved版权所有：湖南潭州教育咨询有限公司 备案号：备13016338号</p></div>

<script type="text/javascript">
	
	$(function(){	
		var timer = null;
		$("#sendComment").click(function(){
			var content = $("#content").val();
			var logId = $("#logId").val();
			
			if(isEmpty(content)) {
				$("#content").focus();
				$("#errorMsg").show().text("请输入评论文本！").fadeOut(3000);
				return;
			}
			
			if(content.length > 500) {
				$("#errorMsg").show().text("输入的文本超过了500位！").fadeOut(3000);
				return;
			}
			
			clearTimeout(timer);
			timer = setTimeout(function(){ //200毫秒才能提交一次
				$.ajax({
					url : "/servletBlog/page/saveComment",
					type : "post",
					data : {
						"content" : content,
						"logId" : logId
					},
					beforeSend : function(){},
					success : function(data){
						if(data == "success") {
							$("#content").val("");//清空文本框
							var html ="<div class='disc_list'>"+
							"		                          <div class='pic'><a href='#'><img src='"+$("#headerpic").attr("src")+"' width='43' height='43'></a></div>"+
							"		                          <div class='liuyan1'>"+
							"		                               <dl>"+
							"		                                   <dt>"+$("#username").text()+"</dt>"+
							"		                                   <dd>"+content+"</dd>                                   "+
							"		                               </dl>"+
							"		                               <div class='pubdata'>"+
							"		                                    <span>发表于："+new Date().format("yyyy-MM-dd HH:mm:ss")+" |</span>"+
							"		                                    <a href='#' class='fl'>只看该作者</a>"+
							"		                                    <a href='#' class='fr'><span class='nolike teac_icon'></span><p>不喜欢(21)</p></a>"+
							"		                                    <a href='#' class='fr'><span class='onlike teac_icon'></span><p>喜欢(123)</p></a>"+
							"		                               </div>"+
							"		                          </div>"+
							"		                          <div class='clearfix'></div>"+
							"		                     </div>";
		                    $("#commentbox").prepend(html);
						} else {
							alert("保存失败!");
						}
	 				}
				});
			},200);	
		});
	});
	

</script>




</body>
</html>