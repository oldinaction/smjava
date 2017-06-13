<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
<title>SWFUpload文件上传</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/swfupload.js"></script>
<script type="text/javascript" src="js/swfupload.queue.js"></script>
<script type="text/javascript" src="js/fileprogress.js"></script>
<script type="text/javascript" src="js/handlers.js"></script>
<script type="text/javascript">
		var swfu;

		window.onload = function() {
			var settings = {
				flash_url : "images/swfupload.swf",
				//此处的参数upload_url最终会传给在js目录下的js文件中处理，所以此处最好选择绝对路径(相对路径../upload.jsp也可以)	
				upload_url: "/jspBlog/jspdemo/swfupload/upload.jsp",
				post_params: {"username" : "smalle"},
				file_size_limit : "100 MB",
				file_types : "*.*", //文件格式如 *.* *.jpg等
				file_types_description : "All Files",
				file_upload_limit : 10,  //配置上传个数
				file_queue_limit : 0, //队列限制，0表示不限制
				file_post_name : 'Filedata',
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},
				debug: true, //控制下方是否显示详情框

				// Button settings
				button_image_url: "images/TestImageNoText_65x29.png",
				button_width: "65",
				button_height: "29",
				button_placeholder_id: "spanButtonPlaceHolder",
				button_text: '<span class="theFont">浏览</span>',
				button_text_style: ".theFont { font-size: 16; }",
				button_text_left_padding: 12,
				button_text_top_padding: 3,
				
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete	
			};

			swfu = new SWFUpload(settings);
	     };
	</script>
</head>
<body>
<div id="header">
	<h1 id="logo"><a href="/">SWFUpload</a></h1>
	<div id="version">v2.2.0</div>
</div>

<div id="content"><!-- 此时可以不需要form表单 -->	
	<p>点击“浏览”按钮，选择您要上传的文档文件后，系统将自动上传并在完成后提示您。</p>
	<p>请勿上传包含中文文件名的文件！</p>
	<div class="fieldset flash" id="fsUploadProgress">
		<span class="legend">快速上传</span>
    </div>
	<div id="divStatus">0 个文件已上传</div>
	<div>
		<span id="spanButtonPlaceHolder"></span>
		<input id="btnCancel" type="button" value="取消所有上传" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 29px;" />
	</div>
</div>

</body>
</html>