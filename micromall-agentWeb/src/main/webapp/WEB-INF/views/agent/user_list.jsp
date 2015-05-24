<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/22
  Time: 21:56
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
        var ajaxUrl = "<c:url value="/agentApi/" />";
        var customerId = ${customerId};
        function showMore(userId) {
            $("#selUserId").val(userId);
            $("#more_dialog").show();
        }
        function hideMore() {
            $("#selUserId").val("");
            $("#more_dialog").hide();
        }

        function goUri(uri) {
            window.location.href = uri + $("#selUserId").val();
        }

        function deleteUser() {
            userHandler.deleteUser($("#selUserId").val());
        }
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/agentWeb/agent/agentWeb.user.js" />"></script>
</head>
<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<!---------------------/////////////////------------------------------------>

<div style="background-color:#545454;height:44px;">
    <p style="color:#fff; float:left;margin-top: 10px;margin-left: 10px;" onclick="javascript:window.location.href='<c:url value="/userEdit?customerId=${customerId}"/>'">新增通讯录</p>

    <p style=" float:right;margin-top:5px;margin-right: 10px;">
        <input style="height: 32px;background-color: #545454;border: solid 1px #7E7E7E;color: #fff;padding-left: 7px;width: 123px;" type="" id="mobile" name="mobile" value="" placeholder="搜索">
        <span id="searchBtn"><img class="imgg" src="<c:url value="/resources/images/untitled10.png" />" width="20px"></span>
    </p>
</div>

<p style="clear:both;"></p>


<!---------------------/////////////////------------------------------------>

<div id="leftTabBoxs" class="tabBoxs">
    <div class="bdd">
        <ul>
            <c:forEach items="${pageInfo.getContent()}" var="userBean">
                <li class="tttt">
                    <div class="conn">
                        <div class="pic">
                            <a href="javascript:showMore(${userBean.userId})"><img src="<c:url value="/resources/images/wyNewPic.png" />"/></a>
                        </div>
                        <p style="color:#000; margin-bottom:2px;line-height: 54px;font-size: 20px;">${userBean.userMobile}</p>

                        <p style="color:#000;line-height: 20px;">姓名：${userBean.userName}</p>

                        <p style="color:#000;line-height: 20px;">地址：${userBean.userAddr}</p>
                    </div>
                    <p style="height:20px"></p>

                    <p style="height:4px; border-top:1px dotted #ddd; clear:both"></p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


<!---------------------/////////弹窗////////------------------------------------>

<div id="more_dialog" style="background-color: #fff;position: fixed;bottom: 0;left: 0;right: 0;margin: auto;display:none;">
    <input type="hidden" id="selUserId"/>

    <p style="color: #A7A7A7;font-size: 30px;float: right;margin-right: 10px;">×</p>

    <p style="clear:both"></p>

    <div class="ws_wrap">
        <div class="add_wei">
            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a class="wsws_back button" href="javascript:goUri('<c:url value="/order/createOrder?customerId=${customerId}&sendId=" />')">发起订单</a>
            </p>

            <p style="height:20px"></p>

            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a class="wsws_back button" href="javascript:goUri('<c:url value="/userEdit?customerId=${customerId}&userId=" />')">修改信息</a>
            </p>

            <p style="height:20px"></p>

            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a class="wsws_back button" href="javascript:deleteUser()">删除</a>
            </p>
        </div>
    </div>
</div>
<!---------------------/////////弹窗end////////------------------------------------>
</body>
</html>

