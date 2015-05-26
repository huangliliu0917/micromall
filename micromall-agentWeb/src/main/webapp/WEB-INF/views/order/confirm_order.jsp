<%@ page import="com.micromall.datacenter.utils.StringUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/24
  Time: 20:03
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/prompt/jquery.prompt.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/prompt/prompt.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <title>确认发货</title>
    <style type="text/css">
        .tabBoxs .bdd .tttt .conn p {
            line-height: 23px;
        }
    </style>
    <script type="text/javascript">
        var orderId = "${orderBean.orderId}";
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/orderApi/" />";
        var orderListUrl = "<c:url value="/order/orderList?customerId=${customerId}" />";
        function confirmShip() {
            var index = true;
            var proCodes = "";
            var $proCodes = $(".proCodes");
            $proCodes.each(function (o, item) {
                var code = $.trim($(item).val());
                if (code.length == 0) {
                    index = false;
                    SimplePrompt.showPrompt("还有货号没有填写");
                    return false;
                }
                if (o == $proCodes.length - 1) {
                    proCodes += $(item).val();
                } else {
                    proCodes += $(item).val() + ",";
                }
            });
            if (!index) {
                return;
            }
            var shipInfo = $.trim($("#shipInfo").val());
            if (shipInfo.length == 0) {
                SimplePrompt.showPrompt("请输入物流信息");
                return;
            }
            loading.show("正在提交");
            var requestData = {
                customerId: customerId,
                orderId: orderId,
                proCodes: proCodes,
                shipInfo: shipInfo
            }

            J.GetJsonRespons(ajaxUrl + "confirmShip", requestData, function (json) {
                loading.close();
                if (json.result == 1) {
                    SimplePrompt.showPromptWithFunc("确认发货成功", function () {
                        window.location.href = orderListUrl;
                    });
                } else {
                    SimplePrompt.showPrompt("确认发货失败");
                }
            }, function () {
            }, J.PostMethod);
        }

        function transferOrder() {
            loading.show("正在转交");
            J.GetJsonRespons(ajaxUrl + "transferOrder", {
                customerId: customerId,
                orderId: orderId
            }, function (json) {
                loading.close();
                if (json.result == 1) {
                    SimplePrompt.showPromptWithFunc("转交成功", function () {
                        window.location.href = orderListUrl;
                    });
                } else {
                    SimplePrompt.showPrompt("转交失败");
                }
            }, function () {
            }, J.PostMethod);
        }
        $(function () {
            $("#addProCode").click(function () {
                var proHtml = $("#proCode_template").html();
                var length = $(".proCode").length;
                proHtml = proHtml.replace(/{id}/g, length + 1);
                $("#proCodePanel").append(proHtml);
            });
        })

        function removePro(id) {
            $("#code" + id).remove();
        }
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<!---------------------/////////////////------------------------------------>

<div id="leftTabBoxs" class="tabBoxs">
    <div class="bdd">
        <ul>
            <li class="tttt">
                <div class="DDH">单号：${orderBean.orderId}</div>
                <p style="height:5px"></p>

                <p style="height:4px; border-top:1px dotted #ddd"></p>

                <p style="height:5px"></p>

                <div class="conn">
                    <div class="pici">
                        <a href="#"><img src="<c:url value="${uploadResourceServer.resourceUri(orderBean.good.goodImg)}" />"/></a>
                    </div>
                    <p style="height:5px"></p>

                    <p style="color:#000; margin-bottom:2px">${orderBean.orderName}</p>

                    <p style="color:#000;">数量：${orderBean.proNum}</p>

                    <p style="height:4px; border-top:1px dotted #ddd; clear:both"></p>

                    <p style="height:5px"></p>

                    <p style="color:#000; margin-bottom:2px">姓名：${orderBean.shipName}</p>

                    <p style="color:#000;">手机：${orderBean.shipMobile}</p>

                    <p style="color:#000;">收货地址：${orderBean.shipAddr}</p>
                </div>
                <p style="height:20px"></p>

                <p style="height:4px; border-top:1px dotted #ddd; clear:both"></p>

                <div class="DDDH"><span style="float:left; color:#999"><fmt:formatDate value="${orderBean.addTime}" type="both"/></span><span style="float:right">金额：￥${orderBean.totalPrice}</span></div>
            </li>
            <li class="tttt">
                <p style="height:5px"></p>

                <div class="conn">
                    <p style="height:5px"></p>

                    <div class="DDH">
                        <span style="float: left">请输入货号</span>
                        <a href="#" id="addProCode" style="float: right"><span><img style="margin-top: -5px;float:right" src="<c:url value="/resources/images/jia.png"/>" width="27px"></span></a>
                    </div>
                    <div style="float: left;width: 90%" id="proCodePanel">
                        <p>
                            <label>
                                <input class="proCodes" style="padding: 10px 0px;margin-top: 10px;border: solid 1px #ddd;color: #000;width: 80%;" type=""/>
                            </label>

                        </p>
                    </div>

                    <p style="height:10px;clear:both"></p>

                    <div class="DDH">请输入物流信息</div>
                    <p>
                        <label>
                            <input style="padding:5px 15px 70px 0px;margin: 10px 0px;border: solid 1px #ddd;color: #000; width:95%" type="" id="shipInfo" value="" placeholder="物流名称：xxx；单号：xxxxxxxxx">
                        </label>
                    </p>
                </div>
            </li>
        </ul>
    </div>
</div>
<!---------------------/////////////////------------------------------------>
<p style="height:10px;clear:both"></p>

<div class="add_wei">
    <c:if test="${orderBean.sendId>0}">
        <p class="command" style="background-color:transparent;padding: 0px 20%;">
            <a class="wsws_back button" href="javascript:confirmShip()">确认发货</a>
        </p>
    </c:if>

    <c:if test="${orderBean.sendId==0}">
        <p class="command" style="background-color:transparent;width:45%; float: left;">
            <a class="wsws_back button" href="javascript:confirmShip()">确认发货</a>
        </p>

        <p class="command" style="background-color:transparent; ;width:45%; float: right;">
            <a class="wsws_back button" href="javascript:transferOrder()">给上级代理</a>
        </p>
    </c:if>
</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>

<script type="text/html" id="proCode_template">
    <p style="float: left;width: 90%" id="code{id}">
        <label>
            <input class="proCodes" style="padding: 10px 0px;margin-top: 10px;border: solid 1px #ddd;color: #000;width: 89%;" type=""/>
        </label>
        <a href="#" onclick="removePro({id})" style="float: right"><span><img style="margin-top: 15px;float:right" src="<c:url value="/resources/images/jian.png"/>" width="27px"></span></a>
    </p>
</script>

