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
  
   <div id="chartContainer">统计报表将在这里加载！</div> 
   
   
<!-- 引入fusioncharts的核心js文件 -->   
<script type="text/javascript" src="../../js/chart/fusioncharts.js"></script>
<!-- fusioncharts.theme.zune.js只支持一部分常用的报表统计比如，饼图，曲线图，柱形图。即下面的所有
column2d column3d line area2d bar2d pie2d pie3d doughnut2d doughnut3d pareto2d pareto3d
比如其他的：温度计，地图，等等就在必须引入其他的js文件-->
<script type="text/javascript" src="../../js/chart/themes/fusioncharts.theme.zune.js"></script>
<script type="text/javascript">
	FusionCharts.ready(function(){
		var revenueChart = new FusionCharts({
			 type: "column2d", //图像的主题:饼图(pie2d)，曲线图(line)，柱形图(column2d)
			 width: "800", //图像的宽高,可以修改
			 height: "500",
			 dataFormat: "json", //数据格式xml、json
			 dataSource:{
				    "chart": {
				        "caption": "标题",
				        "subcaption": "副标题",
				        "xaxisname": "x轴说明",
				        "yaxisname": "y轴说明",
				        "plotgradientcolor": "",
				        "bgcolor": "FFFFFF",
				        "showalternatehgridcolor": "0",
				        "showplotborder": "0",
				        "divlinecolor": "#CCCCCC",
				        "showvalues": "1",
				        "showcanvasborder": "0",
				        "canvasbordercolor": "#CCCCCC",
				        "canvasborderthickness": "1",
				        "captionpadding": "30",
				        "linethickness": "3",
				        "yaxisvaluespadding": "15",
				        "showshadow": "0",
				        "labelsepchar": ": ",
				        "basefontcolor": "000000",
				        "labeldisplay": "AUTO",
				        "numberscalevalue": "1000,1000,1000",
				        "numberscaleunit": "K,M,B",
				        "animation": "0",
				        "palettecolors": "e44a00",
				        "showborder": "0"
				    },
				    "data": [
				        {
				            "label": "一月",
				            "value": "350000000",
				            "alpha": "100",
				            "tooltext": "说明: {br}Europe{br}Africa{br}Asia{br}Americas"
				        },
				        {
				            "label": "二月",
				            "value": "3000000000",
				            "alpha": "90",
				            "tooltext": "可以使用\{\bbr\}换行"
				        },
				        {
				            "label": "三月",
				            "value": "2200000000",
				            "alpha": "80",
				            "tooltext": "Popular in: {br}Asia{br}Europe{br}Africa{br}Australia"
				        },
				        {
				            "label": "Tennis",
				            "value": "1000000000",
				            "alpha": "70",
				            "tooltext": "Popular in: {br}Europe{br}Americas{br}Asia"
				        },
				        {
				            "label": "Volleyball",
				            "value": "900000000",
				            "alpha": "55",
				            "tooltext": "Popular in: {br}Asia{br}Europe{br}Americas{br}Australia"
				        },
				        {
				            "label": "Table Tennis",
				            "value": "900000000",
				            "alpha": "55",
				            "tooltext": "Popular in: {br}Asia{br}Europe{br}Africa{br}Americas"
				        },
				        {
				            "label": "Baseball",
				            "value": "500000000",
				            "alpha": "40",
				            "tooltext": "Popular in: {br}US{br}Japan{br}Cuba{br}Dominican Republic"
				        },
				        {
				            "label": "Golf",
				            "value": "400000000",
				            "alpha": "30",
				            "tooltext": "Popular in: {br}US{br}Canada{br}Europe"
				        },
				        {
				            "label": "Basketball",
				            "value": "400000000",
				            "alpha": "30",
				            "tooltext": "Popular in: {br}US{br}Canada"
				        },
				        {
				            "label": "American Football",
				            "value": "390000000",
				            "alpha": "25",
				            "tooltext": "Popular in: {br}US"
				        }
				    ]
				}
		});
	
		revenueChart.render("chartContainer"); //把数据加载到chartContainer的div中
  	});
	
//xml格式的
/* 	FusionCharts.ready(function(){
		var revenueChart = new FusionCharts({
			 type: "column3d", //图像的主题:饼图(pie2d)，曲线图(line)，柱形图(column2d)
			 width: "800", //图像的宽高,可以修改
			 height: "500",
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
	
		revenueChart.render("chartContainer"); //把数据加载到chartContainer的div中
  	}); */ 
</script>  
    

  </body>
</html>
