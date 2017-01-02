<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<title>成绩分布饼图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<link href="Extjs/resources/css/ext-all-neptune.css" rel="stylesheet" />
<script src="Extjs/ext-all-debug.js"></script>
<script src="Extjs/locale/ext-lang-zh_CN.js""></script>
<script type="text/javascript">
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null;
	}
	function init() {
		var courseID = getQueryString('courseID');
		var dataStore = Ext.create('Ext.data.Store', {
			fields: [
				{ name: 'Section' },
				{ name: 'Num' }
			],
			proxy: {
				type: 'ajax',
				url: encodeURI('TestAction_getPieChart'),
				extraParams: { courseID: courseID },
				getMethod: function() { return 'POST'; },
				reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
					type: 'json', 			//返回数据类型为json格式
					root: 'data',  			//数据
					totalProperty: 'total' 	//数据总条数
				}
			},
			autoLoad: true
		});
		
		var donut = false,
		chart = Ext.create('Ext.chart.Chart', {
			xtype: 'chart',
			animate: true,
			store: dataStore,
			shadow: true,
			legend: { position: 'right' },
			insetPadding: 60,
			theme: 'Base:gradients',
			series: [{
				type: 'pie',
				field: 'Num',
				showInLegend: true,
				donut: false,
				tips: {
					trackMouse: true,
					width: 140,
					height: 28,
					renderer: function(storeItem, item) {
						var total = 0;
						dataStore.each(function(rec) {
							total += rec.get('Num');
						});
						this.setTitle(Math.round(storeItem.get('Num') / total * 100) + '%\t总共' + storeItem.get('Num') + "人");
					}
				},
				highlight: {
					segment: {
						margin: 20
					}
				},
				label: {
					field: 'Section',
					display: 'rotate',
					contrast: true,
					font: '18px Arial'
				}
			}]
		});


		var panel1 = Ext.create('widget.panel', {
			width: 800,
			height: 600,
			title: '成绩分布饼状图',
			renderTo: Ext.getBody(),
			layout: 'fit',
			tbar: [{
				text: '保存表格',
				handler: function() {
						chart.save({
							type: 'image/png'
						});
					}
			}, {
				enableToggle: true,
				pressed: false,
				text: '空心',
				toggleHandler: function(btn, pressed) {
					chart.series.first().donut = pressed ? 35 : false;
					chart.refresh();
				}
			}],
			items: chart
		});
	}
	Ext.onReady(init);
</script>
</head>


</html>