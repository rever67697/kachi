$(function(){
	changeValidateCode($('#code')[0]);
	initLogInfo();
	
	$('#savePwd').click(function(){
		$(this).toggleClass('checked');
	});
	
	//绑定enter键登录事件
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			doLogin();
		}
	});
	
});

function checkLogin() {
	var loginName=$('[name=userName]').val();
	var passWord=$('[name=passWord]').val();
	var code=$('[name=code]').val();
	if(''==loginName) {
		alert('请输入用户名!');
		return false;
	}
	if(''==passWord) {
		alert('请输入密码!');
		return false;
	}
	if(''==code) {
		alert('请输入验证码!');
		return false;
	}
	return true;
}

function doLogin() {
	var bl=checkLogin();
	if(bl) {
		checked=$('#savePwd')[0].checked;
		if(checked) {
			createCookie('kachi_username',$('[name=userName]').val(),7);
			createCookie('kachi_password',$('[name=passWord]').val(),7);
		}
		else {
			createCookie('kachi_username','',7);
			createCookie('kachi_password','',7);
		}
		createCookie('kachi_savePwd',checked,7);
		//form1.submit();
	}
}

	
function changeValidateCode(obj) {
	   //获取当前的时间作为参数，无具体意义
	   var timenow = new Date().getTime();
	   //每次请求需要一个不同的参数，否则可能会返回同样的验证码
	   obj.src = getContextPath()+"/verificationCode?d=" + timenow;
 }

/**初始化登陆信息**/
function initLogInfo() {
    savePwd=readCookie('kachi_savePwd');
    if(eval(savePwd)) {
    	$('[name=userName]').val(readCookie('kachi_username'));
    	$('[name=passWord]').val(readCookie('kachi_password'));
    	$('#savePwd').attr('checked',true).addClass('checked');
    }
}

function createCookie(name,value,days){
	if (days){
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}
function readCookie(name){
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++){
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}
function eraseCookie(name){
	this.createCookie(name,"",-1);
}
function getContextPath(fullUrl){
	   if(fullUrl==null || fullUrl==''){
		   fullUrl = window.location.href+'';
	   }
	   var arrUrl = fullUrl.split('/');
	   return arrUrl[0]+'//'+arrUrl[2]+'/'+arrUrl[3];
}