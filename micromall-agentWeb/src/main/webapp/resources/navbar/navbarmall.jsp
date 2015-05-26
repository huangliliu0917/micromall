<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<link href="/resource/templatev2/common/index/indexv25/css/common.css?20150306" rel="stylesheet" type="text/css"/>--%>
<link rel="stylesheet" href="<c:url value="/resources/navbar/css/dhhang.css" />" type="text/css">
<link type="text/css" href="<c:url value="/resources/navbar/css/top.css" />" rel="stylesheet">
<script type="text/javascript">
    function navbarGo(url) {
        window.location.href = url + customerId;
    }
</script>
<!--底部导航-->
<div class="wl_nav" id="mnav-bottommenu">
    <a href="javascript:navbarGo('<c:url value="/index?customerId=" />')" class="nav_more">首页</a>
    <a href="javascript:navbarGo('<c:url value="/agentList?customerId="/>')" class="nav_index">代理商</a>
    <a href="javascript:navbarGo('<c:url value="/userList?customerId="/>')" class="nav_me">通讯录</a>
    <a href="javascript:navbarGo('<c:url value="/order/orderList?customerId="/>')" class="nav_kefu">进出货</a>

    <p style="clear: both"></p>
</div>
<style type="text/css">
    .wl_nav a {
        text-decoration: none;
    }

    .bj {
        min-width: 23.5%;
        max-width: 23.5%;
        margin: 0 auto;
        height: auto;
        background-color: Transparent;
    }

    body {
        font-family: Helvetica, STHeiti STXihei, Microsoft JhengHei, Microsoft YaHei, Arial;
    }

    #header_sub {
        display: none;
    }
</style>
<!--end底部导航-->
<script type="text/javascript">
    function setDefImg(obj) {
        obj.src = 'images/dkh.png';
        obj.onerror = null;
    }
</script>
