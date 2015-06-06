<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/21
  Time: 20:09
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery.utils.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jQuery.md5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/artDialog/dialog-min.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/artDialog/ui-dialog.css" />">

    <title>微商登录</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/checkLogin" />";
        var returnUrl = "${returnUrl}";

        $(function () {
            $("#loginBtn").click(function () {
                var account = $.trim($("#account").val());
                var password = $.trim($("#password").val());
                if (account.length == 0) {
                    alert("请输入登录名");
                    return;
                }
                if (password.length == 0) {
                    alert("请输入密码");
                    return;
                }
                loading.show("正在登录");
                J.GetJsonRespons(ajaxUrl, {
                    account: account,
                    password: $.md5(password),
                    customerId: customerId
                }, function (json) {
                    if (json.result == 1) {
                        window.location.href = returnUrl == "" ? "<c:url value="/index?customerId=${customerId}" />" : returnUrl;
                    } else {
                        loading.close();
                        alert("登录失败");
                    }
                }, function () {
                }, J.PostMethod);
            })
        })

        var certificates = function () {
            var ctfDialog = dialog({
                title: "代理查询",
                content: $("#certificates_template").html(),
                okValue: "确定",
                ok: function () {
                    var agentAccount = $.trim($("#agentAccount").val());
                    if (agentAccount.length == 0) {
                        alert("请输入代理商手机号");
                        return false;
                    }
                    if (!J.IsMobile(agentAccount)) {
                        alert("手机号码格式不正确");
                        return false;
                    }
                    window.location.href = '<c:url value="/certificates?customerId=${customerId}" />' + '&agentAccount=' + agentAccount;
                },
                cancelValue: '取消',
                cancel: function () {
                }
            });
            ctfDialog.show();
        }
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all" style="padding:20px 40px 10px 40px">
        <span class="wz0"><img src="${uploadResourceServer.resourceUri(configBean.logo)}" width="130px" height="auto"></span>

        <p style="clear:both; height:30px"></p>

        <span class="wz0"></span>

        <p style="clear:both"></p>
        <span class="wz1">${configBean.title}</span>

        <p style="clear:both"></p>

    </div>
    <!---------------------/////////////////------------------------------------>
    <p class="h10"></p>

</div>
<!---------------------/////////////////------------------------------------>

<div class="add_wei_shang">

    <p><label><span class="mm">账号</span>
        <input type="tel" id="account" name="account" value="" placeholder=""></label></p>

    <p><label><span class="mm">密码</span>
        <input type="password" id="password" name="password" value="" placeholder=""></label></p>

</div>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>

<p class="h10"></p>

<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <div class="add_wei">

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a class="wsws_back button" id="loginBtn">登陆</a>
        </p>

        <p style="height: 10px;"></p>

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a class="wsws_back button" href="<c:url value="/applyAgent?customerId=${customerId}" />">申请代理商</a>
        </p>
    </div>
    <div>

        <p class="h20"></p>
        <a href="javascript:certificates()"><p class="dl">代理查询</p></a>
        <%--<a href="#"><p class="wj">关于${configBean.title}</p></a>--%>
    </div>

</div>
</body>
</html>

<script type="text/html" id="certificates_template">
    代理商手机：<input style="color: #000;border: 1px solid #ddd;height: 27px;width: 200px;" id="agentAccount" type="tel"/>
</script>

