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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <title>微商管理</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/logout" />";
        function goMenu(uri) {
            window.location.href = uri;
        }

        function logOut() {
            loading.show("正在退出");
            var op = {
                type: "post",
                url: ajaxUrl,
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
        $(function () {
            var orderNum = ${unShipCount};
            if (orderNum > 0) {
                $(".tipCircle").html(orderNum);
                $(".tipCircle").show();
            } else {
                $(".tipCircle").hide();
            }
        })
    </script>
    <style type="text/css">
        #orderUl .tipCircle {
            position: absolute;
            border-radius: 50px;
            background-color: crimson;
            height: 20px;
            width: 20px;
            color: #fff;
            text-align: center;
            right: -7px;
            margin-top: -10px;
        }

        .ull {
            border: 1px solid #D7D7D7;
            width: 43%;
            margin: 10px;
            float: left;
            border-radius: 20px;
        }
    </style>
</head>

<body style="background-color:#fff; max-width:640px; margin:0 auto">
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all"
    ">
    <span class="wz0" style="font-size:40px;">微商管理</span>

    <p style="clear:both; height:30px"></p>

</div>
<!---------------------/////////////////------------------------------------>
</div>
<!---------------------/////////////////------------------------------------>
<div>
    <ul class="ull" onclick="goMenu('<c:url value="/agentList?customerId=${customerId}"/>')">
        <li class="png"><img src="<c:url value="/resources/images/user2.png" />" width="100%"></li>
        <li class="wenz1">代理商管理</li>
    </ul>


    <ul class="ull" onclick="goMenu('<c:url value="/userList?customerId=${customerId}"/>')">
        <li class="png"><img src="<c:url value="/resources/images/shoujihaoer.png" />" width="100%"></li>
        <li class="wenz1">我的通讯录管理</li>
    </ul>

    <p class="h10" style="clear:both"></p>

    <ul id="orderUl" style="position: relative" class="ull" onclick="goMenu('<c:url value="/order/orderList?customerId=${customerId}"/>')">
        <li class="tipCircle" style="display: none;"></li>
        <li class="png"><img src="<c:url value="/resources/images/file-settings.png" />" width="100%"></li>
        <li class="wenz1">进出货管理</li>

    </ul>


    <ul class="ull" onclick="goMenu('<c:url value="/weapon/weaponList?customerId=${customerId}"/>')">
        <li class="png"><img src="<c:url value="/resources/images/diamond.png" />" width="100%"></li>
        <li class="wenz1">微武器</li>
    </ul>

    <p class="h10" style="clear:both"></p>

    <ul class="ull">
        <li class="png"><img src="<c:url value="/resources/images/connection-empty.png" />" width="100%"></li>
        <li class="wenz1">统计</li>
    </ul>


    <ul class="ull" onclick="goMenu('<c:url value="/usercenter?customerId=${customerId}"/>')">
        <li class="png"><img src="<c:url value="/resources/images/settings.png" />" width="100%"></li>
        <li class="wenz1">个人中心</li>
    </ul>
</div>

<p style="clear:both"></p>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>
<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <p class="h20"></p>

    <p style="text-align:center;">
        <a href="#"><span class="wj_i">关于长生鸟</span></a> |
        <a href="javascript:logOut()"><span class="wj_i">退出登录</span></a>
    </p>
</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>

