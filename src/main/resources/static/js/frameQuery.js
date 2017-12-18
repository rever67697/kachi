/** frameQuery.js **/
/*
 * jQuery frameQuery Animations
 * 作者yp 2012年12月28日
 * 查询框架
 * 配合sqlhelper.do类
 * 框架介绍
 * 1.
 * 
 * 
 * 参数说明
 * 1.表格部分 2.查询条件部分
 * options={
 *   queryId,   //查询ID和数据库表query_tbs_public.fid对应
 *   queryFrom, //查询条件提交的表单id
 *   querygrid, //加载列表的tableid
 *   //以及其他easyui datagrid本来该有的属性，例如：
 *   toolbar,   //操作数据按钮
 *   columns,   //查询列表表头列
 *   onLoadSuccess, //加载成功后回调函数 
 *   onDblClickRow, //双击选择调用函数
 * }
 * 参数实例
 * options={
 * queryId:'TJ0001',
 * queryFrom:'queryform',
 * querygrid:'certificationgrid',
 * title:'',
 * columns:[
 * 				{field:'REGCODE',title:'申请登记号',width:'100'},
				{field:'TASKNOTE',title:'申请类别',width:'150'},
				{field:'ORG_NAME',title:'单位名称',width:'100'},
				{field:'LIC_BEG_DATE',title:'有效期开始时间',width:'100'},
				{field:'LIC_END_DATE',title:'有效期结束时间',width:'100'},
				{field:'CONTACT_STAFF',title:'联系人',width:'60'},
				{field:'CONTACT_TEL',title:'联系人电话',width:'100'}
 * ],
 * onLoadSuccess:function(page){
 * 
 * }
 * }
 */
