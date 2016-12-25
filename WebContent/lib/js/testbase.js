Ext.onReady(function() {
	var courseID;
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
	
	var panel = Ext.create('Ext.panel.Panel', {
		renderTo: 'TestBaseTable',
		title: '题库管理',
		layout : 'fit',
		autoScroll : true,
		listeners : {
   	        'resize' : function (component, width, height, oldWidth, oldHeight, eOpts) {}
		}
	});
	
	var btnAdd = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '添加',
		icon: "lib/image/toolbar/add.gif",
		handler: function() {
			createForm({
				autoScroll: true,
				bodyPadding: 5,
				action: 'addProject',
				url: "",
				items: [{
					xtype: 'container',
					anchor: '100%',
					layout: 'hbox',
					items:[{
						xtype: 'container',
						flex: 1,
						layout: 'anchor',
						items: [{
							xtype:'textfield',
							fieldLabel: 'ID',
							labelWidth: 120,
							labelAlign: 'right',
							anchor:'100%',
							name: 'ID',
							hidden: true,
							hiddenLabel: true
						},{
							xtype:'textfield',
							fieldLabel: 'Accessory',
							labelWidth: 120,
							labelAlign: 'right',
							anchor:'100%',
							name: 'Accessory',
							hidden: true,
							hiddenLabel: true
						},{
							xtype:'textfield',
							fieldLabel: '设施、装备、物资名称',
							labelWidth: 150,
							labelAlign: 'right',
							anchor:'100%',
							name: 'Content'
						},{
							xtype:'textfield',
							fieldLabel: '数量',
							labelAlign: 'right',
							labelWidth: 150,
							anchor:'100%',
							name: 'Quantity'
						},{
							xtype:'textfield',
							fieldLabel: '设施、装备、物资状态',
							labelWidth: 150,
							labelAlign: 'right',
							anchor:'100%',
							name: 'State'
						},{
							xtype:'textfield',
							fieldLabel: '负责人',
							labelWidth: 150,
							labelAlign: 'right',
							anchor:'100%',
							name: 'Responsible'
						}]
					},{
						xtype: 'container',
						flex: 1,
						layout: 'anchor',
						items: [{
							xtype:'textfield',
							fieldLabel: '所属类别',
							labelAlign: 'right',
							anchor:'100%',
							name: 'Type'
						},{
							xtype:'textfield',
							fieldLabel: '单位',
							labelAlign: 'right',
							anchor:'100%',
							name: 'Unit'
						},{
							xtype:'textfield',
							fieldLabel: '存放地点',
							labelAlign: 'right',
							anchor:'100%',
							name: 'Place'
						}]
					}]
				}]
			});
			bbar.moveFirst();	//状态栏回到第一页
			showWin( { winId: 'addProject', title: '新增题库', items: [forms] } );
		}
	});
	var btnEdit = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '编辑',
		disabled:true,
		icon: "lib/image/toolbar/edit.png"
	});
	var btnDel = Ext.create('Ext.Button', {
		width: 55,
		height: 32,
		text: '删除',
		disabled:true,
		icon: "lib/image/toolbar/delete.gif"
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
				dataStore.on('beforeload', function (store, options) {
					Ext.apply(dataStore.proxy.extraParams, { courseID: courseID }); 
				});
				dataStore.load({
					callback: function (records, operation, success) {
						if (success) bbar.moveFirst();
						else alert("load failed");}
				});
			}
		}
	});
	
	var tbar = new Ext.Toolbar({
		defaults: { scale: 'medium' }
	});
	
	tbar.add(btnAdd);
	tbar.add(btnEdit);
	tbar.add(btnDel);
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
			bodyPadding: config.bodyPadding,
			buttonAlign: 'center',
			autoScroll: true,
			frame: false,
			items: config.items,
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
							success: function(form, action) { dataStore.load( { params: { start: 0, limit: pageSize } } );	},
							failure: function(form, action) { Ext.Msg.alert('警告',action.result.msg); }
						})
						this.up('window').close();  	
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
			width: 800,
			height: 500,
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
		tbar: tbar
	});
	
	panel.add(grid);
	courseStore.load();
})