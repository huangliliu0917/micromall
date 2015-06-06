<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/5
  Time: 13:48
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

    <title>代理查询</title>
</head>

<body style=" background:url(<c:url value="/resources/images/1_02.jpg"/>) repeat-y; background-size:100%">


<c:choose>
    <c:when test="${agentBean==null}">
        <div style="padding:0px 26px;">
            <div>

                <!---------------------/////////////////------------------------------------>
                <div class="all" style="padding:20px 40px 10px 40px">
                    <p style="clear:both; height:30px"></p>

                    <p style="height:20px"></p>

                    <P style="font-size:30px;color:#929292;line-height: 35px;">未搜索到授权证书</P>

                    <p style="height:20px"></p>

                    <P style="color:#929292;">SORRY</P>

                    <P style="color:#929292;">NO SEARCH TO YOUR CERTIFICATE OF AUTHORIZATION</P>

                    <p style="clear:both"></p>

                </div>
                <p class="h10"></p>

            </div>
        </div>
    </c:when>
    <c:otherwise>
        <img src="<c:url value="/resources/images/1_01.jpg" />" width="100%">

        <div style="padding:0px 26px;">
            <div>

                <!---------------------/////////////////------------------------------------>
                <div class="all" style="padding:20px 40px 10px 40px">
                    <a href="#"><span class="wz0"><img src="<c:url value="${uploadResourceServer.resourceUri(configBean.logo)}" />" width="100px" height="100px"></span></a>

                    <p style="clear:both; height:30px"></p>

                    <P style="font-size:40px" class="wz1">授权证书</P>

                    <p style="height:20px"></p>

                    <P>AUTHORIZED&nbsp;&nbsp;CERTIFICATES</P>

                    <p style="clear:both"></p>

                </div>
                <!---------------------/////////////////------------------------------------>
                <p class="h10"></p>

            </div>
            <!---------------------/////////////////------------------------------------>

            <div class="add_wei_shangwp">

                <p><label><span class="mmm">兹授权</span>
                    <input type="" readonly="readonly" value="${agentBean.name}" placeholder=""></label><span class="mmmm">为</span></p>

            </div>
            <p class="fontone"><input class="hotinput" type="" readonly="readonly" value="${configBean.title}" placeholder=""></p>

            <p class="fonttwo">公司O2O微商渠道的指定代理商，授权期间享受我公司品牌权益，同时遵守公司各项制度。请广大消费者放心购买，感谢您对本公司产品的信赖和支持。</p>

            <div class="add_wei_shangwpi">
                <p><label><span class="mmm">代理商编码:</span>
                    <input type="" readonly="readonly" value="${agentBean.authorizationCode}" placeholder=""></label></p>
            </div>
            <div class="add_wei_shangwpi">
                <p><label><span class="mmm">授权日期:</span>
                    <input type="" value="${agentBean.addTime}" placeholder=""></label></p>
            </div>
            <!---------------------/////////////////------------------------------------>
            <p style="clear:both; height:30px;"></p>

            <P class="wz1 fontthree">特此授权</P>

            <p style="clear:both; height:20px;"></p>

            <p class="fontfour"><input class="hotinputi" type="" value="${configBean.title}" placeholder="杭州火图科技有限公司"></p>

            <p class="h20"></p>

            <p class="h10"></p>
        </div>
        <img src="<c:url value="/resources/images/1_04.jpg" />" width="100%">
    </c:otherwise>
</c:choose>

</body>
</html>
