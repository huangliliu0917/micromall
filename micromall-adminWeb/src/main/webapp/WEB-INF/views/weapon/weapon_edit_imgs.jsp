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
    <script type="text/javascript" src="<c:url value="/resources/scripts/emotion/jquery.qqFace.Data.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/emotion/jquery.qqFace.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/emotion/qqFace.css"/>">
    <title>编辑商品</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/weaponApi/saveWeapon" />";
        var uploadUrl = "<c:url value="/upload" />";

        function fileChange() {
            $.jBox.tip("正在上传...", "loading");
            fileUpload(null, uploadUrl, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("上传成功", "success");
                    var index = $(".weaponImg").length;
                    $("#imgList").append("<li id='imgLi" + index + "'><img class='previewImg' src='" + json.fileUri + "'/>" +
                    "<img onclick='removeImg(" + index + ")' class='delImg' src='<c:url value="/resources/images/delImg.png" />'/>" +
                    "<input class='weaponImg' type='hidden' value='" + json.file + "' /></li>");
                } else {
                    $.jBox.tip("上传失败", "error");
                }
            });
        }

        function removeImg(index) {
            $("#imgLi" + index).remove();
        }

        function checkImgCount() {
            if ($(".weaponImg").length >= 9) {
                $.jBox.tip("最多只能上传9张图片");
                return;
            }
            $("#btnFile").click();
        }

        $(function () {
            $("#saveSubmit").click(function () {
                var weaponContent = $("#weaponContent").val().replace(/\r/g, "").replace(/\n/g, "");
                var weaponImgs = "";
                var $imgs = $(".weaponImg");
                $imgs.each(function (o, item) {
                    if (o == $imgs.length - 1) {
                        weaponImgs += $(item).val();
                    } else {
                        weaponImgs += $(item).val() + ",";
                    }
                });

                if (weaponContent.length == 0) {
                    $.jBox.tip("请输入软文内容");
                    return;
                }
                if ($imgs.length == 0) {
                    $.jBox.tip("请至少上传一张图片");
                    return;
                }
                $.jBox.tip("正在保存", "loading");
                var requestData = {
                    weaponContent: weaponContent,
                    weaponImgs: weaponImgs,
                    weaponType: 0,
                    weaponId:${weaponId}
                }

                J.GetJsonRespons(ajaxUrl, requestData, function (json) {
                    if (json.result == 1) {
                        $.jBox.tip("保存成功", "success");
                        window.location.href = "<c:url value="/weapon/weaponList" />"
                    } else {
                        $.jBox.tip("保存失败", "error");
                    }
                }, function () {
                }, J.PostMethod);
            });

            $("#emotion").qqFace({
                assign: "weaponContent",
                staticpath: "<c:url value="/resources/scripts/emotion/staticface/" />",
                path: "<c:url value="/resources/scripts/emotion/face/" />"
            });
        });
    </script>
    <style type="text/css">
        .waps-step-web li {
            margin: 5px 0;
        }

        #imgUl li {
            width: 100px;
            height: 100px;
            /*border: 1px solid #ccc;*/
            margin-left: 5px;
            float: left
        }

        .previewImg {
            width: 100px;
            height: 100px;
        }

        .delImg {
            width: 25px;
            height: 25px;
            margin-left: 75px;
            margin-top: -100px;
        }

        .emotion {
            width: 42px;
            height: 20px;
            background: url(<c:url value="/resources/images/face.gif" />) no-repeat 2px 2px;
            padding-left: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;"><i class="fa  fa-file-o"></i>添加微武器（${weaponType==0?"图文":"软文"}）</p>

        </div>
        <div class="cnt-wp">
            <form>
                <div class="cnt form">
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <p class="title"><i class="red">*</i>内容：<span class="emotion" id="emotion">表情</span></p>

                                    <p style="height: 10px;"></p>
                                    <textarea id="weaponContent" style="width: 303px;height: 117px;padding: 5px;" placeholder="">${weaponBean.weaponContent}</textarea>
                                </li>
                                <li style="width: 500px;">
                                    <span class="title"><i class="red">*</i>图片（最多上传9张）：</span>
                                    <input type="file" id="btnFile" name="btnFile" onchange="fileChange()" hidden="hidden"/>

                                    <p style="height: 10px;"></p>
                                    <ul style="width: 330px;" id="imgUl">
                                        <div id="imgList">
                                            <c:forEach items="${weaponBean.imgList}" var="img" varStatus="index">
                                                <li id="imgLi${index.index}">
                                                    <img src="${uploadResourceServer.resourceUri(img)}" class="previewImg"/>
                                                    <img onclick='removeImg(${index.index})' class='delImg' src='<c:url value="/resources/images/delImg.png" />'/>
                                                    <input class='weaponImg' type='hidden' value='${img}'/>
                                                </li>
                                            </c:forEach>
                                        </div>
                                        <li style="width:100px;height: 100px;border: 1px solid #ccc; margin-left: 5px; float: left" onclick="checkImgCount()">
                                            <img style="width:100px;height: 100px;" id="addImg" src="<c:url value="/resources/images/addImg.png" />"/>
                                        </li>
                                    </ul>

                                    <%--<div class="fg-button clearfix" style="float:right;">--%>
                                    <%--<a href="javascript:$('#btnFile').click();">上传图片</a>--%>
                                    <%--</div>--%>
                                </li>
                            </ul>
                        </div>
                        <p style="clear: both"></p>

                        <div style="padding:20px 5% 20px 5%">
                            <input type="button" id="saveSubmit" value="保存" class=" copybtn6">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

