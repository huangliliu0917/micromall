<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="/resources/css/houtaikk.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery.utils.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/ajaxfileupload.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/admin.upload.js" />"></script>
    <title>编辑商品</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/config/saveConfig" />";
        var uploadUrl = "<c:url value="/upload" />"
        $(function () {
            $("#btnFile").change(function () {
                $("#goodImg").val("");
                $.jBox.tip("正在上传...", "loading");
                fileUpload(null, uploadUrl, function (json) {
                    if (json.result == 1) {
                        $.jBox.tip("上传成功", "success");
                        $("#logo").val(json.file);
                        $("#previewLogo").attr("src", json.fileUri);
                    } else {
                        $.jBox.tip("上传失败", "error");
                    }
                });
            });

            $("#saveSubmit").click(function () {
                var title = $.trim($("#title").val());
                var logo = $("#logo").val();
                var contact = $.trim($("#contact").val());
                var aboutUs = $.trim($("#aboutUs").val()).replace(/\r/g, "").replace(/\n/g, "");

                if (title.length == 0) {
                    $.jBox.tip("请输入商家名称");
                    return;
                }
                if (logo.length == 0) {
                    $.jBox.tip("请上传商家logo");
                    return;
                }
                if (contact.length == 0) {
                    $.jBox.tip("请输入联系方式");
                    return;
                }
                if (aboutUs.length == 0) {
                    $.jBox.tip("请输入关于我们");
                    return;
                }
                var requestData = {
                    title: title,
                    logo: logo,
                    contact: contact,
                    aboutUs: aboutUs
                }

                $.jBox.tip("正在保存...", "loading");
                J.GetJsonRespons(ajaxUrl, requestData, function (json) {
                    if (json == 1) {
                        $.jBox.tip("保存成功", "success");
                        parent.reload();
                    } else {
                        $.jBox.tip("保存失败", "error");
                    }
                }, function () {
                }, J.PostMethod);
            })
        });
    </script>

</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;"><i class="fa  fa-file-o"></i>商家基本设置 </p>

        </div>
        <div class="cnt-wp">
            <form>
                <div class="cnt form">
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <span class="title"><i class="red">*</i>商家名称：</span>
                                    <input type="text" class="text" value="${configBean.title}" id="title"/>
                                </li>
                                <li style="width: 500px;">
                                    <span class="title"><i class="red">*</i>商家Logo：</span>
                                    <input type="file" id="btnFile" name="btnFile" hidden="hidden" onchange="goodHandler.uploadImg()"/>
                                    <input type="hidden" id="logo" readonly="readonly" style="width: 300px" value="${configBean.logo}"/>
                                    <img id="previewLogo" src="${uploadResourceServer.resourceUri(configBean.logo)}"/>

                                    <div class="fg-button clearfix" style="float:right;">
                                        <a href="javascript:$('#btnFile').click();">上传图片</a>
                                    </div>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>联系方式：</span>
                                    <input type="text" class="text" value="${configBean.contact}" id="contact" onkeydown="J.CertainNumber(event)"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>关于我们：</span>
                                    <textarea id="aboutUs">${configBean.aboutUs}</textarea>
                                </li>
                            </ul>
                        </div>
                        <div style="padding:20px 5% 20px 5%">

                            <input type="button" id="saveSubmit" value="保存" class=" copybtn6">
                            <input type="reset" value="重置" class="copybtn7">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

