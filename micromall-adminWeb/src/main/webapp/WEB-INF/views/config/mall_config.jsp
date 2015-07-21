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
    <script type="text/javascript" src="<c:url value="/resources/scripts/kindeditor-4.1.10/kindeditor-all.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/kindeditor-4.1.10/lang/zh_CN.js" />"></script>
    <title>基本设置</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/config/saveConfig" />";
        var uploadUrl = "<c:url value="/upload" />";

        function uploadLogo() {
            $("#logo").val("");
            $.jBox.tip("正在上传...", "loading");
            fileUpload(null, uploadUrl, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("上传成功", "success");
                    $("#logo").val(json.file);
                    $("#previewLogo").attr("src", json.fileUri);
                    $("#previewLogo").show();
                } else {
                    $.jBox.tip("上传失败", "error");
                }
            });
        }
        $(function () {
            <c:if test="${configBean!=null}">
            $("#previewLogo").show();
            $("input[name='raAboutUsType'][value='${configBean.aboutUsType}']").attr("checked", "checked");
            <c:if test="${configBean.aboutUsType==1}">
            $("#aboutUs").val("${configBean.aboutUs}");
            $("#type1").show();
            $("#type0").hide();
            </c:if>
            <c:if test="${configBean.aboutUsType==0}">
            $("#type1").hide();
            $("#type0").show();
            </c:if>
            </c:if>

            var editor;
            KindEditor.ready(function (K) {
                editor = K.create("#weaponContent", {
                    uploadJson: "<c:url value="/resources/scripts/kindeditor-4.1.10/jsp/upload_json.jsp?customerId=${customerId}" />"
                });
                <c:if test="${configBean!=null}">
                <c:if test="${configBean.aboutUsType==0}">
                editor.html($("#hdContent").html());
                </c:if>

                </c:if>
            });

            $("input[name='raAboutUsType']").change(function () {
                if ($(this).val() == 1) {
                    $("#type1").show();
                    $("#type0").hide();
                } else {
                    $("#type1").hide();
                    $("#type0").show();
                }
            })

            $("#saveSubmit").click(function () {
                var title = $.trim($("#title").val());
                var logo = $("#logo").val();
                var contact = $.trim($("#contact").val());
                var aboutUsType = $("input[name='raAboutUsType']:checked").val();
                var aboutUs;
                if (aboutUsType == 0) {
                    aboutUs = editor.html();
                } else {
                    aboutUs = $.trim($("#aboutUs").val());
                }

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
                    aboutUsType: aboutUsType,
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
                                    <input type="file" id="btnFile" name="btnFile" hidden="hidden" onchange="uploadLogo()"/>
                                    <input type="hidden" id="logo" readonly="readonly" style="width: 300px" value="${configBean.logo}"/>
                                    <img id="previewLogo" style="display: none;width: 100px;" src="${uploadResourceServer.resourceUri(configBean.logo)}"/>

                                    <div class="fg-button clearfix" style="float:right;">
                                        <a href="javascript:$('#btnFile').click();">上传图片</a>
                                    </div>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>联系方式：</span>
                                    <input type="text" class="text" value="${configBean.contact}" id="contact" onkeydown="J.CertainNumber(event)"/>
                                </li>
                                <li>
                                    <p id="hdContent" style="display: none;">
                                        ${configBean.aboutUs}
                                    </p>
                                    <span class="title"><i class="red">*</i>关于我们：</span>
                                    <input type="radio" name="raAboutUsType" checked="checked" value="0"/>自定义
                                    <input type="radio" name="raAboutUsType" value="1"/>外链
                                    <p style="height: 10px;"></p>

                                    <div id="type0">
                                        <textarea id="weaponContent" name="content" style="width:700px;height:300px;"></textarea>
                                    </div>
                                    <div id="type1" style="display: none;">
                                        http:// <input class="text" id="aboutUs"/>
                                    </div>
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

