<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/22
  Time: 15:53
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/prompt/jquery.prompt.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/prompt/prompt.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/ajaxfileupload.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/agentWeb/admin.upload.js" />"></script>
    <title>新增代理商</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/agentApi/agentApply" />";
        var uploadUrl = "<c:url value="/upload?customerId=${customerId}" />";

        function uploadImg() {
            $("#cardIdImg").val("");
            loading.show("正在上传");
            fileUpload(null, uploadUrl, function (json) {
                loading.close();
                if (json.result == 1) {
                    $("#cardIdImg").val(json.file);
                    $("#previewImg").show();
                    $("#previewImg").attr("src", json.fileUri);
                } else {
                    $.jBox.tip("上传失败", "error");
                }
            });
        }

        $(function () {
            $("#btnSave").click(function () {
                var name = $.trim($("#name").val());
                var mobile = $.trim($("#mobile").val());
                var weixin = $.trim($("#weixin").val());
                var referrer = $.trim($("#referrer").val());
                var cardId = $.trim($("#cardId").val());
                var cardIdImg = $("#cardIdImg").val();
                var workOnTime = $.trim($("#workOnTime").val());
                var saleAmount = $.trim($("#saleAmount").val());
                var workOnType = $.trim($("#workOnType").val());
                var area = $.trim($("#area").val());
                var applyLevel = $("#applyLevel").val();
                var applyReason = $.trim($("#applyReason").val()).replace(/\r/g, "").replace(/\n/g, "");

                if (name.length == 0) {
                    SimplePrompt.showPrompt("请输入您的姓名");
                    return;
                }
                if (mobile.length == 0) {
                    SimplePrompt.showPrompt("请输入您的手机号码");
                    return;
                }
                if (!J.IsMobile(mobile)) {
                    SimplePrompt.showPrompt("您输入的手机号码格式不正确");
                    return;
                }
                if (weixin.length == 0) {
                    SimplePrompt.showPrompt("请输入您的微信号");
                    return;
                }
                if (cardId.length == 0) {
                    SimplePrompt.showPrompt("请输入您的身份证号码");
                    return;
                }
                if (cardIdImg.length == 0) {
                    SimplePrompt.showPrompt("请上传手持身份证照片");
                    return;
                }
                if (applyReason.length == 0) {
                    SimplePrompt.showPrompt("请输入申请理由");
                    return;
                }

                var requestData = {
                    name: name,
                    mobile: mobile,
                    weixin: weixin,
                    referrer: referrer,
                    cardId: cardId,
                    workOnTime: workOnTime,
                    saleAmount: saleAmount,
                    workOnType: workOnType,
                    area: area,
                    customerId: customerId,
                    applyLevelId: applyLevel,
                    applyReason: applyReason,
                    cardIdImg: cardIdImg
                }
                loading.show("正在提交");
                J.GetJsonRespons(ajaxUrl, requestData, function (json) {
                    loading.close();
                    if (json.result == 1) {
                        SimplePrompt.showPrompt("感谢您的关注，您已成功提交审核，请等待审核结果");
                    } else {
                        SimplePrompt.showPrompt("您的提交失败了");
                    }
                }, function () {
                }, J.PostMethod);
            })
        })
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div>

    <p class="h20"></p>

    <p class="h20"></p>
    <!---------------------/////////////////------------------------------------>
    <div class="all" style="padding:20px 40px 10px 40px">
        <span class="wz0" style="font-size:40px;">代理商申请</span>

        <p style="clear:both; height:30px"></p>

    </div>
    <!---------------------/////////////////------------------------------------>
    <p class="h10"></p>

</div>
<!---------------------/////////////////------------------------------------>

<div class="add_wei_shang_o bottom">

    <p><label>
        <input type="" id="name" name="mobile" placeholder="姓名"></label></p>

    <p><label>
        <input type="" id="mobile" name="mobile" placeholder="手机号码（将会成为您的登录账号以及收件手机号）"></label></p>

    <p><label>
        <input type="" id="referrer" name="mobile" placeholder="推荐人手机号（可不填）"></label></p>

    <p><label>
        <input type="" id="weixin" name="mobile" placeholder="微信号"></label></p>

    <p><label>
        <input type="" id="cardId" name="mobile" placeholder="身份证号码"></label></p>

    <p><label>手持身份证照片<a href="javascript:$('#btnFile').click()" style="float: right;">上传</a><input type="file" id="btnFile" name="btnFile" hidden="hidden" onchange="uploadImg()"/>
        <img id="previewImg" style="height: 20px;width: 20px;display: none;" src=""/>
        <input type="hidden" id="cardIdImg"/>
    </label></p>

    <p><label>
        <input type="" id="workOnTime" name="mobile" placeholder="从事微商时间"></label></p>

    <p><label>
        <input type="" id="saleAmount" name="mobile" placeholder="月销售额"></label></p>

    <p><label>
        <input type="" id="workOnType" name="mobile" placeholder="目前经营品牌类目"></label></p>

    <p><label>
        <input type="" id="area" name="mobile" placeholder="所在地区"></label></p>

    <p>
        <label>
            <select id="applyLevel" style="width: 100%;background-color: #e7e9eb;border: 0px;color: #000;">
                <option value="0">意向等级</option>
                <c:forEach items="${levelList}" var="levelBean">
                    <option value="${levelBean.levelId}">${levelBean.levelName}</option>
                </c:forEach>
            </select>
        </label>
    </p>
    <p>
        <label>
            <textarea id="applyReason" style="width: 100%;background-color: #e7e9eb;border: 0px;color: #000;" placeholder="申请理由"></textarea>
        </label>
    </p>
</div>
<!---------------------/////////////////------------------------------------>
<p class="h20"></p>

<p class="h10"></p>

<!---------------------/////////////////------------------------------------>
<div class="ws_wrap">
    <div class="add_wei">

        <p class="command" style="background-color:transparent; padding:0px 20%;">
            <a href="#" class="wsws_back button" id="btnSave">提交申请</a>
        </p>
    </div>

</div>
</body>
</html>

