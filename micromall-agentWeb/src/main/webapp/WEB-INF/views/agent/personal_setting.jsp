<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 16:05
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/prompt/jquery.prompt.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/prompt/prompt.css" />">
    <title>新增代理商</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var editAgentId = ${editAgentId};

        $(function () {
            agentHandler.init("<c:url value="/agentApi/" />")
        })
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/agentWeb/agent/agentWeb.agent.js" />"></script>
</head>

<body>
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all" style="padding:20px 40px 10px 40px">
        <span class="wz0" style="font-size:40px;">个人资料编辑</span>

        <p style="clear:both; height:30px"></p>

    </div>
    <!---------------------/////////////////------------------------------------>
    <p class="h10"></p>

</div>
<!---------------------/////////////////------------------------------------>

<div class="add_wei_shang_o bottom">

    <p><label>
        <input type="" id="agentAccount" disabled="disabled" name="mobile" value="${agentBean.agentAccount}" placeholder="代理人手机号"></label></p>

    <p><label>
        <input type="" id="name" name="mobile" value="${agentBean.name}" placeholder="代理人姓名"></label></p>

    <p>
        <label><input type="hidden" id="agentLevel" name="mobile" value="${agentBean.agentLevel.levelId}">${agentBean.agentLevel.levelName}</label>
    </p>

    <p><label>
        <input type="" id="agentArea" name="mobile" value="${agentBean.agentArea}" placeholder="覆盖地区"></label></p>

    <p><label>
        <input type="" id="agentChannel" name="mobile" value="${agentBean.agentChannel}" placeholder="销售渠道"></label></p>

    <p><label>
        <input type="" id="agentCardId" name="mobile" value="${agentBean.agentCardId}" placeholder="身份证"></label></p>

    <p><label>
        <input type="" id="agentQQ" name="mobile" value="${agentBean.agentQQ}" placeholder="QQ号码"></label></p>

    <p><label>
        <input type="" id="agentWeixin" name="mobile" value="${agentBean.agentWeixin}" placeholder="微信号"></label></p>

    <p style="border-bottom: 1px solid #5A5A5A;"><label>
        <input type="" id="agentAddr" name="mobile" value="${agentBean.agentAddr}" placeholder="收货地址"></label></p>


</div>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>

<p class="h10"></p>

<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <div class="add_wei">

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a href="javascript:agentHandler.saveAgent()" class="wsws_back button" id="btnSave">保存</a>
        </p>
    </div>

</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>
