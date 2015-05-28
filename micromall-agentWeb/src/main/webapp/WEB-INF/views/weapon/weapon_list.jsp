<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/28
  Time: 10:36
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
    <title>微武器</title>
    <script type="text/javascript">
        var customerId =  ${customerId};
    </script>
    <style>
        .hp {
            width: 90%;
            margin: 0 auto;
            padding: 10px;
            text-overflow: ellipsis;
            text-align: center;
            white-space: nowrap;
            overflow: hidden;
            display: inline-block;
        }

        .wpbuttons {
            border: solid 1px #ddd;
            padding: 4px 10px;
            background-color: #ddd;
            float: right;
            margin-right: 10px;
            margin-top: -43px;
        }
    </style>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div>
    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="tabBoxs">
            <span class="hd" style="position:fixed; top:0px; z-index:9999; left:0px; right:0px; margin:0 auto;background-color: #5A5A5A;">
             <a href="<c:url value="/weapon/weaponList?customerId=${customerId}" />" class="${weaponType==0?"on":""}">图文库</a>
             <a href="<c:url value="/weapon/weaponList?weaponType=1&customerId=${customerId}" />" class="${weaponType==1?"on":""}">软文库</a>
            </span>

        <p style="clear:both; height:10px"></p>
    </div>
    <!---------------------/////////////////------------------------------------>
</div>
<!---------------------/////////////////------------------------------------>
<c:choose>
    <c:when test="${weaponType==0}">
        <c:forEach items="${pageInfo.getContent()}" var="weaponBean">
            <div class="boxx">
                <p class="hp">
                        ${weaponBean.weaponContent}
                </p>

                <div style="text-align:center">
                    <c:forEach items="${weaponBean.imgList}" var="img">
                        <p class="pngii"><img src="${uploadResourceServer.resourceUri(img)}" width="80px" height="80px"></p>
                    </c:forEach>
                </div>
                <p style="clear:both; height:5px"></p>
                <a class="wpbuttons" href="#"><span>查看</span></a>
            </div>
            <p style="clear:both; height:5px"></p>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach items="${pageInfo.getContent()}" var="weaponBean">
            <div class="boxxr">
                <p class="pngi" style="margin: 16px -43px 6px 16px;"><img src="${uploadResourceServer.resourceUri(weaponBean.weaponImgs)}" width="75px" height="75px"></p>

                <p style="width:68%;margin: 0 auto;padding: 9px 1px;text-overflow: ellipsis;overflow: hidden;display: inline-block;height: 56px;">
                        ${weaponBean.weaponContent}
                </p>
                <a class="wpbuttons" style="margin-top: 62px;" href="#"><span>查看</span></a>

                <p style="clear:both; height:5px"></p>

            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>