jQuery.frameQuery = function (options) {
	var dtd = $.Deferred();//jq1.5新增的延时对象
	
	var CONST = {
		COOK_COND_PANEL_COLL:"tj_c",//用于记录查询条件面板是否折叠的cookies名字
		COOK_PAGE_ROW:"tj_p"//用于记录查询分页数的cookies名字
	}
	var parms=encodeURI(encodeURI(decodeURIComponent($('#'+options.queryFrom).serialize(),true)));
 
	if (options.queryURL=="" || options.queryURL==null){
		  $.error("你写的查询没有给相对路径！");
	}
	var queryURL=getContextPath()+"/"+options.queryURL;
	var gridConf={
		cookies:{
			collPanel:true,
			pageRow:true
		},
		//pageList:[10,15,20,30],--chendaoheng
		pageList:[30,50,80,100,200,300],
		url:queryURL,
		queryParams:$('#'+options.queryFrom).serializeObject(),
		nowrap: false,
		fitColumns:false,
		//pageNumber:1, add by huangwen 默认不缓存页码
		pageNumber:1, 
		iconCls:'icon-save',
		nowrap: true,
		striped: true,
		fixCsv:function(){
			dtd.resolve();
		},
		fit:true,
		collapsible:true,
		pagination:true,
		rownumbers:true,
		onBeforeLoad:function(page){
			//$(this).datagrid('options').url = queryURL;
			
		},
		onLoadSuccess: function(dd){
			dtd.resolve();
		}
	};
	
	//进行处理,对于传入的options中queryParams中的项优先取
	if(options.queryParams){
		$.each(options.queryParams,function(k,v2){
			if(gridConf.queryParams[k]!=''&&undefined!=gridConf.queryParams[k]) {
				options.queryParams[k]=gridConf.queryParams[k];
			}
		})
	}
	//	
	if(options.extendToolBar&&options.flag==0) {
		options.toolbar = options.toolbar.concat(options.extendToolBar);
		options.flag=1;		
	}
	var myconf = $.extend(true,{},gridConf,options);
	myconf.columns = options.columns;
	
	if (myconf.cookies.pageRow) {
		var pageSize = readCookie(CONST.COOK_PAGE_ROW);//读取已经记录的分页数，时间顺序是要放在 grid初始化前
		if (pageSize) {
			myconf.pageSize = pageSize;
		}
	}
	
	var grid = $('#'+options.querygrid).datagrid(myconf);//渲染表格
	
	
	var gPager = grid.datagrid("getPager");
	gPager.pagination({
		onChangePageSize:function(ps){
			createCookie(CONST.COOK_PAGE_ROW,ps,30);//记录一下分页数
			grid.datagrid("options").pageSize = ps;
			//grid.datagrid("reload");
		}
	})
			
	//增加导出按钮
    if(options.exportFile){
    	gPager.pagination({
    		buttons:[{text:"导出全部",iconCls:'icon-export_xls',handler:exportCSV}]
    	});
    }
    
	var qcp = options.queryCondPanel || {
		region:"north"
	}
	
    if(qcp){//查询条件图标
    	if(qcp.icon){
    		$('body').layout("panel",qcp.region || "north").panel({
			 	onCollapse:function(){
					var collNor = $('body').data("layout").panels.expandNorth;
					if(collNor){
						if(collNor.data("qfIcon")){
	    					return;
	    				}else{
							$('body').data("layout").panels.expandNorth.panel("setTitle","<span class='icon "+options.queryCondPanel.icon+"' style='height:12px;'></span>点击展开").data("qfIcon",true);
	    				}
					}
					if(myconf.cookies.collPanel){
						createCookie(CONST.COOK_COND_PANEL_COLL,"1");
					}
				},
				onExpand:function(){
					if(myconf.cookies.collPanel){
						createCookie(CONST.COOK_COND_PANEL_COLL,"0");
					}
				}
			 })
    	}
    }
    
	if (myconf.cookies.collPanel) {
		var coll = readCookie(CONST.COOK_COND_PANEL_COLL);
		if (coll === "1") {
			$('body').layout("collapse", qcp.region || "north");
		}
	}
     
    //导出CSV文件
    function exportCSV(){
    	var totalNum = grid.datagrid("getData").total;
		if(totalNum<1){
			$.messager.alert('失败','不能导出空表单');
			return false;
		}
    	var fparams = grid.datagrid("options")["queryParams"];
    	var overseeDept='';	
    	if(!options.exportFlag){
	    	if($('input[name = s_overseeDept]').val())
	    	{
	    		overseeDept=$('input[name = s_overseeDept]');
	    	}
	    	if($('input[name = OVERSEE_DEPT]').val())
	    	{
	    		overseeDept=$('input[name = OVERSEE_DEPT]');
	    	}
	    	if($('input[name = overseedept]').val())
	    	{
	    		overseeDept=$('input[name = overseedept]');
	    	}
		    var overseeDeptVal=[];
		    if(overseeDept!='')
		    {
		    overseeDept.each(function(index,domEle){ 	    
		    overseeDeptVal.push($(domEle).val());
		    });
		    }
		    $('input[name = s_overseeDept]').val(overseeDeptVal);
		    $('input[name = OVERSEE_DEPT]').val(overseeDeptVal);
		    $('input[name = overseedept]').val(overseeDeptVal);
    	}
    	
    	//为了确保多个表使用同一个查询框时，导出表头正确---cdh
    	var form = $("#"+options.queryFrom);
    	
    	/************************以下 之前的代码 by/huangwen 2017-03-31******************************************/
//    	var tableHead = encodeURI(decodeURIComponent("序号," + grid.datagrid("getColumnTitles",options.exportAllCol ? 'all' : undefined), true));
//    	form.find('input[name="tablehead"]').val(tableHead);
    	/************************以上 之前的代码 by/huangwen 2017-03-31******************************************/
    	
    	/*****以下 过滤掉hidden的字段,省去维护查询脚本和columns的field字段顺序一致的成本 by/huangwen 2017-03-31*****************/
    	var tableHead =  thHelper.tablehead(options);
    	var tableHeadFields =  thHelper.tableHeadFields(options);
    	if(options.queryFrom){
//    		$.each(form.find('input'),function(i,e){
//    			e.value = thHelper.encodeVal(e.value) ;
//    		})
    		form.find('input[name="tablehead"]').val(tableHead);
    		form.find('input[name="tableheadfield"]').val(tableHeadFields);
    	}else{
    		form=$("<form>");
			form.attr("style","display:none").attr("target","").attr("method","post");
			form.append('<input type="hidden" name="tablehead" value="'+tableHead+'">');
			form.append('<input type="hidden" name="tableheadfield" value="'+tableHeadFields+'">');
			for(var k in options.queryParams)
				form.append('<input type="hidden" name="'+k+'" value="'+encodeURI(decodeURIComponent(options.queryParams[k]),true)+'">');
			$("body").append(form);
//			alert(form.html());
    	}
    	
    	
    	/*****以下 过滤掉hidden的字段,省去维护查询脚本和columns的field字段顺序一致的成本 by/huangwen 2017-03-31*****************/
    	
    	if( options.onfixcsv ){
    		/************************以下 保留tableHead原本的版本 by/huangwen 2017-03-31***********************/
        	var tableHead = encodeURI(decodeURIComponent("序号," + grid.datagrid("getColumnTitles",options.exportAllCol ? 'all' : undefined), true));
        	form.find('input[name="tablehead"]').val(tableHead);
        	/************************以上 保留tableHead原本的版本 by/huangwen 2017-03-31***********************/
    		
        	var tableHeadFields = encodeURI(decodeURIComponent("RN," + grid.datagrid("getCsvColumnFields",options.exportAllCol ? 'all' : undefined), true));
        	form.find('input[name="tableheadfield"]').val(tableHeadFields);
    	}
    	var url =  queryURLs + "&pagerow=" + totalNum + "&rows=" + totalNum + "&page=1&resptype=csv&isfile=1";
    	
    	if( options.onfixcsv ){
    		var formdata = $("#"+options.queryFrom).serializeObject();
    		var $csvform = $("#__zhcsvform");
    		if( $csvform.size() < 1){
    			$csvform = $('<form id="__zhcsvform" style="display:none;" method="post"></form>');
    			$(document.body).append($csvform);
    		}
    		$csvform.html("");
    		var html = "";
    		var csvformdata = $.extend({},fparams);
    		csvformdata['tableheadfield'] = formdata['tableheadfield'];
    		csvformdata['tablehead'] = formdata['tablehead'];
    		$.each(csvformdata,function(k,v){
    			if( k != 'pagerow' && k != 'rows' && k != 'page' && k != 'resptype'  && k != 'isfile' ){
    				html += '<input name="' + k + '" type="hidden" value="' + v + '" /> ';
    			}
    		});
    		$csvform.html(html);
			$csvform.attr("action",url).submit();
    	}else{
    		form.attr("action",url).submit();
    	}
    	
		return false;//兼容IE6
    }
    
    /**
     * 获取tablehead 和 tableHeadFields 的工具类 add by huangwen 2017-03-31
     */
    var thHelper = {
    		title:"TITLE",
    		filed:"FIELD",
    		tablehead : function(option){
    			return this.getColumnTitleOrFiled(option,this.title);
    		},
    		tableHeadFields : function(option){
    			return this.getColumnTitleOrFiled(option,this.filed);
    		},
    		getColumnTitleOrFiled:function(option,modifier){
    			var columns = option.columns[0];
    			var items = modifier==this.filed ? 'RN,': '序号,';
    			for(var i in columns)
    				if(columns[i].hidden!=true && columns[i].field!=undefined && columns[i].title!=undefined)
    					if(modifier==this.filed)
    						items += columns[i].field + ",";
    					else
    						items += columns[i].title.replace(/<br\/>/g ,'') + ",";
    			return this.encodeVal(items);
    		},
    		encodeVal:function(value){
    			return encodeURI(decodeURIComponent(value.replace(/%/g ,'％'),true))
    		},
    		decodeVal:function(value){
    			return decodeURI(encodeURIComponent(value.replace(/%/g ,'％'),true))
    		},
    }
    
    
    
    
	
    function beginDownload(){
    	// 待完成
    }
	
    $.when(dtd).done(function(){
		if (!grid.data("hasParsed")) {
			setTimeout(function(){
				var form = $("#"+options.queryFrom);
				form.find("[class^=defer-]").addClass(function(idx,oCls){
					return oCls.replace(/defer-/,"");
				})
				
				var formdata = form.serializeObject(); // mod by zh : 2016-09-14 
				$.parser.parse(form);
				//form.form('load',formdata);  // mod by zh : 2016-09-14
				
				$(".table_search").find(".combo").live("focusin.frameQuery",function(){//下拉框获得焦点时显示面板
					$("div.combo-panel").panel("close");
					$(this.previousSibling).combo("showPanel");
				})
				if (options.exportFile) {
					var tableH = form.find('input[name="tablehead"]');
					//多个表使用同一个查询框时，只添加一个表头字段---cdh
					if( tableH.size() < 1)
					//var tableHead = encodeURI(decodeURIComponent("序号," + grid.datagrid("getColumnTitles",options.exportAllCol ? 'all' : undefined), true));
					//var filename = encodeURI(decodeURIComponent(options.title, true));
					form.append('<input type="hidden" name="tablehead" value="">').attr("method","post");
					
					var tableheadfield = form.find('input[name="tableheadfield"]');
					//多个表使用同一个查询框时，只添加一个表头字段---cdh
					if( tableheadfield.size() < 1)
						form.append('<input type="hidden" name="tableheadfield" value="">');
				}
				
				if(options.deferParse){
					options.deferParse();
				}
			},500);
		}else{
			grid.data("hasParsed",true);
		}
	})	    
}

