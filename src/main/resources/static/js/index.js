$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次密码不一致'
    }
});
$(document).ready(function () {
    $.post(kcJs.fn.getContextPath()+'/getUser',function(data){
        if(data){
            $('.userName').text(data.name);
            $('.userName').menubutton({
                iconCls: 'icon-user',
                menu: '#userMenu'
            });
            $('.departmentName').text(data.departmentName);
        }
    },'json');

    $.post(kcJs.fn.getContextPath()+'/getMenu',function(data){
        if(data){
            InitLeftMenu(data);
        }
    },'json');

    tabClose();
    tabCloseEven();
});



function addTab(subtitle, url,iconCls) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26 ,
            iconCls:iconCls
        });
    } else {
        $('#tabs').tabs('select', subtitle);

    }
    tabClose();
}


function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}


function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children("span").text();
        $('#tabs').tabs('close', subtitle);
    });

    $(".tabs-inner").bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY,
        });
        var subtitle = $(this).children("span").text();
        $('#mm').data("currtab", subtitle);
        return false;
    });
}

//绑定右键菜单事件
function tabCloseEven() {
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
    });

    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != currtab_title)
                $('#tabs').tabs('close', t);
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
    //更新
    $('#mm-tabupdate').click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        $('#tabs').tabs('update',{
            tab:currTab,
            options:{
                content:createFrame(url)
            }
        });
    });
}

function InitLeftMenu(data) {
    $("#nav").find(".panel-body").each(function(index) {
        var t=$(this).panel("options").title;
        $('#nav').accordion("remove",t);
    });

    $("#nav").accordion({animate:false});
    if(data.length==0){
        $.messager.alert('提示','当前用户没有授权！','info');
        return;
    }
    $.each(data, function(i, n) {
        var menulist ='';
        menulist +='<ul style="padding-left:5px;">';
        $.each(n.children, function(j, o) {
            menulist += '<li><div><a href="javascript:void(0)" rel="' + kcJs.fn.getContextPath()+'/'+o.url + '?id='+o.id+'"><span class="icon '+o.iconCls+'" >&nbsp;</span>';
            menulist += '<span class="nav leftMenuSel">' +o.text + '</span></a></div></li> ';
        });
        menulist += '</ul>';
        $('#nav').accordion('add', {
            title: '<span class="accordionCls">'+n.text+'</span>',
            //title:n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.iconCls,
            height:'15px'
        });

    });
    //给所有有连接的子菜单加上事件
    $('.easyui-accordion div ul li a').click(function(){
        var tabTitle = $(this).children('.nav').text();
        var url = $(this).attr("rel");
        //获取菜单前面的图标样式
        var icon = 'icon '+$('.icon',this).attr('class');
        if(''!=url&&'null'!=url) {
            addTab(tabTitle,url,icon);
        }else {
            addTab(tabTitle,kcJs.getContextPath()+'/site/error404.html',icon);
        }

        $('.easyui-accordion div ul li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function(){
        $(this).parent().addClass("hover");
    },function(){
        $(this).parent().removeClass("hover");
    });

    //选中第一个子菜单
    var panels = $('#nav').accordion('panels');
    if(panels && panels[0]){
        var t = panels[0].panel('options').title;
        $('#nav').accordion('select', t);
    }
}

function logout(){
    $.messager.confirm('提示','是否退出登录？',function(r){
        if(r){
            $.post(kcJs.fn.getContextPath()+'/logout',function(data){
                window.location.href=kcJs.fn.getContextPath()+'/site/login.html';
            },'json');
        }
    });
}

function modifyPwd(){
    $('#pwdForm')[0].reset();
    $('#pwdDialog').dialog('open');
}

function submit(){
    if($('#pwdForm').form('validate')){
        var oldPwd = $('[name=oldPwd]').val();
        var newPwd = $('[name=newPwd]').val();
        $.post(kcJs.fn.getContextPath()+'/modifyPwd',{oldPwd:oldPwd,newPwd:newPwd},function(data){
            if(data && data.code=='200'){
                $.messager.alert('提示','修改成功,将于3秒后退出登录','info');
                setTimeout(function(){
                    $.post(kcJs.fn.getContextPath()+'/logout',function(data){
                        window.location.href=kcJs.fn.getContextPath()+'/site/login.html';
                    },'json');
                },3000);
            }else{
                $.messager.alert('提示',data.msg,'info');
            }
        });
    };
}