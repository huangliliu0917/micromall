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
    <%--<span class="tipCircle" style="display: none;"></span>--%>

    <p style="clear: both"></p>
</div>
<style type="text/css">
    .wl_nav a {
        text-decoration: none;
    }

    .wl_nav {
        overflow: hidden;
        height: 49px;
        border-top: 1px solid #eee;
        position: fixed;
        z-index: 900;
        /* width: 100%; */
        bottom: 0;
        left: 0;
        max-width: 640px;
        min-width: 320px;
        margin: 0 auto;
        left: 0;
        right: 0;
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

    #mnav-bottommenu .tipCircle {
        position: absolute;
        border-radius: 50px;
        background-color: crimson;
        height: 20px;
        width: 20px;
        color: #fff;
        text-align: center;
        right: 13px;
        margin-top: 5px;
    }
</style>
<!--end底部导航-->
<script type="text/javascript">
    function setDefImg(obj) {
        obj.src = 'images/dkh.png';
        obj.onerror = null;
    }
</script>
