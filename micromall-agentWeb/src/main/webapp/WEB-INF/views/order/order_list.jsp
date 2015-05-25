<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/24
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>进出货管理</title>
    <script type="text/javascript">
        $(function () {
            $("#searchBtn").click(function () {
                $("#searchForm").submit();
            })
        })
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<!---------------------/////////////////------------------------------------>

<div style="background-color:#545454;height:44px;">
    <p style="color:#fff; float:left;margin-top: 10px;margin-left: 10px;" onclick="javascript:window.location.href='<c:url value="/order/createOrder?customerId=${customerId}"/>'">我要进货</p>

    <form id="searchForm" action="<c:url value="/order/orderList" />">
        <input type="hidden" value="${pageIndex}" id="pageIndex" name="pageIndex"/>
        <input type="hidden" value="${orderType}" id="orderType" name="orderType">
        <input type="hidden" value="${customerId}" id="customerId" name="customerId">

        <p style=" float:right;margin-top:5px;margin-right: 10px;">
            <input style="height: 32px;background-color: #545454;border: solid 1px #7E7E7E;color: #fff;padding-left: 7px;width: 123px;" id="orderId" name="orderId" type="" value="${orderId}" placeholder="订单号">
            <span id="searchBtn"><img class="imgg" src="<c:url value="/resources/images/untitled10.png" />" width="20px"></span>
        </p>
    </form>
</div>
<p style="clear:both;"></p>

<!---------------------/////////////////------------------------------------>
<div class="tabBoxs">
<span class="hdi" style="margin:0 auto;">
<a href="<c:url value="/order/orderList?customerId=${customerId}"/>" class="${orderType==0?"on":""}">全部</a>
<a href="<c:url value="/order/orderList?customerId=${customerId}&orderType=1"/>" class="${orderType==1?"on":""}" }>我的进货</a>
<a href="<c:url value="/order/orderList?customerId=${customerId}&orderType=2"/>" class="${orderType==2?"on":""}">我的出货</a>
</span>

    <p style="clear:both;"></p>
</div>
<!---------------------/////////////////------------------------------------>

<div id="leftTabBoxs" class="tabBoxs">
    <div class="bdd">
        <ul>
            <c:forEach items="${pageInfo.getContent()}" var="orderBean">
                <li class="tttt">
                    <div class="DDH">单号：${orderBean.orderId}
                        <c:if test="${orderBean.orderStatus==1}">
                            <span style="float:right; color:#FF4700">已发货</span>
                        </c:if>
                        <c:if test="${orderBean.orderStatus==0 && (orderBean.sendId>0 or orderBean.realShipId==agentId)}">
                            <a href="<c:url value="/order/confirmOrder?customerId=${customerId}&orderId=${orderBean.orderId}" />"><span style="float: right;color: #fff;padding: 0px 12px;background-color:#FF4700;">发货</span></a>
                        </c:if>
                        <c:if test="${orderBean.orderStatus==0&&orderBean.sendId==0 && orderBean.realShipId!=agentId}">
                            <span style="float:right; color:#FF4700">未发货</span>
                        </c:if>
                    </div>
                    <p style="height:5px"></p>

                    <p style="height:4px; border-top:1px dotted #ddd"></p>

                    <p style="height:5px"></p>

                    <div class="conn">
                        <div class="pici">
                            <a href="#"><img src="<c:url value="${uploadResourceServer.resourceUri(orderBean.good.goodImg)}" />"/></a>
                        </div>

                        <p style="color:#000; margin-bottom:2px">${orderBean.orderName}</p>

                        <p style="height:5px"></p>

                        <p>收件人：${orderBean.shipName}</p>

                        <p style="height:5px"></p>

                        <p>收件人手机：${orderBean.shipMobile}</p>

                        <p style="height:5px"></p>

                        <p><span>物流：${orderBean.shipInfo}</span></p>
                    </div>
                    <p style="height:20px"></p>

                    <p style="height:4px; border-top:1px dotted #ddd; clear:both"></p>

                    <div class="DDDH"><span style="float:left; color:#999"><fmt:formatDate value="${orderBean.addTime}" type="both"/></span><span style="float:right">数量：${orderBean.proNum}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        金额：￥${orderBean.totalPrice}</span></div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>