$.extend({
	frameFast:function(conf){
		var fastSearchKey = conf.fastSearchKey || "fastSearch";
		var dlg = $("#"+fastSearchKey+"Dlg");
		if(dlg.data('hasInit')){
			dlg.dialog("open");
			return;
		}
	
		var defaultConf = {
			nowrap: true, //数据不换行
			striped: true, //数据间隔表格
			fit: true,
			pagination:true,
			rownumbers:true,
			remoteSort: false,//不采用服务端排序
			fitColumns:false,
			queryParams:{resptype:'pagejson'},
			iconCls:'icon-search',
			dlgConf:{}
		}
		
		var dftDlgConf = {
			width:650,
			height:420,
			iconCls:'icon-search',
			modal:true,
			shadow:false
		}
		
		var useConf = $.extend(true,{},defaultConf,conf);
		var dlgConf = $.extend(true,{},dftDlgConf,useConf.dlgConf)
		
		
		var grid = $("#"+fastSearchKey+"Gd");
		var sform = $("#"+fastSearchKey+"Form");
		var btnSubmit = $("#"+fastSearchKey+"Submit");
		
		
		grid.datagrid(useConf);
		dlg.dialog(dlgConf).dialog("open").data('hasInit',true);
		
		btnSubmit.click(function(){
			grid.datagrid("reload",$.extend({},useConf.queryParams,sform.serializeObject()));
		})
	
	},
	/*
	 * 包含着一个表单和多个表格的统一提交框架
	 * 效果是：把表单填好，然后再把表格填好，一次性提交，后台即可接收
	 * 要以easyUI的扩展edatagrid为基础
	 * 
	 * @param {Object} conf
	 * 框架参数：
	 * saveUrl:提交表单的地址
	 * loadUrl:载入数据的地址（有可能是action中带有数据，则忽略）
	 * 表格参数：
	 * newRowDefaultValue:新增加行的默认值
	 */	
	frameForm:function(conf){
		var frameDefault = {
			defaultSumbitData:{},
			jsonDataKey:"jsonData",
			submitBtn:"#frameSubmit",
			formObj:'#bookInForm'
		}
		
		var useConf = {};
		var fConf = $.extend({},frameDefault,conf);
		
		if(fConf.grid){
			$.each(fConf.grid,function(){
				var grid1Conf = this;
				var grid = $("#" + grid1Conf.datagridId).addClass("frame-form-grid");
				
				function isEmptyNewRow(row){//是一条空的新行则返回true，其他情况返回false
					if(row && row.isNewRecord){
						var retVal=true;
						$.each(row,function(key,val){
							if(retVal && key != "isNewRecord"){
								retVal = retVal && (val==="" || val==null)
							}
						})
						return retVal;
					}else{
						return false;
					}
				}
				
				var defaultEvent = {
					btnAdd:function(){
						grid.edatagrid("addRow");
					},
					btnDel:function(){
						var rows = grid.edatagrid("getSelections");
						if(rows){
							$.each(rows,function(idx,val){
								grid.edatagrid("deleteRow",grid.edatagrid("getRowIndex",val));
							})
						}
					},
					editEvent:function(rowIndex,rowData){//编辑事件，按回车接受编辑，Esc取消
						var edTr = grid.edatagrid("getPanel").find("tr.datagrid-row-editing");
						edTr.find("input").bind("keydown.edGrid",function(e){
							if(e.keyCode==13){
								grid.edatagrid("endEdit",rowIndex);
							}
							if(e.keyCode==27){
								grid.edatagrid("cancelEdit",rowIndex);
							}
						});
					}
				}
				$.extend(defaultEvent,grid1Conf);
				
				var excelGrid = {
					nowrap: true, //数据不换行
					striped: true, //数据间隔表格
					rownumbers:true,
					collapsible:true,
					remoteSort: false,//不采用服务端排序
					fitColumns:true,
					resizable:true,
					iconCls:'icon-save',
					loadMsg:'数据正在加载中....请稍候!',
					frozenColumns:[[{field:'ck',checkbox:true}]],
					singleSelect:false,
					pagination:true,
					toolbar:[
						{text:'添加',iconCls:'icon-add',handler:defaultEvent.btnAdd},'-',
						{text:'删除',iconCls:'icon-remove',handler:defaultEvent.btnDel}
					],
					onBeforeLoad:function(param){
						grid.edatagrid("options").url = grid1Conf.url + "?pagerow=" + param.row
					},
					onClickRow:function(rowIndex,rowData) {
						grid.edatagrid("editRow",rowIndex);
				    },
					onBeforeSave:function(ri){//保存之前验证
						return grid.edatagrid("validateRow",ri);
					},
					onSave:function(ri,rd){
						if(isEmptyNewRow(rd)){
							grid.edatagrid("deleteRow",ri);
						}else{
							grid.edatagrid("updateRow",{index:ri,row:$.extend({},grid1Conf.newRowDefaultValue,rd)});
						}
					},
					onEdit:defaultEvent.editEvent,
					onAdd:defaultEvent.editEvent
				}
				
				if(grid1Conf.extendToolBar) {
					grid1Conf.toolbar = excelGrid.toolbar.concat(grid1Conf.extendToolBar)
				}
			
				useConf = $.extend({},excelGrid,defaultEvent,grid1Conf);
				grid.edatagrid(useConf);
			})
		}
		
		$(fConf.submitBtn).click(function(){
			jsGlobal.View.clickMask(true);
			if(this.className.match(/l-btn-disabled/)){
				return ;
			}
			if(!($(fConf.formObj).form("validate")&&customValidator.validate('',fConf.formObj))) {
				$.messager.alert("系统提示","请正确填写表单内容");
				return false;
			}
			
			
			var lbt = /l-btn/.test(this.className);
			if(lbt){
				var $btn = $(this).linkbutton("disable");
			}
			
			var $tbl = $("table.frame-form-grid");
			var select=$tbl.datagrid('getSelected');
			var index=$tbl.datagrid("getRowIndex",select);
			$tbl.datagrid("endEdit",index);
			var data = $.extend({},$(fConf.formObj).serializeObject(),fConf.defaultSumbitData);
			
			function relaSendData(sourceData){//处理发送数据的映射关系
				if(useConf.sendRela){
					var outputData = []
					$.each(sourceData,function(idx,val){
						$.each(val,function(oidx,oval){
							var newKey = useConf.sendRela[oidx]
							if(newKey){
								val[newKey] = oval;
							}
						})
						outputData.push(val);
					})
					return outputData;
				}else{
					return sourceData;
				}
			}
			var ind = [],upd = [],ded = [], dd = [];
			$tbl.each(function(){//把表格上的数据处理成能发送的字符串，可能会有映射关系处理
				var $grid = $(this);
				ind = ind.concat(relaSendData($grid.datagrid("getChanges","inserted")));
				upd = upd.concat(relaSendData($grid.datagrid("getChanges","updated")));
				ded = ded.concat(relaSendData($grid.datagrid("getChanges","deleted")));
				dd = dd.concat(relaSendData($grid.datagrid("acceptChanges").datagrid("getData").rows));
			})
			
			data[fConf.jsonDataKey] = JSON.stringify(dd);//定义在json.js中
			data[fConf.jsonDataKey+"Inserted"] = JSON.stringify(ind);//定义在json.js中
			data[fConf.jsonDataKey+"Updated"] = JSON.stringify(upd);//定义在json.js中
			data[fConf.jsonDataKey+"Deleted"] = JSON.stringify(ded);//定义在json.js中
			
			commfunc.ax(fConf.saveUrl,data,function(msg){
				if(msg) {
					var fillBackData = $.isArray(msg.result) ? msg.result[0] : $.isArray(msg.rows) ? msg.rows[0] : msg; 
					$("[fillBack]",fConf.formObj).each(function(){//提交成功信息回填
						$(this).val(fillBackData[this.name] || fillBackData[this.name.toUpperCase()] || fillBackData[this.getAttribute("fillBack")]);
					})
					
					$.messager.alert('提示','保存成功!','info');
					
					$tbl.each(function(idx,val){
						setTimeout(function(){
							$(val).datagrid("reload");
						},25)
					})
				}
				if(lbt){
					$btn.linkbutton("enable");
				}
				jsGlobal.View.clickMask(false);
			});
			return true;
		})
	}
})

$.extend($.fn.datagrid.defaults.editors,{
	cmbtreeQuery: {
	    init: function(_4fd, conf) {
	        var oldObj = $("<input type=\"text\">").appendTo(_4fd);
	        //_4ff.combotree(_4fe);
		  	var jsonstr='';   	
			$.getJSON(conf.url,function(json){
				jsonstr=JSON.stringify(json).toLowerCase();
				conf.url = undefined;
				oldObj.combotree(conf).combotree('clear').combotree('loadData',JSON.parse(jsonstr));
			});	
	        return oldObj;
	
	    },
	    destroy: function(_500) {
	        $(_500).combotree("destroy");
	
	    },
	    getValue: function(_501) {
	        return $(_501).combotree("getValue");
	
	    },
	    setValue: function(_502, _503) {
	        $(_502).combotree("setValue", _503);
	
	    },
	    resize: function(_504, _505) {
	        $(_504).combotree("resize", _505);
	    }
	}
})
