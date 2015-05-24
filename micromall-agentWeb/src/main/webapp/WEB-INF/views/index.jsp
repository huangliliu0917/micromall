<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/21
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>微商管理</title>
    <script type="text/javascript">
        function goMenu(uri) {
            window.location.href = uri;
        }
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all" style="padding:20px 40px 10px 40px">
        <span class="wz0" style="font-size:40px;">微商管理</span>

        <p style="clear:both; height:30px"></p>

    </div>
    <!---------------------/////////////////------------------------------------>
</div>
<!---------------------/////////////////------------------------------------>

<ul class="ull" onclick="goMenu('<c:url value="/agentList?customerId=${customerId}"/>')">
    <li class="png"><img src="<c:url value="/resources/images/user2.png" />" width="100%"></li>
    <li class="wenz1">代理商管理</li>
</ul>


<ul class="ull" onclick="goMenu('<c:url value="/userList?customerId=${customerId}"/>')">
    <li class="png"><img src="<c:url value="/resources/images/shoujihaoer.png" />" width="100%"></li>
    <li class="wenz1">我的通讯录管理</li>
</ul>

<p class="h10" style="clear:both"></p>

<ul class="ull" onclick="goMenu('<c:url value="/order/orderList?customerId=${customerId}"/>')">
    <li class="png"><img src="<c:url value="/resources/images/file-settings.png" />" width="100%"></li>
    <li class="wenz1">进出货管理</li>
</ul>


<ul class="ull">
    <li class="png"><img src="<c:url value="/resources/images/diamond.png" />" width="100%"></li>
    <li class="wenz1">微武器</li>
</ul>

<p class="h10" style="clear:both"></p>

<ul class="ull">
    <li class="png"><img src="<c:url value="/resources/images/connection-empty.png" />" width="100%"></li>
    <li class="wenz1">统计</li>
</ul>


<ul class="ull">
    <li class="png"><img src="<c:url value="/resources/images/settings.png" />" width="100%"></li>
    <li class="wenz1">权限管理</li>
</ul>


<p style="clear:both"></p>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>
<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <p class="h20"></p>

    <p style="text-align:center;">
        <a href="#"><span class="wj_i">关于长生鸟</span></a></p>
</div>

</div>
</body>
</html>

