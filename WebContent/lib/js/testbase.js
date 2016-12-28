Ext.onReady(function() {
	var findStr = '';		// 搜索关键字，默认为空
	var selRecs = [];		// 选中的记录
	var IDList = [];		// 选中的记录的ID
	var courseID;			// 课程ID
	var forms;
	var pageSize = 25;
	var dataStore = Ext.create('Ext.data.Store', {
		fields: [
			{ name: 'ID' },
			{ name: 'Question' },
			{ name: 'OptionA' },
			{ name: 'OptionB' },
			{ name: 'OptionC' },
			{ name: 'OptionD' },
			{ name: 'Answer' }
		],
		proxy: {
			type: 'ajax',
			url: encodeURI('TestAction_getTestBaseList'),
			getMethod: function() { return 'POST'; },
			reader: {   				//这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
				type: 'json', 			//返回数据类型为json格式
				root: 'data',  			//数据
				totalProperty: 'total' 	//数据总条数
			}
		},
		pageSize: pageSize
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
	
	var getSel = function() {
		selRecs = [];  //清空数组
		IDList = [];
		selRecs = grid.getSelectionModel().getSelection();
		for (var i = 0; i < selRecs.length; i++)
			IDList.push(selRecs[i].data.ID);
	};
	
	var panel = Ext.create('Ext.panel.Panel', {
		renderTo: 'TestBaseTable',
		title: '题库管理',
		layout : 'fit',
		autoScroll : true,
		listeners : {
   	        'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
	
	var reloadStore = function() {
		findStr = textSearch.getValue();
		dataStore.on('beforeload', function (store, options) {
			Ext.apply(dataStore.proxy.extraParams, { courseID: courseID, findStr: findStr }); 
		});
		bbar.moveFirst();
//		dataStore.load({
//			callback: function(records, operation, success) {
//				if (success) bbar.moveFirst();
//				else Ext.Msg.alert("数据载入失败！");}
//		});
	};
	var textSearch = Ext.create('Ext.form.TextField', {
		emptyText: '请输入查询内容',
		width: 150,
		height: 25,
		listeners: {  
			specialkey: function(field,e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					reloadStore();
				}
			}
		}

	});
	var btnSearch = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '查询',
		icon: "lib/image/toolbar/search.png",
		handler: reloadStore
	});
	var btnAdd = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '添加',
		icon: "lib/image/toolbar/add.gif",
		handler: function() {
			createForm({
				action: 'addProject',
				url: "TestAction_addTestBase"
			});
			showWin( { winId: 'addProject', title: '新增题库', items: [forms] } );
		}
	});
	var btnEdit = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '编辑',
		disabled:true,
		icon: "lib/image/toolbar/edit.png",
		handler: function() {
			createForm({
				action: 'editProject',
				url: "TestAction_editTestBase"
			});
			showWin( { winId: 'editProject', title: '修改题库', items: [forms] } );
		}
	});
	var btnDel = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '删除',
		disabled:true,
		icon: "lib/image/toolbar/delete.gif",
		handler: function() {
			getSel();
			Ext.Msg.confirm('删除', '确定彻底删除吗？', function(buttonID) {
				if (buttonID == 'yes')
					$.getJSON(encodeURI('TestAction_deleteTestBase'), { IDList: IDList.toString()}, function (res) { bbar.moveFirst(); });
			});
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
				textSearch.setValue('');
				reloadStore();
			}
		}
	});
	
	var tbar = new Ext.Toolbar({
		defaults: { scale: 'medium' }
	});
	
	tbar.add(textSearch);
	tbar.add(btnSearch);
	tbar.add('-');
	tbar.add(btnAdd);
	tbar.add(btnEdit);
	tbar.add(btnDel);
	tbar.add('-');
	tbar.add(comboBox);
	
	var bbar = Ext.create('Ext.PagingToolbar',{
		pageSize: pageSize,
		displayInfo: true,
		emptyMsg: "没有数据要显示！",
		displayMsg: "当前为第{0}--{1}条，共{2}条数据", //参数是固定的，分别是起始和结束记录数、总记录数
		store: dataStore
	});
	
	var createForm = function(config) {
		forms = Ext.create('Ext.form.Panel', {
			renderTo: Ext.getBody(),
			bodyPadding: 5,
			buttonAlign: 'center',
			autoScroll: true,
			frame: false,
			items: [{
				xtype: 'container',
				flex: 1,
				layout: 'anchor',
				items: [{
					xtype:'textfield',
					fieldLabel: 'ID',
					name: 'ID',
					hidden: true,
					hiddenLabel: true
				},{
					xtype:'textarea',
					fieldLabel: '题目',
					labelWidth: 50,
					labelAlign: 'right',
					anchor:'100%',
					name: 'Question'
				},{
					xtype:'textfield',
					fieldLabel: '选项A',
					labelAlign: 'right',
					labelWidth: 50,
					anchor:'100%',
					name: 'OptionA'
				},{
					xtype:'textfield',
					fieldLabel: '选项B',
					labelAlign: 'right',
					labelWidth: 50,
					anchor:'100%',
					name: 'OptionB'
				},{
					xtype:'textfield',
					fieldLabel: '选项C',
					labelAlign: 'right',
					labelWidth: 50,
					anchor:'100%',
					name: 'OptionC'
				},{
					xtype:'textfield',
					fieldLabel: '选项D',
					labelAlign: 'right',
					labelWidth: 50,
					anchor:'100%',
					name: 'OptionD'
				},{
					xtype:'radiogroup',
					fieldLabel: '答案',
					labelAlign: 'right',
					labelWidth: 50,
					anchor:'100%',
					items:[
							{boxLabel: '选项A', name: 'Answer', inputValue: 'A', checked: true},
							{boxLabel: '选项B', name: 'Answer', inputValue: 'B'},
							{boxLabel: '选项C', name: 'Answer', inputValue: 'C'},
							{boxLabel: '选项D', name: 'Answer', inputValue: 'D'}
					]
				}]
			}],
			buttons: [{
				text: '取消',
				handler: function(){ this.up('window').close();	}
			},{
				text: '确定',
				handler: function() {
					if(forms.form.isValid()){
						forms.form.submit({
							clientValidation: true,
							url: encodeURI(config.url),
							method : 'post',
							params : {courseID: courseID},
							success: function(form, action) { bbar.moveFirst();	},
							failure: function(form, action) { bbar.moveFirst(); }
						})
						this.up('window').close();
						bbar.moveFirst();
					} else Ext.Msg.alert('警告','请完善信息！'); 
				}
			},{
				text: '重置',
				handler: function(){
					forms.form.reset();
					if (config.action == "editProject")	forms.getForm().loadRecord(selRecs[0]);  //加载选中记录数据
				}
			}]
		});// forms定义结束
		if (config.action == 'editProject') forms.getForm().loadRecord(selRecs[0]);  //加载选中记录数据
	};
	
	var showWin = function(config) {
		var defaultCng = {
			modal: true,  //设定为模态窗口
			plain: true,
			width: 600,
			height: 400,
			layout: 'fit',
			titleAlign: 'center',
			closable: true,		//可关闭的
			closeAction: 'close',	//关闭动作，有hide、close、destroy
			draggable: true,
			resizable: true,
			maximizable: true,
			constrain: true
		};
		config = $.extend(defaultCng, config);
		var win = new Ext.Window(config);
		win.show();
		return win;
	};
		
	var grid = Ext.create('Ext.grid.Panel', {
		selModel: new Ext.selection.CheckboxModel({ selType: 'checkboxmodel' }),
		store: dataStore,
		height: 600,
		pageSize: pageSize,
		columns: [
			{ text: '序号', xtype: 'rownumberer',width: 40, sortable: false},
			{ text: '问题', dataIndex: 'Question', align: 'center', width: 180},
			{ text: '选项A', dataIndex: 'OptionA', align: 'center', width: 120},
			{ text: '选项B', dataIndex: 'OptionB', align: 'center', width: 120},
			{ text: '选项C', dataIndex: 'OptionC', align: 'center', width: 120},
			{ text: '选项D', dataIndex: 'OptionD', align: 'center', width: 120},
			{ text: '答案', dataIndex: 'Answer', align: 'center', width: 80},
		],
		bbar: bbar,
		tbar: tbar,
		viewConfig: { loadMask: { msg: '正在加载数据中……'	} },
		listeners: {
			selectionchange: function(me, selected, eOpts) {
				selRecs = grid.getSelectionModel().getSelection();
				if(selRecs.length == 1) btnEdit.enable();
				else btnEdit.disable();
				//多选的按钮
				if(selRecs.length >= 1) btnDel.enable();
				else btnDel.disable();
			}
		}
	});
	
	panel.add(grid);
	courseStore.load();
})