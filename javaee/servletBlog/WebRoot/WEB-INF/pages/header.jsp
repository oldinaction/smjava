<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="header">
     <div class="wth1100">
          <div class="teac_pic"><a href="#"><img id="headerpic" src="/servletBlog/images/${userSession.headerPic}" alt=""/></a></div>
          <div class="teac_nr">
               <div class="teac_bt" id="username"><span>${userSession.nickname}&nbsp;&nbsp;&nbsp;</div>
               <div class="teac_bt"> 
               		<a href="/servletBlog/logout" style="background:green;padding:10px 17px;color:#fff;">注销</a>
               		<a href="/servletBlog/index" style="background:green;padding:10px 17px;color:#fff;">返回</a>
               </div>
               <div class="teac_zi">${userSession.description}</div>
          </div>
          <div class="clearfix"></div>
     </div>     
</div>