var kcJs=
(function($) {
    //定义颜色
    var v_colors={"blue":"rgb(67,116,224)",
			"gray":"rgb(128,128,128)",
			"green":"rgb(51,194,7)",
			"red":"rgb(243,123,29)",
			"black":"rgb(0,0,0)"
		   };	
    var v_status={"0":["正常",v_colors.blue],
			  "1":["没卡",v_colors.black],
			  "2":["指定",v_colors.green],
			  "3":["停机",v_colors.red],
			  "4":["作废",v_colors.gray]
			};
	_FUNC={
		getContextPath:function(fullUrl){
			   if(fullUrl==null || fullUrl==''){
				   fullUrl = window.location.href+'';
			   }
			   var arrUrl = fullUrl.split('/');
			   return arrUrl[0]+'//'+arrUrl[2]+'/'+arrUrl[3];
		},	
		getCardStatus:function(status){
			return v_status[status];
		},
		getColor:function(o){
			return v_colors[o];
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
//cookie
function createCookie(name,value,days)
{
	if (days)
	{
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name)
{
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++)
	{
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name)
{
	createCookie(name,"",-1);
} 
