<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/24
  Time: 14:01
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
    <title>发起订单</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/orderApi/createOrder" />";

        $(function () {
            $("#closeDialog").click(function () {
                $("#selGood_dialog").hide();
            })

            $("#num").keyup(function () {
                var num = $("#num").val();
                var price = parseFloat($("#price").html());
                $("#totalPrice").html((num * price).toFixed(2));
            })
        })

        function showSelDialog() {
            $("#selGood_dialog").show();
        }

        function selGood(goodId) {
            var tempHtml = $("#selPanel_template").html();
            tempHtml = tempHtml.replace("{goodId}", goodId);
            tempHtml = tempHtml.replace("{goodImg}", $("#goodImg" + goodId).attr("src"));
            tempHtml = tempHtml.replace(/{goodName}/g, $("#goodName" + goodId).html());
            $("#price").html(parseFloat($("#price" + goodId).val()).toFixed(2));
            $("#selGood_dialog").hide();
            $("#seledPanel").html(tempHtml);
            $("#unSelPanel").hide()
            $("#seledPanel").show();
        }

        function createOrder() {
            var goodId = $("#selGoodId").val();
            var num = $("#num").val();
            var orderName = $("#orderName").val() + "(" + num + ")";
            var shipAddr = $("#shipAddr").html();
            var shipName = $("#shipName").html();
            var shipMobile = $("#shipMobile").html();
            var totalPrice = $("#totalPrice").html();

            if (num.length == 0) {
                SimplePrompt.showPrompt("请输入数量");
                return;
            }
            loading.show("正在保存");
            var requestData = {
                customerId: customerId,
                goodId: goodId,
                proNum: num,
                orderName: orderName,
                shipAddr: shipAddr,
                shipName: shipName,
                shipMobile: shipMobile,
                sendId:${sendId},
                totalPrice: totalPrice
            }
            J.GetJsonRespons(ajaxUrl + "", requestData, function (json) {
                loading.close();
                if (json.result == 1) {
                    SimplePrompt.showPromptWithFunc("保存成功", function () {
                        SimplePrompt.hide();
                        window.location.href = "<c:url value="/order/orderList?customerId=${customerId}"/>";
                    });
                } else {
                    SimplePrompt.showPrompt("保存失败");
                }
            }, function () {
            }, J.PostMethod);

        }
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<!---------------------/////////////////------------------------------------>

<div class="dingdandiss_cell" id="unSelPanel" style="display: block;" onclick="showSelDialog()">
    <p class="fonti" style="height: 16px;">
        选择商品
    </p>

    <p class="picc" style="margin-top: 3px;"><img src="<c:url value="/resources/images/xz.png" />" width="30px"></p>
</div>
<div class="dingdandiss_cell" style="display: none;" id="seledPanel" onclick="showSelDialog()">

</div>

<div id="selGood_dialog" style="background:rgba(0,0,0,0.5); position:absolute;top: 10%;bottom: 0;height: 300px;max-width: 640px;min-width: 320px;overflow:scroll;overflow-x: hidden;display: none;">
    <p style="color:white;font-size: 18px;margin-left: 10px;float: left;margin-top: 10px;">选择商品</p>

    <p id="closeDialog" style="color:white;font-size: 30px;margin-right: 10px;float: right">×</p>

    <p style="clear: both;"></p>
    <c:forEach items="${goodList}" var="goodBean">
        <div onclick="selGood(${goodBean.goodId})" style="margin:10px;" class="dingdandiss_cell">
            <input id="price${goodBean.goodId}" value="${goodBean.price}" type="hidden"/>

            <p class="pngiq"><img id="goodImg${goodBean.goodId}" src="${uploadResourceServer.resourceUri(goodBean.goodImg)}" width=" 65px" height="65px"></p>

            <p class="fonti" id="goodName${goodBean.goodId}">${goodBean.goodName}</p>

            <p style="clear:both; height:5px"></p>
        </div>
    </c:forEach>
</div>


<div class="dingdandiss_cell">
    <dl class="dingdandiss_dl dl_tabel">

        <dt class="ui_color_weak ui_align_right">收货地址：</dt>
        <dd class="" id="shipAddr">${shipInfo.shipAddr}</dd>
        <dd><input style="border: solid 0px;color: #000;" type="text" id="shipAddr" placeholder=""></dd>
        <dt class="ui_color_weak ui_align_right">收件人：</dt>
        <dd class="" id="shipName">${shipInfo.shipName}</dd>
        <dt class="ui_color_weak ui_align_right">联系电话：</dt>
        <dd class="" id="shipMobile">${shipInfo.shipMobile}</dd>
    </dl>
</div>


<div class="dingdandiss_cell">
    <dl class="dingdandiss_dl dl_tabel">

        <dt class="ui_color_weak ui_align_right">单价：</dt>
        <dd class="" id="price"></dd>

    </dl>
</div>


<div class="dingdandiss_cell">
    <dl class="dingdandiss_dl dl_tabel">
        <dt class="ui_color_weak ui_align_right">数量：</dt>
        <dd><input style="border: solid 0px;color: #000;" type="number" id="num" placeholder=""></dd>
    </dl>
</div>


<div class="dingdandiss_cell">
    <dl class="dingdandiss_dl dl_tabel">

        <dt class="ui_color_weak ui_align_right">总价：</dt>
        <dd class="" id="totalPrice"></dd>

    </dl>
</div>

<p style="height:20px;"></p>

<div class="ws_wrap">
    <div class="add_wei">

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a class="wsws_back button" id="add" href="javascript:createOrder()">确认订单</a>
        </p>
    </div>
</div>


</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>
<script type="text/html" id="selPanel_template">
    <input type="hidden" id="selGoodId" value="{goodId}"/>
    <input type="hidden" id="orderName" value="{goodName}">
    <p class="pngiq"><img src="{goodImg}" width="65px" height="auto;"></p>

    <p class="fonti">{goodName}</p>

    <p style="clear:both; height:5px"></p>
</script>

