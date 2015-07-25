<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/3
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/weishang.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/artDialog/dialog-min.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/artDialog/ui-dialog.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery.utils.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jQuery.md5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <title>个人中心</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/agentApi/" />";

        function alert(content) {
            dialog({
                title: '提示',
                content: content,
                okValue: "确定",
                ok: function () {
                }
            }).show();
        }
        var updatePass = function () {
            var updatePassDialog = dialog({
                title: '修改密码',
                content: $("#update_pass_temp").html(),
                okValue: "确定",
                ok: function () {
                    var orignalPass = $.trim($("#orignalPass").val());
                    var newPass = $.trim($("#newPass").val());
                    var confirmPass = $.trim($("#confirmPass").val());

                    if (orignalPass.length == 0) {
                        alert("请输入旧密码");
                        return false;
                    }

                    if (newPass.length == 0) {
                        alert("请输入新密码");
                        return false;
                    }
                    if (newPass != confirmPass) {
                        alert("两次密码输入不相同");
                        return false;
                    }

                    var requestData = {
                        orignalPass: $.md5(orignalPass),
                        newPass: $.md5(newPass),
                        customerId: customerId
                    };
                    loading.show("正在提交");
                    J.GetJsonRespons(ajaxUrl + "updatePass", requestData, function (json) {
                        loading.close();
                        if (json.result == 1) {
                            alert("修改成功");
                            window.location.reload();
                        } else {
                            alert("修改失败，请检查旧密码输入是否正确");
                        }
                    }, function () {
                    }, J.PostMethod);
                },
                cancelValue: '取消',
                cancel: function () {
                }
            });

            updatePassDialog.show();
        }

        var updateAddr = function () {
            var updateAddrDialog = dialog({
                title: "修改收货地址",
                content: $("#update_addr_temp").html(),
                okValue: "确定",
                ok: function () {
                    var newAddr = $.trim($("#newAddr").val());
                    if (newAddr.length == 0) {
                        alert("请输入新的收货地址");
                        return false;
                    }
                    var requestData = {
                        newAddr: newAddr,
                        customerId: customerId
                    }
                    loading.show("正在提交");
                    J.GetJsonRespons(ajaxUrl + "updateAddr", requestData, function (json) {
                        loading.close();
                        if (json.result == 1) {
                            alert("保存成功");
                            updateAddrDialog.close();
                        } else {
                            alert("保存失败");
                        }
                    }, function () {
                    }, J.PostMethod);
                },
                cancelValue: '取消',
                cancel: function () {
                }
            });
            updateAddrDialog.show();
        }
        var logoutUri = "<c:url value="/logout" />";
        function logOut() {
            loading.show("正在退出");
            var op = {
                type: "post",
                url: logoutUri,
                data: {
                    customerId: customerId
                },
                dataType: 'json',
                success: function (json) {
                    loading.close();
                    if (json.result == 1) {
                        window.location.reload();
                    }
                }
            };
            $.ajax(op);
        }
    </script>
    <style>
        .name {
            font-size: 40px;
            text-align: center;
            color: #fff;
        }

        .level {
            font-size: 14px;
            text-align: center;
            color: #fff;
            background-color: #FFAE00;
            width: 79px;
            margin: 0 auto;
            border-radius: 10px;
        }

        .boxrgba {
            background: rgba(0, 0, 0, 0.5);
            padding: 7px;
            color: #fff;
            height: 20px;
        }

        .up {
            float: left;
            margin-left: 10px;
        }

        .down {
            float: right;
            margin-right: 10px;
        }

        .my_menu_btn2:hover {
            background-color: #f1f1f1;
        }

        .my_menu_btn2 {
            text-align: left;
            height: 45px;
            line-height: 3em;
            font-size: 1.1em;
            display: inline-block;
            background: #fff;
            width: 100%;
            border-bottom: solid 1px #ddd;
        }

        .fontwp {
            margin-left: 10px;
        }

        .upInput {
            color: #000;
            border: 1px solid #ddd;
            height: 27px;
            width: 200px;
        }
    </style>
</head>

<body>
<div>

    <div style="background:url(<c:url value="/resources/images/ffdddd.jpg"/>) no-repeat;">
        <p style=" height:20px;"></p>

        <p class="name">${agentBean.name}</p>

        <p class="level">${agentBean.agentLevel.levelName}</p>

        <p style=" height:20px;"></p>

        <p class="boxrgba">
            <span class="up">下级代理：${underAgentNum}</span>
            <span class="down">上级代理：${superAgent==null?"总代":superAgent.name}</span>
        </p>

        <p style="clear:both;"></p>
    </div>


</div>
<!---------------------/////////////////------------------------------------>

<div class="add_wei_shang_ii bottom">


    <div class="add_wei_shang_ii bottom">
        <div onclick="updatePass()" class="my_menu_btn2">
            <a href="#"><span class="fontwp">修改密码</span><span style="float: right; padding-right: 10px; font-size:18pt;">＞</span></a>
        </div>

        <div class="my_menu_btn2">
            <a href="<c:url value="/personalSetting?customerId=${customerId}" />"><span class="fontwp">查看我的资料</span><span style="float: right; padding-right: 10px; font-size:18pt;">＞</span></a>
        </div>

        <div onclick="updateAddr()" class="my_menu_btn2">
            <a href="#"><span class="fontwp">修改我的地址</span><span style="float: right; padding-right: 10px; font-size:18pt;">＞</span></a>
        </div>
        <div class="my_menu_btn2">
            <a href="<c:url value="/certificates?customerId=${customerId}&agentAccount=${agentBean.agentAccount}" />"><span class="fontwp">查看我的代理证书</span><span style="float: right; padding-right: 10px; font-size:18pt;">＞</span></a>
        </div>
        <div class="my_menu_btn2">
            <a href="javascript:logOut()"><span class="fontwp">退出登录</span><span style="float: right; padding-right: 10px; font-size:18pt;">＞</span></a>
        </div>
    </div>
</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>
<script type="text/html" id="update_pass_temp">
    <table style="border:0px;">
        <tr style="height:45px;">
            <td>旧密码：</td>
            <td><input class="upInput" type="password" id="orignalPass"/></td>
        </tr>
        <tr style="height:45px;">
            <td>新密码：</td>
            <td><input class="upInput" type="password" id="newPass"/></td>
        </tr>
        <tr style="height:45px;">
            <td>确认新密码：</td>
            <td><input class="upInput" type="password" id="confirmPass"/></td>
        </tr>
    </table>
</script>
<script type="text/html" id="update_addr_temp">
    收货地址：<input type="text" class="upInput" id="newAddr"/>
</script>

