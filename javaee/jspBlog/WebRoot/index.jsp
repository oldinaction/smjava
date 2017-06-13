<%@page import="cn.smalle.dao.content.ContentDao"%>
<%@page import="cn.smalle.bean.Content"%>
<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sm" uri="/WEB-INF/tld/sm.tld"%>

<html>
<head>
	<meta name="themename" content="29002">
	<title>博客主页</title>
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<!-- 先导入样式后导入js -->
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<!-- 引入UMeditor相关的css文件 -->
	<link href="js/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">	
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<!-- 引入UMeditor相关的js文件 -->
	<script type="text/javascript" charset="utf-8" src="js/umeditor/umeditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/umeditor/lang/zh-cn/zh-cn.js"></script>
	
	<style type="text/css">
		.input{height: 32px;border: 1px solid #ccc;border-radius:1px;width:240px;}
		.content{font-size: 16px;}
		.layer_icon{ background:url(images/b_layer.png) no-repeat; display:inline-block;}
		.w460{width: 460px;}
		.fr{float:right}
		.fl{float:left}
		
		/*弹出通用样式*/
		.b_l{border-radius:4px; box-shadow:1px 2px 4px #6c6e72;-moz-box-shadow:1px 2px 4px #6c6e72;-webkit-box-shadow:1px 2px 4px #6c6e72;background:#fff;position:fixed;z-index:100}
		/*标题*/
		.bcom_ti{ background:#f4f4f4;border-bottom:1px solid #e4e4e4; border-radius:4px 4px 0 0; height:46px; line-height:44px;padding:0 15px;}
		.bide{ background-position:0 0;width:22px; height:22px;margin-top:11px;}
		.bide:hover{background-position:-23px 0;}
		/*单独样式（评论删除）*/
		.bcom_cent{padding:40px 0 30px}
		.bcomti{ font-size:18px;text-align:center; color:#000}
		.bcoma{margin:auto;width:180px;}
		.bcoma a{ width:74px; height:30px; line-height:25px; border-radius:4px; margin:30px 6px 0; color:#fff; text-align:center; display:inline-block}
		.bc_abut1{ background:#5580fb}
		.bc_abut1:hover{background:#4e79f1;}
		.bc_abut2{ background:#cacaca}
		.bc_abut2:hover{background:#c2c0c0;}
		.tmui-overlay{width:100%;height:100%;background-color:#000;position:absolute;top:0;left:0;z-index:99;filter:alpha(opacity=58);-moz-opacity:0.58;-khtml-opacity:0.58;opacity:0.58;}
		
		/*回顶部和底部的样式*/
		#gotop{display:none;width:34px;height:15px;position:fixed;bottom:80px;right:200px;background:url("images/bbs_icon.png") 0px -89px no-repeat;}
		#gobottom{display:none;width:34px;height:15px;position:fixed;top:80px;right:200px;background:url("images/bbs_icon.png") 0px -89px no-repeat;}
	</style>
</head>
<body>
<%
	List<Content> contents = ContentDao.findContents(0, 10, "DESC");
	pageContext.setAttribute("contents", contents);
%>

<div class="h95"></div>
<div class="box wid700  "> 
    <div class="selfinfo">
       	<div class="logo">
               <a href="/"><img src="images/proxy.jpg" height="80"><i></i></a>
        </div>
       	<h1><a href="/">smalle</a></h1>
        <div class="text"></div>
    </div>
    <div class="sch">
        <form action="/search" method="get">
            <input value="搜索" type="text" name="q" onfocus="if(this.value=='搜索'){this.value='';}" onblur="if(this.value==''){this.value='搜索';}" class="txt">
        </form>
    </div>
    <ul class="sidelist">
		<li><a href="javascript:void(0);" rel="nofollow">私信</a></li>
		<li><a href="javascript:void(0);">归档</a></li>
		<li><a href="javascript:void(0);">RSS</a></li>
    </ul>
</div>
<div id="message" class="postwrapper box wid700  ">
     <div class="mes_nr">
     	 <input type="text" id="title"  class="input" autofocus="autofocus" placeholder="请输入标题" maxlength="100">
	     <br>
	     <br>	     
	     <textarea id="myEditor" class="input text_area" style="height:300px"></textarea>
	     <br>
     	 <input type="text" id="tag" class="input" placeholder="请输入标签" maxlength="50">
	     <div class="fabiao"><input type="button" id="tm_sendcomment" class="submit" value="发表评论"><span>还可以输入<label id="count">140</label>字</span></div>
	     <div class="clearfix"></div>
	</div>
</div>

<div class="postwrapper box wid700" id="contentbox">
<c:forEach var="content" items="${contents}">
	<div class="block photo">
		<div class="side">
			<div class="day">
				<a href="javascript:void(0);">${sm:dateFormat(content.createtime, "dd")}</a>
			</div>
			<div class="month">
				<a href="javascript:void(0);">${sm:dateFormat(content.createtime, "MM")}</a>
			</div>
		</div>
		<div class="main">
			<div class="content">
				${content.content}<!-- 注意是content.content 而不是content.getContent() -->
			</div>
			<div class="link">
				<a href="javascript:void(0);">
					评论(1) ${sm:dateFormat(content.createtime, "yyyy/MM/dd HH时mm分ss秒")}
				</a>
				<a href="javascript:viod(0);" data-opid="${content.id}" onclick="delete_content(this)">删除</a>
			</div>
			
			<div class="bcmt">
				<div class="s-fc0 ztag" style="line-height: 30px; display: none;">由于该用户的权限设置，你暂时无法进行评论...</div>
				<div class="bcmtadd" style="display:none">
					<div class="bcmtipt s-bd0 s-bg0">
						<div class="bcmtiptc">
							<div contenteditable="true" class="editdiv s-fc0 ztag f-trans"
								maxlength="200" style="height: 130px;"></div>
						</div>
					</div>
					<div class="bcmtbtn">
						<span class="ztag"
							style="color:red;display:none;margin-right:10px;">提示</span>
						<button class="s-bd1 s-fc1 s-bg1 ztag">发布</button>
						<div class="txt s-fc0"></div>
					</div>
				</div>
				<div class="bcmtlst">
					<ul class="clearfix ztag" id="commentbox"></ul>
				</div>
				<div class="bcmtmore s-bd2 ztag" style="display: none;">
					<div class="bcmtlsta">
						<span class="s-fc4">正在载入中...</span>
					</div>
				</div>
				<div class="bcmtmore s-bd2" style="display: none;">
					<div class="bcmtlsta">
						<a href="#" class="s-fc2 ztag">查看更多</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</div>


<!--回顶部或底部 -->
<i id="gobottom"></i>
<i id="gotop"></i>

<script type="text/javascript">
	var editor = UM.getEditor("myEditor"); //div和textarea都可以绑定

	var timer = null;
	//发表评论
	$("#tm_sendcomment").click(function(){
		var title = $("#title").val();
		var content = editor.getContentTxt();
		if(isEmpty(title)) {
			alert("标题不能为空");
			$("#title").focus();
			return;
		}
		if(isEmpty(content)) {
			alert("内容不能为空");
			UM.getEditor("myEditor").focus(); //？？？获取不到焦点
			return;
		}
		clearTimeout(timer);
		content = editor.getContent();
		timer = setTimeout(function(){
			$.ajax({
				type:"post",
				url:"service/contentDao.jsp",
				data:{title : title, content : content, tag : $("#tag").val()},
				beforeSend:function(){},
				success:function(data){
					if(data.trim() != "success") {
						$("#title").focus();
						alert("标题和内容不能为空");
					} else {
						var day = new Date().format("dd");
						var month = new Date().format("MM");	
						var html = "<div class='block photo'>"+
						"	<div class='side'>"+
						"		<div class='day'>"+
						"			<a href='javascript:void(0);'>"+day+"</a>"+
						"		</div>"+
						"		<div class='month'>"+
						"			<a href='javascript:void(0);'>"+month+"</a>"+
						"		</div>"+
						"	</div>"+
						"	<div class='main'>"+
						"		<div id='comment'></div>"+
						"		<div class='content'>"+
									content +
						"		</div>"+
						"		<div class='link'>"+
						"			<a href='javascript:void(0);'>"+
						"				评论(1) "+ new Date().format("yyyy-MM-dd HH:mm:ss") +
						"			</a>"+
						"			<a href='javascript:viod(0);' data-opid='1' onclick='delete_content(this)'>删除</a>"+
						"		</div>"+
						"		<div class='bcmt'>"+
						"			<div class='s-fc0 ztag' style='line-height: 30px; display: none;'>由于该用户的权限设置，你暂时无法进行评论...</div>"+
						"			<div class='bcmtadd' style='display:none'>"+
						"				<div class='bcmtipt s-bd0 s-bg0'>"+
						"					<div class='bcmtiptc'>"+
						"						<div contenteditable='true' class='editdiv s-fc0 ztag f-trans' maxlength='200' style='height: 130px;'></div>"+
						"					</div>"+
						"				</div>"+
						"				<div class='bcmtbtn'>"+
						"					<span class='ztag' style='color:red;display:none;margin-right:10px;'>提示</span>"+
						"					<button class='s-bd1 s-fc1 s-bg1 ztag'>发布</button>"+
						"					<div class='txt s-fc0'></div>"+
						"				</div>"+
						"			</div>"+
						"			<div class='bcmtlst'>"+
						"				<ul class='clearfix ztag' id='commentbox'></ul>"+
						"			</div>"+
						"			<div class='bcmtmore s-bd2 ztag' style='display: none;'>"+
						"				<div class='bcmtlsta'>"+
						"					<span class='s-fc4'>正在载入中...</span>"+
						"				</div>"+
						"			</div>"+
						"			<div class='bcmtmore s-bd2' style='display: none;'>"+
						"				<div class='bcmtlsta'>"+
						"					<a href='#' class='s-fc2 ztag'>查看更多</a>"+
						"				</div>"+
						"			</div>"+
						"		</div>"+
						"	</div>"+
						"</div>";
						$("#contentbox").prepend(html); //prepend加到前面 append加到后面
						$("#title").val("");
						UM.getEditor("myEditor").setContent("", false);//清空富文本编辑器
						$("#tag").val("");
					}
				}		
			});
		},200);
	});
	
	//删除评论
	function delete_content(obj) {
		var opid = $(obj).data("opid");
		sm_dialoag({"content":"您确定要删除此条内容吗！", callback:function(ok){
			if(ok) {
				$.ajax({
					type:"post",
					url:"service/contentDeleteDao.jsp",
					data:{"id":opid},
					beforeSend:function(){},
					success:function(data){
						if(data.trim() == "success") {
							//删除成功
							$(obj).parents(".photo").fadeOut("slow",function(){
								$(this).remove();
							});
						} else {
							//删除失败
							sm_dialoag({"title":"提示", "content":"删除失败！"});
						}
					}	
				});
			}
		}});	
	}
	
	//
	
</script> 

<script type="text/javascript">
	//返回顶部和底部功能
	$(function(){
		$(window).scroll(function(){
			var top = $(window).scrollTop();
			if(top > 400){
				$("#gotop").stop(true,true).fadeIn(300);
				$("#gobottom").stop(true,true).fadeOut(300);
			} else {
				$("#gotop").stop(true,true).fadeOut(300);
				$("#gobottom").stop(true,true).fadeIn(300);
			}
		});
	});
	
	$("#gotop").click(function(){
		$("html,body").stop(true,true).animate({scrollTop : 0},500);
	});
	//可以实现自动阅读功能
	$("#gobottom").on("click",function(){
		var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
		$("body").stop(true,true).animate({"scrollTop" : scrollHeight},35000,function(){
			$("#gobottom").hide();
		});
		return true;
	});
</script>


</body>
</html>

