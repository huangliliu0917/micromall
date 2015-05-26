<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/23
  Time: 13:46
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/prompt/jquery.prompt.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/prompt/prompt.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <title>我的通讯录</title>
    <script type="text/javascript">
        var userId = ${userId};
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/agentApi/" />"
        var returnUrl = "<c:url value="/userList?customerId=${customerId}" />";
    </script>

    <script type="text/javascript" src="<c:url value="/resources/scripts/agentWeb/agent/agentWeb.user.js" />"></script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all" style="padding:20px 40px 10px 40px">
        <span class="wz0" style="font-size:40px;">新增通讯录用户</span>

        <p style="clear:both; height:30px"></p>

    </div>
    <!---------------------/////////////////------------------------------------>
    <p class="h10"></p>

</div>
<!---------------------/////////////////------------------------------------>

<div class="add_wei_shang_i bottom">

    <p><label>
        <input type="tel" id="userMobile" name="mobile" value="${userBean.userMobile}" placeholder="联系人手机"></label></p>

    <p><label>
        <input type="text" id="userName" name="mobile" value="${userBean.userName}" placeholder="姓名"></label></p>

    <p><label>
        <input type="text" id="userQQ" name="mobile" value="${userBean.userQQ}" placeholder="QQ"></label></p>

    <p><label>
        <input type="text" id="userWeixin" name="mobile" value="${userBean.userWeixin}" placeholder="微信"></label></p>

    <p><label>
        <input type="text" id="userAddr" name="mobile" value="${userBean.userAddr}" placeholder="收获地址"></label></p>

</div>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>

<p class="h10"></p>

<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <div class="add_wei">

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a class="wsws_back button" href="javascript:userHandler.saveUser()">保存</a>
        </p>
    </div>

</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>

