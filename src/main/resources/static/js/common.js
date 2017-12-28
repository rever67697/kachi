var kcJs=
(function($) {
    //定义颜色
    var v_colors={"blue":"rgb(67,116,224)",
				 "gray":"rgb(128,128,128)",
				 "green":"rgb(51,194,7)",
				 "red":"rgb(243,123,29)",
				 "black":"rgb(0,0,0)",
				 "light-blue":"rgb(87,181,227)",
				 "orange":"rgb(240,160,26)"
	};
    //卡状态
    var c_status={"0":["正常",v_colors.blue],
			      "1":["停用",v_colors.red],
			      "2":["指定",v_colors.green],
			      "3":["待激活",v_colors.orange],
			      "4":["作废",v_colors.gray],
    			  "5":["没卡",v_colors.black]
	};
    //终端状态
    var t_status={"0":["正常",v_colors.blue],
			  	  "1":["未初始化",v_colors.orange],
			  	  "2":["停用",v_colors.red],
			  	  "3":["注销",v_colors.gray]
	};
    //中国相关的运营商代码
    var d_mnc={"00":"中国移动",
    		 "01":"中国联通",
    		 "02":"中国移动",
    		 "03":"中国电信",
    		 "04":"中国移动",
    		 "05":"中国电信",
    		 "06":"中国联通",
    		 "07":"中国移动",
    		 "09":"中国联通",
    		 "11":"电信"
    };
    var dic_noYes=[{"value":"0","name":"否"},{"value":"1","name":"是"}];
	_FUNC={
		createCookie:function(name,value,days){
			if (days){
				var date = new Date();
				date.setTime(date.getTime()+(days*24*60*60*1000));
				var expires = "; expires="+date.toGMTString();
			}else var expires = "";
			document.cookie = name+"="+value+expires+"; path=/";
		},
		 readCookie:function(name){
			var nameEQ = name + "=";
			var ca = document.cookie.split(';');
			for(var i=0;i < ca.length;i++){
				var c = ca[i];
				while (c.charAt(0)==' ') c = c.substring(1,c.length);
				if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
			}
			return null;
		},
		eraseCookie:function(name){
			this.createCookie(name,"",-1);
		},
		//获取项目根路径
		getContextPath:function(fullUrl){
			   if(fullUrl==null || fullUrl==''){
				   fullUrl = window.location.href+'';
			   }
			   var arrUrl = fullUrl.split('/');
			   return arrUrl[0]+'//'+arrUrl[2]+'/'+arrUrl[3];
		},	
		//获取sim卡的状态信息
		getCardStatus:function(o){
			return c_status[o];
		},
		//获取颜色值
		getColor:function(o){
			return v_colors[o];
		},
		//获取终端状态信息
		getTerminalStatus:function(o){
			return t_status[o];
		},
		//接收一个long类型的毫秒数，返回格式化的字符串
		getDate:function(o,pattern){
			if(o && /^\d+$/.test(o)){
				pattern = pattern||'yyyy-MM-dd';
				return new Date(parseInt(o)).format(pattern);
			}
			return '';
		},
		//用作输入框日期的格式化
		formatInputDate:function(obj,value,pattern){
			$(obj).val(this.getDate(value,pattern||'yyyy-MM-dd'));
		},
		//给指定容器下的输入框自动填充数值
		autoFillData:function(container,data,arr){
			var _this = this;
			$('input,select',$(container)).each(function(i,o){
	  			var name = $(this).attr('name');
	  			if(name){
	  				$(this).val(data[name]);
	  				if(arr){
	  					for(var i = 0;i < arr.length;i++){
	  						if(name==arr[i]){
	  							_this.formatInputDate($(this),data[name],'yyyy-MM-dd hh:mm:ss');
	  						}
	  					}
	  				}
	  			}
	  		});
		},
		//给指定的select容器初始化数据，参数container：容器,data：数组数据,needNull：是否需要给一个空值，nullDesc：空值的描述，defaultVal：是否选中默认值
		initSelectOption:function(option){
			if(!option.data){
				return;
			}
			option = $.extend({"value":"value","name":"name","nullDesc":"--请选择--"},option);
			var html = '';
			if(option.needNull) html+='<option value="">'+option.nullDesc+'</option>';
			$.each(option.data,function(i,o){
				html += '<option value="'+o[option.value]+'" '+(option.defaultVal&&o[option.value]==option.defaultVal?"selected":"")+'>'+o[option.name]+'</option>';
			});
			$(option.container).html(html);
		},
		//给指定的select容器通过url返回的数组初始化数据
		initSelect:function(option){
			option = $.extend({"needNull":true,"queryParam":{}},option);
			var _this = this;
			$.post(this.getContextPath()+'/'+option.url,option.queryParams,function(data){
				if(data && data.code=='200'){
					option.data=data.data;
					_this.initSelectOption(option);
				}else{
					$.messager.alert('提示','操作失败','info');
				}
			});
		},
		//给select容器当0：否1：是初始化
		initDic_noYes:function(obj){
			this.initSelectOption({"container":obj,"data":dic_noYes});
		},
		//限定输入框只能输入数字类型
		onlyNumber:function(obj){
			$(obj).keyup(function(){
				$(this).val($(this).val().replace(/\D/,''));
			});
		},
		getMncName:function(o){
			if(o && o.indexOf("460") == 0){
				return d_mnc[o.substr(3,2)];
			}
			return o;
		}
	};
	return {fn:_FUNC};
})(jQuery);	
//解决在火狐上不兼容
$.fn.serializeObject = function()
{
 var o = {};
 var a = this.serializeArray();
 $.each(a, function() {
  if (o[this.name]) {
   if (!o[this.name].push) {
    o[this.name] = [o[this.name]];
   }
   o[this.name].push(this.value || '');
  } else {
   o[this.name] = this.value || '';
  }
 });
 return o;
};
Date.prototype.format = function(format) { 
	  var o = { 
	    "M+" : this.getMonth()+1, //month 
	    "d+" : this.getDate(),    //day 
	    "h+" : this.getHours(),   //hour 
	    "m+" : this.getMinutes(), //minute 
	    "s+" : this.getSeconds(), //second 
	    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
	    "S" : this.getMilliseconds() //millisecond 
	  };
	  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
	    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
	    format = format.replace(RegExp.$1, 
	      RegExp.$1.length==1 ? o[k] : 
	        ("00"+ o[k]).substr((""+ o[k]).length)); 
	  return format; 
};

$(function(){
	$('input,select',$('.table.readOnly')).attr({'readOnly':true});
	kcJs.fn.initDic_noYes($('.fill-noYes'));
	kcJs.fn.onlyNumber($('.onlyNumber'));
});