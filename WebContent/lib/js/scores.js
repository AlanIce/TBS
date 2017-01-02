Ext.onReady(function() {
	var courseID;
	var pageSize = 25;
	var dataStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'Username' },
			{ name: 'Name' },
			{ name: 'TestDate' },
			{ name: 'Score' }
		],
		proxy: {
			type: 'ajax',
			url: encodeURI('TestAction_getScoresList'),
			getMethod: function() { return 'POST'; },
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		},
		pageSize: pageSize
	});

	var panel = Ext.create('Ext.panel.Panel', {
		renderTo: 'ScoresTable',
		title: '成绩管理',
		layout : 'fit',
		autoScroll : true,
		listeners : {
			'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
	
	var courseStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'CourseName' },
			{ name: 'CourseID' }
		],
		method : 'POST',
		proxy: {
			type: 'ajax',
			url: encodeURI('SelectAction_getCourseList'),
			getMethod: function() { return 'POST'; },
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		}
	});
	
	var comboBox = new Ext.form.ComboBox({
		store: courseStore,
		displayField: 'CourseName',
		valueField: "CourseID",
		editable : false,
		selectOnFocus:true,
		autoSelect: true,
		listeners: {   
			render : function(cb) {
				cb.getStore().on("load", function(s, r, o) {   
					cb.setValue(r[0].get('CourseID'));
				});   
			},
			'change': function(combo, records, eOpts) {
				courseID = combo.getValue();
				dataStore.on('beforeload', function(store, options) {
					Ext.apply(dataStore.proxy.extraParams, { courseID: courseID }); 
				});
				bbar.moveFirst();
			}
		}
	});
	
	var radiogroup = {
		xtype: 'radiogroup',
		itemId: 'score_type',
		width: 270,
		items: [{
			xtype: 'radio',
			boxLabel: '已考试',
			name: 'Type',
			inputValue: 'radio1',
			checked: true,
		},{
			xtype: 'radio',
			boxLabel: '未考试',
			name: 'Type',
			inputValue: 'radio2',
		}],
		listeners: {
			'change': function(obj) {
				var type = obj.lastValue['Type'];
				if(type == 'radio1') dataStore.getProxy().url = encodeURI('TestAction_getScoresList');
				else if(type == 'radio2') dataStore.getProxy().url = encodeURI('TestAction_getUnUserList');
				bbar.moveFirst();
			}
		}
	};
	
	var bbar = Ext.create('Ext.PagingToolbar',{
		pageSize: pageSize,
		displayInfo: true,
		emptyMsg: "没有数据要显示！",
		displayMsg: "当前为第{0}--{1}条，共{2}条数据", //参数是固定的，分别是起始和结束记录数、总记录数
		store: dataStore
	});
	
	var tbar = new Ext.Toolbar({
		defaults: { scale: 'medium' }
	});
		
	tbar.add('课程选择：')
	tbar.add(comboBox);
	tbar.add(radiogroup);
	tbar.add({
		text: '分数分布',
		handler: function() { window.open('pie?courseID='+courseID); }
	});
	
	var grid = Ext.create('Ext.grid.Panel', {
		store: dataStore,
		height: 600,
		pageSize: pageSize,
		viewConfig: { getRowClass: function(record, rowIndex, rowParams, store) {
				if (record.get('Score') < 60)  return 'x-grid-record-red';
			}
		},
		columns: [
			{ text: '序号', xtype: 'rownumberer',width: 40, sortable: false},
			{ text: '学号', dataIndex: 'Username', align: 'center', width: 180},
			{ text: '姓名', dataIndex: 'Name', align: 'center', width: 120},
			{ text: '考试时间', dataIndex: 'TestDate', align: 'center', width: 175},
			{ text: '得分', dataIndex: 'Score', align: 'center', width: 80}
		],
		tbar: tbar,
		bbar: bbar
	});
	
	panel.add(grid);
	courseStore.load();
})