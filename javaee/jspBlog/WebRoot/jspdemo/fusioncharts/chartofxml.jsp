<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>  
    <title>统计报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  选择报表的样式<select onchange="sm_change(this);">
  	<option value="column2d">column2d</option>
  	<option value="column3d">column3d</option>
  	<option value="line">line</option>
  	<option value="area2d">area2d</option>
  	<option value="bar2d">bar2d</option>
  	<option value="pie2d">pie2d</option>
  	<option value="pie3d">pie3d</option>
  	<option value="doughnut2d">doughnut2d</option>
  	<option value="doughnut3d">doughnut3d</option>
  	<option value="pareto2d">pareto2d</option>
  	<option value="pareto3d">pareto3d</option>
  </select>
   
  <div id="chartContainer">统计报表将在这里加载！</div> 
   
   
<!-- 引入fusioncharts的核心js文件 -->   
<script type="text/javascript" src="../../js/chart/fusioncharts.js"></script>
<!-- fusioncharts.theme.zune.js只支持一部分常用的报表统计比如，饼图，曲线图，柱形图。即下面的所有
column2d column3d line area2d bar2d pie2d pie3d doughnut2d doughnut3d pareto2d pareto3d
比如其他的：温度计，地图，等等就在必须引入其他的js文件-->
<script type="text/javascript" src="../../js/chart/themes/fusioncharts.theme.zune.js"></script>
<script type="text/javascript">
	sm_showchart_forXML({
		type:"column2d",
		width:900,
		height:460
	});
	
	function sm_showchart_forXML(option){
		FusionCharts.ready(function(){
			var revenueChart = new FusionCharts({
				 //type可以为http://docs.fusioncharts.com/tutorial-setup-list-of-charts.html页面中列举的
				 type: option.type, //图像的主题:饼图(pie2d)，曲线图(line)，柱形图(column2d)
				 width: option.width, //图像的宽高,可以修改
				 height: option.height,
				 dataFormat: "xml", //数据格式xml、json
				 dataSource:'<chart caption="标题" subcaption="副标题" xaxisname="x轴说明" yaxisname="y轴说明" plotgradientcolor="" bgcolor="FFFFFF" showalternatehgridcolor="0" showplotborder="0" divlinecolor="#CCCCCC" showvalues="1" showcanvasborder="0" canvasbordercolor="#CCCCCC" canvasborderthickness="1" captionpadding="30" linethickness="3" yaxisvaluespadding="15" showshadow="0" labelsepchar=": " basefontcolor="000000" labeldisplay="AUTO" numberscalevalue="1000,1000,1000" numberscaleunit="K,M,B" animation="0" palettecolors="e44a00" showborder="0">'+
						'<set label="一月" value="3500000000" alpha="100" tooltext="Popular in: {br}Europe{br}Africa{br}Asia{br}Americas" />'+
						'<set label="二月" value="3000000000" alpha="90" tooltext="Popular in: {br}India{br}UK{br}Pakistan{br}England{br}Australia" />'+
						'<set label="三月" value="2200000000" alpha="80" tooltext="Popular in: {br}Asia{br}Europe{br}Africa{br}Australia" />'+
						'<set label="Tennis" value="1000000000" alpha="70" tooltext="Popular in: {br}Europe{br}Americas{br}Asia" />'+
						'<set label="Volleyball" value="900000000" alpha="55" tooltext="Popular in: {br}Asia{br}Europe{br}Americas{br}Australia" />'+
						'<set label="Table Tennis" value="900000000" alpha="55" tooltext="Popular in: {br}Asia{br}Europe{br}Africa{br}Americas" />'+
						'<set label="Baseball" value="500000000" alpha="40" tooltext="Popular in: {br}US{br}Japan{br}Cuba{br}Dominican Republic" />'+
						'<set label="Golf" value="400000000" alpha="30" tooltext="Popular in: {br}US{br}Canada{br}Europe" />'+
						'<set label="Basketball" value="400000000" alpha="30" tooltext="Popular in: {br}US{br}Canada" />'+
						'<set label="American Football" value="390000000" alpha="25" tooltext="Popular in: {br}US" />'+
						'</chart>'
			});
			//把数据加载到chartContainer的div中
			revenueChart.render("chartContainer");
	  	});
	}
	
	function sm_change(obj) {
		sm_showchart_forXML({
			type:obj.value,
			width:900,
			height:460,
		});	
	}
</script>  
    

  </body>
</html>
