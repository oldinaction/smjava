/**
 * 实现一个弹出窗口（下面是窗体的样式）
	.b_l{border-radius:4px; box-shadow:1px 2px 4px #6c6e72;-moz-box-shadow:1px 2px 4px #6c6e72;-webkit-box-shadow:1px 2px 4px #6c6e72;background:#fff;position:fixed;z-index:100}
	
	.bcom_ti{ background:#f4f4f4;border-bottom:1px solid #e4e4e4; border-radius:4px 4px 0 0; height:46px; line-height:44px;padding:0 15px;}
	.bide{ background-position:0 0;width:22px; height:22px;margin-top:11px;}
	.bide:hover{background-position:-23px 0;}
	
	.bcom_cent{padding:40px 0 30px}
	.bcomti{ font-size:18px;text-align:center; color:#000}
	.bcoma{margin:auto;width:180px;}
	.bcoma a{ width:74px; height:30px; line-height:25px; border-radius:4px; margin:30px 6px 0; color:#fff; text-align:center; display:inline-block}
	.bc_abut1{ background:#5580fb}
	.bc_abut1:hover{background:#4e79f1;}
	.bc_abut2{ background:#cacaca}
	.bc_abut2:hover{background:#c2c0c0;}
	.tmui-overlay{width:100%;height:100%;background-color:#000;position:absolute;top:0;left:0;z-index:99;filter:alpha(opacity=58);-moz-opacity:0.58;-khtml-opacity:0.58;opacity:0.58;}
 * @param options
 */
function sm_dialoag(options){
		var defaults = {
		title:"提示",
		content:"请输入内容 ！",
		width:460,
		height:220,
		sureText:"确定",
		cancleText:"取消",
		showButton:true,
		callback:function(){}
	};
	var opts = $.extend({},defaults,options);
	$("body").append("<div class='b_l w460' id='dialogbox'>"+
	"	<div class='bcom_ti'>"+
	"		<a href='javascript:void(0);' class='bide layer_icon close fr'></a> <span>"+opts.title+"</span>"+
	"	</div>"+
	"	<div class='bcom_cent'>"+
	"		<p class='bcomti'>"+opts.content+"</p>"+
	"		<p class='bcoma'>"+
	"			<a href='javascript:void(0);' class='bc_abut1 sure'>"+opts.sureText+"</a>"+
	"			<a href='javascript:void(0);' class='bc_abut2 close'>"+opts.cancleText+"</a>"+
	"		</p>"+
	"	</div>"+
	"</div>").append("<div class='tmui-overlay' style='height:"+$(window).height()+"px'></div>");
	var $dialog = $("#dialogbox");
	if(!opts.showButton)$dialog.find(".bcoma").remove();
	$dialog.width(opts.width);
	$dialog.height(opts.height);
	tm_center_dialog($dialog);
	//关闭按钮绑定点击事件
	$dialog.find(".close").click(function(){
		$dialog.next().remove();//删除遮罩层
		$dialog.slideUp("slow",function(){
			$(this).remove();
		});	
		if(opts.callback)opts.callback(false);
	});
	$dialog.find(".sure").click(function(){
		$dialog.next().remove();//删除遮罩层
		$dialog.slideUp("slow",function(){
			$(this).remove();
		});	
		if(opts.callback)opts.callback(true);
	});
	//窗口resize
	$(window).resize(function(){
		tm_center_dialog($dialog);
	});
};

//层居中
function tm_center_dialog($dialog){
	var windowWidth = $(window).width();
	var windowHeight = getClientHeight();
	var dialogWidth = $dialog.width();
	var dialogHeight = $dialog.height();
	var left = (windowWidth-dialogWidth)/2;
	var top =  (windowHeight-dialogHeight)/2;
	$dialog.css({left:left,top:top});
};

//浏览器的可见高度
function getClientHeight() {
	var clientHeight = 0;
	if (document.body.clientHeight && document.documentElement.clientHeight) {
		clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	} else {
		clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	}
	return clientHeight;
};

//浏览器的可见宽度
function getClientWidth() {
	var clientWidth = 0;
	if (document.body.clientWidth && document.documentElement.clientWidth) {
		clientWidth = (document.body.clientWidth < document.documentElement.clientWidth) ? document.body.clientWidth: document.documentElement.clientWidth;
	} else {
		clientWidth = (document.body.clientWidth > document.documentElement.clientWidth) ? document.body.clientWidth: document.documentElement.clientWidth;
	}
	return clientWidth;
};