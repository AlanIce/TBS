Ext.onReady(function() {
	var pageSize = 20;
	
	// TestRecord Panel
	var testrecordStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'CourseName'},
			{ name: 'TestDate'},
			{ name: 'Score'}
		],
		proxy: {
			type: 'ajax',
			url: encodeURI('TestAction_getTestrecordList'),
			extraParams: {username: username},
			getMethod: function() { return 'POST'; },
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		},
		autoLoad: true,
		pageSize: pageSize
	});

	var testrecordPanel = Ext.create('Ext.panel.Panel', {
		renderTo: 'TestRecordTable',
		title: '考试记录',
		layout : 'fit',
		autoScroll : true,
		listeners : {
   	        'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
	
	var testrecordTbar = new Ext.Toolbar({
		defaults: { scale: 'medium' }
	});
	
	var radiogroup = {
		xtype: 'radiogroup',
		itemId: 'score_type',
		width: 270,
		items: [{
			xtype: 'radio',
			boxLabel: '详细记录',
			name: 'Type',
			inputValue: 'radio1',
			checked: true,
		},{
			xtype: 'radio',
			boxLabel: '最终成绩',
			name: 'Type',
			inputValue: 'radio2',
		}],
		listeners: {
			'change': function(obj) {
				var type = obj.lastValue['Type'];
				if(type == 'radio1') testrecordStore.getProxy().url = encodeURI('TestAction_getTestrecordList');
				else if(type == 'radio2') testrecordStore.getProxy().url = encodeURI('TestAction_getFinalTestrecordList');
				testrecordBbar.moveFirst();
			}
		}
	};
	
	var testrecordBbar = Ext.create('Ext.PagingToolbar',{
		pageSize: pageSize,
		displayInfo: true,
		emptyMsg: "没有数据要显示！",
		displayMsg: "当前为第{0}--{1}条，共{2}条数据", //参数是固定的，分别是起始和结束记录数、总记录数
		store: testrecordStore
	});
	
	testrecordTbar.add(radiogroup);
	var testrecordGrid = Ext.create('Ext.grid.Panel', {
		store: testrecordStore,
		width: 400,
		height: 600,
		pageSize: pageSize,
		viewConfig: { getRowClass: function(record, rowIndex, rowParams, store) {
			if (record.get('Score') < 60)  return 'x-grid-record-red';
			}
		},
		columns: [
			{ text: '序号', xtype: 'rownumberer',width: 40, sortable: false},
			{ text: '科目', dataIndex: 'CourseName', align: 'center', width: 180},
			{ text: '考试时间', dataIndex: 'TestDate', align: 'center', width: 160},
			{ text: '得分', dataIndex: 'Score', align: 'center', width: 80},
		],
		tbar: testrecordTbar,
		bbar: testrecordBbar
	});	
	testrecordPanel.add(testrecordGrid);
	
	// UserCourse Panel
	var type = 'selected';		//是否选课
	var usercourseStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'CourseID'},
			{ name: 'CourseName'},
			{ name: 'Teacher'}
		],
		proxy: {
			type: 'ajax',
			url: encodeURI('SelectAction_getMyCourseList'),
			extraParams: { type: type},
			getMethod: function() { return 'POST'; },
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		},
		autoLoad: true,
		pageSize: pageSize
	});

	var usercoursePanel = Ext.create('Ext.panel.Panel', {
		renderTo: 'UserCourseTable',
		title: '考试记录',
		layout : 'fit',
		autoScroll : true,
		listeners : {
   	        'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
	
	var radiogroup2 = {
			xtype: 'radiogroup',
			itemId: 'select_type',
			width: 270,
			items: [{
				xtype: 'radio',
				boxLabel: '已选择',
				name: 'Type2',
				inputValue: 'selected',
				checked: true,
			},{
				xtype: 'radio',
				boxLabel: '未选择',
				name: 'Type2',
				inputValue: 'unselected',
			},{
				xtype: 'radio',
				boxLabel: '全部',
				name: 'Type2',
				inputValue: 'all',
			}],
			listeners: {
				'change': function(obj) {
					var type = obj.lastValue['Type2'];
					usercourseStore.getProxy().extraParams = { type: type };
					usercourseBbar.moveFirst();
				}
			}
		};
	
	var usercourseTbar = new Ext.Toolbar({
		defaults: { scale: 'medium' }
	});
	
	usercourseTbar.add(radiogroup2);
	var usercourseBbar = Ext.create('Ext.PagingToolbar',{
		pageSize: pageSize,
		displayInfo: true,
		emptyMsg: "没有数据要显示！",
		displayMsg: "当前为第{0}--{1}条，共{2}条数据", //参数是固定的，分别是起始和结束记录数、总记录数
		store: usercourseStore
	});
	
	var usercourseGrid = Ext.create('Ext.grid.Panel', {
		store: usercourseStore,
		height: 600,
		pageSize: pageSize,
		viewConfig: { getRowClass: function(record, rowIndex, rowParams, store) {
			if (record.get('Score') < 60)  return 'x-grid-record-red';
			}
		},
		columns: [
			{ text: '序号', xtype: 'rownumberer',width: 40, sortable: false},
			{ text: '课程号', dataIndex: 'CourseID', align: 'center', width: 80},
			{ text: '科目', dataIndex: 'CourseName', align: 'center', width: 180},
			{ text: '授课老师', dataIndex: 'Teacher', align: 'center', width: 120}
		],
		tbar: usercourseTbar,
		bbar: usercourseBbar
	});	
	usercoursePanel.add(usercourseGrid);
})