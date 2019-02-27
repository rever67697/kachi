var kcJs =
    (function ($) {
        //定义颜色
        var v_colors = {
            "blue": "rgb(67,116,224)",
            "gray": "rgb(128,128,128)",
            "green": "rgb(51,194,7)",
            "red": "rgb(243,123,29)",
            "black": "rgb(0,0,0)",
            "light-blue": "rgb(87,181,227)",
            "orange": "rgb(240,160,26)"
        };
        //sim卡状态
        var c_status = {
            "0": ["正常", v_colors.blue],
            "1": ["停用", v_colors.red],
            "2": ["指定", v_colors.green],
            "3": ["待激活", v_colors.orange],
            "4": ["作废", v_colors.gray],
            "5": ["没卡", v_colors.black]
        };
        //sim卡的使用状态
        var u_status = {
            "0": ["空闲", v_colors.blue],
            "1": ["占用", v_colors.red],
        };
        //副卡状态
        var c_channelcardStatus = {
            "0": ["预置卡", v_colors.blue],
            "1": ["临时卡", v_colors.green],
            "2": ["作废", v_colors.red]
        };
        //终端状态
        var t_status = {
            "0": ["正常", v_colors.blue],
            "1": ["未初始化", v_colors.orange],
            "2": ["停用", v_colors.red],
            "3": ["注销", v_colors.gray]
        };
        //终端版本状态
        var tv_status = {
            "0": ["有效", v_colors.blue],
            "1": ["无效", v_colors.red]
        };
        //中国相关的运营商代码
        var d_mnc = {
            "00": "中国移动",
            "01": "中国联通",
            "02": "中国移动",
            "03": "中国电信",
            "04": "中国移动",
            "05": "中国电信",
            "06": "中国联通",
            "07": "中国移动",
            "09": "中国联通",
            "11": "电信"
        };
        //指定卡类型
        var ready_type = {
            "0": ["临时指定一次", v_colors.blue],
            "1": ["一直指定", v_colors.green]
        };
        //取卡结果
        var result_code = {
            "0": "正常",
            "1": "无卡可用",
            "2": "设备欠费",
            "3": "没有订单",
            "4": "拒绝接入",
        };

        var d_cpStatus = {
            "0": ["正常", v_colors.blue],
            "1": ["待激活", v_colors.orange],
            "2": ["拔出", v_colors.gray],
            "8": ["超时", v_colors.red]
        };
        var dic_noYes = [{"value": "0", "name": "否"}, {"value": "1", "name": "是"}];
        //日志分类
        var dic_bussinesstype = [
            {value: "登录信息", name: "登录信息"},
            {value: "用户管理", name: "用户管理"},
            {value: "部门管理", name: "部门管理"},
            {value: "卡池管理", name: "卡池管理"},
            {value: "终端管理", name: "终端管理"},
            {value: "流量卡管理", name: "流量卡管理"},
            {value: "套餐管理", name: "套餐管理"},
            {value: "在线终端管理", name: "在线终端管理"},
            {value: "指定卡管理", name: "指定卡管理"},
            {value: "副卡管理", name: "副卡管理"},
            {value: "App版本管理", name: "App版本管理"},
            {value: "运营商配置", name: "运营商配置"},
            {value: "接口管理", name: "接口管理"},
            {value: "定时任务", name: "定时任务"},
        ];
        //终端充值--充值流量
        var v_charge_flow = [
            {"充值流量": 0},
            {"1G": "1"},
            {"3G": "3"},
            {"5G": "5"},
            {"10G": "10"},
            {"15G": "15"},
            {"20G": "20"},
            {"无限量": "999"}
        ];
        //终端充值--充值日期
        var v_charge_date = [
            {"充值日期": 0},
            {"1天": 1},
            {"3天": 3},
            {"5天": 5},
            {"7天": 7},
            {"15天": 15},
            {"30天": 30},
            {"60天": 60},
            {"90天": 90},
            {"180天": 180},
            {"1年": 360},
            {"2年": 720},
            {"3年": 1080},
        ];

        //simcard批量操作可以修改的属性
        var card_param = [
            {k: "packageId", v: "套餐"},
            {k: "offPeriod", v: "账期日"},
            {k: "sustained", v: "账期持续时间"},
            {k: "status", v: "卡状态"},
            {k: "provinceCode", v: "省"},
            {k: "expiryDate", v: "有效期截卡时间"},
            {k: "usedVpn", v: "是否支持vpn"},
            {k: "softType", v: "是否是软卡"},
            {k: "openDate", v: "开卡日期"}
        ];

        _FUNC = {
            //获取项目根路径
            getContextPath: function (fullUrl) {
                if (fullUrl == null || fullUrl == '') {
                    fullUrl = window.location.href + '';
                }
                var arrUrl = fullUrl.split('/');
                return arrUrl[0] + '//' + arrUrl[2] + '/' + arrUrl[3];
            },
            //获取sim卡的状态信息
            getCardStatus: function (o) {
                return c_status[o];
            },
            //获取sim卡的使用状态信息
            getUsedStatus: function (o) {
                return u_status[o];
            },
            //获取副卡的状态信息
            getChannelcardStatus: function (o) {
                return c_channelcardStatus[o];
            },
            //获取颜色值
            getColor: function (o) {
                return v_colors[o];
            },
            //获取终端状态信息
            getTerminalStatus: function (o) {
                return t_status[o];
            },
            //获取终端版本状态信息
            getTVStatus: function (o) {
                return tv_status[o];
            },
            //获取指定卡类型状态颜色映射
            getReadyType: function (s) {
                return ready_type[s];
            },
            //获取取卡结果
            getResultCode: function (s) {
                return result_code[s];
            },
            //获取取卡结果
            getCpStatus: function (s) {
                return d_cpStatus[s];
            },
            //接收一个long类型的毫秒数，返回格式化的字符串
            getDate: function (o, pattern) {
                if (o && /^\d+$/.test(o)) {
                    pattern = pattern || 'yyyy-MM-dd';
                    return new Date(parseInt(o)).format(pattern);
                }
                return '';
            },
            //用作输入框日期的格式化
            formatInputDate: function (obj, value, pattern) {
                $(obj).val(this.getDate(value, pattern || 'yyyy-MM-dd'));
            },
            //给指定容器下的输入框自动填充数值
            autoFillData: function (container, data) {
                var _this = this;
                $(':input', $(container)).each(function (i, o) {
                    var name = this.name;
                    if (name && data[name] + '') {
                        $(this).val(data[name]);
                    }
                });
            },
            //暂时不启用这个函数，这个函数可以记录修改之前的参数
            autoFillData_new: function (container, data) {
                var _this = this;
                var prefix = 'kaChiLog_';
                if ($('#kaChiLogDiv', $(container)).length > 0) {
                    $('#kaChiLogDiv', $(container)).remove();
                }
                var ele = $('<div style="display: none" id="kaChiLogDiv"></div>');
                $(':input', $(container)).each(function (i, o) {
                    var name = this.name;
                    if (name && data[name] + '') {
                        $(this).val(data[name]);
                    }
                    ele.append('<input name="' + (prefix + this.name) + '" type="hidden" value="' + this.value + '">');
                });
                $(container).append(ele);
            },
            //给指定的select容器初始化数据，参数container：容器,data：数组数据,needNull：是否需要给一个空值，nullDesc：空值的描述，defaultVal：是否选中默认值
            initSelectOption: function (option) {
                if (!option.data) {
                    $(option.container).html('');
                    return;
                }
                option = $.extend({"value": "value", "name": "name", "nullDesc": "--请选择--"}, option);
                var html = '';
                if (option.needNull) html += '<option value="">' + option.nullDesc + '</option>';
                $.each(option.data, function (i, o) {
                    html += '<option value="' + o[option.value] + '" ' + (option.defaultVal && o[option.value] == option.defaultVal ? "selected" : "") + '>' + o[option.name] + '</option>';
                });
                if (option.append) {
                    $(option.container).append(html);
                } else {
                    $(option.container).html(html);
                }
                if (option.callback && typeof option.callback == 'function') {
                    option.callback();
                }
            },
            //给指定的select容器通过url返回的数组初始化数据
            initSelect: function (option) {
                option = $.extend({"needNull": true, "queryParam": {}, "append": false}, option);
                var _this = this;
                $.post(this.getContextPath() + '/' + option.url, option.queryParam, function (data) {
                    if (data && data.code == '200') {
                        option.data = data.data;
                        _this.initSelectOption(option);
                    } else {
                        $.messager.alert('提示', '操作失败', 'info');
                    }
                });
            },
            //给select容器当0：否1：是初始化
            initDic_noYes: function (obj) {
                this.initSelectOption({"container": obj, "data": dic_noYes, "append": true, "needNull": false});
            },
            //给日志select容器初始化
            initDic_bussinesstype: function (obj) {
                this.initSelectOption({container: obj, data: dic_bussinesstype, needNull: true, nullDesc: "全部"});
            },
            //限定输入框只能输入数字类型
            onlyNumber: function (obj) {
                $(obj).keyup(function () {
                    $(this).val($(this).val().replace(/\D/, ''));
                });
            },
            getMncName: function (o) {
                if (o && o.indexOf("460") == 0) {
                    return d_mnc[o.substr(3, 2)];
                }
                return o;
            },
            getBoxTxt: function (cardCount) {
                var html = '';
                for (var o in c_status) {
                    html += '<div style="float:left"><span class="boxdesc" style="background:' + c_status[o][1] + '"></span>&nbsp;'
                        + c_status[o][0] + '<br><span style="display:inline-block;margin-left:10px">（ ' + cardCount[o] + ' ）</span></div>';
                }
                return html;
            },
            searchParams: function searchParams(url) {
                var ret = {};
                var match;
                var plus = /\+/g;
                var reg = /([^\?&=]+)=([^&]*)/g;
                var decode = function (s) {
                    return decodeURIComponent(s.replace(plus, " "));
                };
                while (match = reg.exec(url)) ret[decode(match[1])] = decode(match[2]);
                return ret;
            },
            getFunctions: function () {
                var id = this.searchParams(location.href).id;
                if (id) {
                    var funs = [];
                    $.ajaxSetup({async: false});
                    $.post(this.getContextPath() + '/getFunctions', {id: id}, function (data) {
                        if (data && data.code == '200' && data.data) {

                            for (var i = 0; i < data.data.length; i++) {
                                var o = data.data[i];
                                funs.push({
                                    iconCls: o.iconCls,
                                    text: o.text,
                                    handler: o.funDesc
                                });
                                if (i < data.data.length - 1) funs.push('-');
                            }
                        }
                    });
                    $.ajaxSetup({async: true});
                    return funs;
                }
            },
            getDiffDate: function (date, n) {
                //间隔的毫秒= n * 24 * 60 * 60 * 1000
                var intMilliSec = n * 86400000;
                var resultDate = new Date(Date.parse(new Date(date)) + intMilliSec); //Date.parse()返回的是毫秒数
                return resultDate.format("yyyy-MM-dd") + ' 00:00:00';
            },
            //终端充值初始化type=flow/date
            initCharge: function (obj, type) {
                var _data = type == "flow" ? v_charge_flow : v_charge_date,
                    _html = '';
                $.each(_data, function (i, o) {
                    for (var i in o) {
                        _html += '<option value="' + o[i] + '">' + i + '</option>';
                    }
                });
                $(obj).html(_html);
            },
            //通过有效期截止时间获取终端剩余时间
            getRemainTime: function (date_str) {
                if (date_str) {
                    date_str = date_str.substr(0, 10);
                    var diff = Math.floor((new Date(date_str) - new Date()) / 1000 / 60 / 60 / 24);
                    return diff;
                }
                return "";
            }

        };
        return {fn: _FUNC};
    })(jQuery);
//解决在火狐上不兼容
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
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
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

$(function () {
    $('input,select', $('.table.readOnly')).attr({'readOnly': true});
    kcJs.fn.initDic_noYes($('.fill-noYes'));
    kcJs.fn.onlyNumber($('.onlyNumber'));
});