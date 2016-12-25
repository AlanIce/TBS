Ext.onReady(function() {
	var pageSize = 20;
	var dataStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'Project'},
			{ name: 'Teacher'},
			{ name: 'TestDate'},
			{ name: 'Score'}
		],
		proxy: {
			type: 'ajax',
			url: encodeURI('TestAction_getTestrecordList?Username=' + username + '&CourseID='),
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		},
		autoLoad: true,
		pageSize: pageSize
	});

	var panel = Ext.create('Ext.panel.Panel', {
		renderTo: 'TestRecordTable',
		title: '考试记录',
		layout : 'fit',
		autoScroll : true,
		listeners : {
   	        'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
		
	var grid = Ext.create('Ext.grid.Panel', {
		store: dataStore,
		height: 600,
		pageSize: pageSize,
		columns: [
			{ text: '序号', xtype: 'rownumberer',width: 40, sortable: false},
			{ text: '科目', dataIndex: 'Project', align: 'center', width: 180},
			{ text: '授课老师', dataIndex: 'Teacher', align: 'center', width: 120},
			{ text: '考试时间', dataIndex: 'TestDate', align: 'center', width: 175},
			{ text: '得分', dataIndex: 'Score', align: 'center', width: 80},
		],
		bbar: Ext.create('Ext.PagingToolbar',{
			pageSize: 2,
			displayInfo: true,
			emptyMsg: "没有数据要显示！",
			displayMsg: "当前为第{0}--{1}条，共{2}条数据", //参数是固定的，分别是起始和结束记录数、总记录数
			store: dataStore
		})
	});
	
	panel.add(grid);
})